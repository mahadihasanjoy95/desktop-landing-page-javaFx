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
import sample.data.dto.PasswordResetDto;
import sample.helper.Common;
import sample.helper.ResponseMetadata;
import sample.helper.SuccessResponse;
import sample.interfaces.PasswordResetListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordView implements Initializable, PasswordResetListener, EventHandler<ActionEvent> {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnSignIn;
    @FXML
    private StackPane stackPane;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private TextField txtOtp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private VBox signUpVBox;

    @FXML
    private ToolBar toolbar;
    @FXML
    private Button btnSubmit;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField txtPassword1;
    @FXML
    private Button btnShowPass;
    @FXML
    private VBox forgetPassUpVBox;

    private ProgressIndicator pi;
    private CheckBox checkBox;
    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screenCal = new ScreenCal();
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        checkBox = new CheckBox();
        txtPassword1.setManaged(false);
        txtPassword1.setVisible(false);

        txtPassword1.textProperty().bindBidirectional(txtPassword.textProperty());
        txtPassword1.managedProperty().bind(checkBox.selectedProperty());
        txtPassword1.visibleProperty().bind(checkBox.selectedProperty());

        txtPassword.managedProperty().bind(checkBox.selectedProperty().not());
        txtPassword.visibleProperty().bind(checkBox.selectedProperty().not());

        pi = new ProgressIndicator();
        pi.setLayoutX((((int) Screen.getPrimary().getBounds().getWidth()) - 10) / 2);
        pi.setLayoutY((((int) Screen.getPrimary().getBounds().getHeight()) - 70) / 2);

        screenCal.profileAllignement(pane, new StackPane());

        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(borderPane);
        screenCal.signInVBoxAllignment(forgetPassUpVBox);

        btnSubmit.setOnAction(this);
        btnSignIn.setOnAction(this);
        btnShowPass.setOnAction(this);

        txtEmailAddress.setText(Constants.EMAIL_FORGET_PASS);
        txtEmailAddress.setEditable(false);

    }

    @Override
    public void passwordResetCompleted(SuccessResponse<ResponseBody> successResponse) {
        ResponseMetadata responseMetadata = successResponse.getResponseMetadata();
        if (responseMetadata.isSuccessful()) {
            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.INFORMATION, pane.getScene().getWindow(), Messages.FORM_SUCCESS, Messages.PASSWORD_CHANGE_SUCCESS);
            });
            Constants.EMAIL_FORGET_PASS = "";
            Platform.runLater(() -> LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS));

        } else {
            Platform.runLater(() ->
            {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), Messages.FORM_ERROR, Messages.FAILED_TO_RESET_PASSWORD);
            });
        }
    }

    @Override
    public void passwordResetFailed(String message) {
        Platform.runLater(() ->
        {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), Messages.FORM_ERROR, Messages.FAILED_TO_RESET_PASSWORD);
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnSubmit) {
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);
            if (txtEmailAddress.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_EMAIL);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtOtp.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_OTP);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtPassword.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_PASSWORD);
                stackPane.getChildren().remove(pi);
                return;
            }

            PasswordResetDto passwordResetDto = new PasswordResetDto(txtEmailAddress.getText(), txtPassword.getText(), txtOtp.getText());
            new sample.data.controller.PasswordResetController(this).start(passwordResetDto);

        } else if (event.getSource() == btnSignIn) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS);
        } else if (event.getSource() == btnShowPass) {

            if (checkBox.isSelected()) {
                checkBox.setSelected(false);
                txtPassword1.setPrefWidth(txtPassword.getPrefWidth());
            } else {
                checkBox.setSelected(true);
                txtPassword1.setPrefWidth(txtPassword.getPrefWidth());
            }
        }
    }
}
