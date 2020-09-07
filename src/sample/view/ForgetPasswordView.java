package sample.view;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Window;
import okhttp3.ResponseBody;
import sample.data.dto.SendOtpDto;
import sample.helper.Common;
import sample.helper.ResponseMetadata;
import sample.helper.SuccessResponse;
import sample.interfaces.ForgotPasswordListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.util.ResourceBundle;

public class ForgetPasswordView implements Initializable, ForgotPasswordListener, EventHandler<ActionEvent> {

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private Button btnSendLink;
    @FXML
    private Button btnSignIn;
    @FXML
    private VBox forgetPassUpVBox;
    @FXML
    private ToolBar toolbar;
    @FXML
    private BorderPane borderPane;
    @FXML
    private StackPane stackPane;
    private ProgressIndicator pi;
    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        screenCal = new ScreenCal();

        pi = new ProgressIndicator();
        pi.setLayoutX((((int) Screen.getPrimary().getBounds().getWidth()) - 10) / 2);
        pi.setLayoutY((((int) Screen.getPrimary().getBounds().getHeight()) - 70) / 2);
        screenCal.profileAllignement(pane,new StackPane());
        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(borderPane);
        screenCal.forgetPassVBoxAllignment(forgetPassUpVBox);
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        txtEmailAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                forgetPassUpVBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        btnSendLink.setOnAction(this);
        btnSignIn.setOnAction(this);
    }

    @Override
    public void forgotPasswordCompleted(SuccessResponse<ResponseBody> successResponse) {
        ResponseMetadata responseMetadata = successResponse.getResponseMetadata();

        if (responseMetadata.isSuccessful()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stackPane.getChildren().remove(pi);
                    LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.PASSWORD_RESET_URL, Constants.FxmlUrl.PASSWORD_RESET_CSS);
                }
            });
        } else if (responseMetadata.isConflict() || responseMetadata.isNotFoundError()) {

            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), Messages.FORM_ERROR, Messages.EMAIL_NOT_REGISTERED);
            });
        } else {
            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), Messages.FORM_ERROR, Messages.FAILED_TO_SEND_EMAIL);
            });
        }
    }

    @Override
    public void forgotPasswordFailed(String message) {
        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.WARNING, pane.getScene().getWindow(), Messages.FORM_ERROR,
                    message);
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnSendLink) {
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);

            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);

            if (txtEmailAddress.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR, Messages.EMPTY_EMAIL);
                stackPane.getChildren().remove(pi);
                return;
            } else if (!Common.emailValidator(txtEmailAddress.getText())) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.INVALID_EMIL_FORMAT);
                stackPane.getChildren().remove(pi);
                return;
            }
            Constants.EMAIL_FORGET_PASS = txtEmailAddress.getText();
            SendOtpDto sendOtpDto = new SendOtpDto(txtEmailAddress.getText());
            new sample.data.controller.ForgotPasswordController(this).start(sendOtpDto);

        } else if (event.getSource() == btnSignIn) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS);
        }
    }
}
