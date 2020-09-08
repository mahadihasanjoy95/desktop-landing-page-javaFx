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
import javafx.scene.layout.*;
import javafx.stage.Window;
import okhttp3.ResponseBody;
import sample.data.controller.ChangePasswordController;
import sample.data.dto.ChangePasswordDto;
import sample.data.model.UserDetails;
import sample.utils.Common;
import sample.helper.SuccessResponse;
import sample.interfaces.ChangePasswordListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.utils.Page;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordView implements Initializable, ChangePasswordListener, EventHandler<ActionEvent> {


    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtOldPassword;
    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtConfirmNewPassword;
    @FXML
    private Button btnDone;
    @FXML
    private BorderPane borderPane;

    @FXML
    private ToolBar toolbar;
    @FXML
    private HBox userNameHBox;
    @FXML
    private VBox signUpVBox;

    @FXML
    private StackPane stackPane;
    @FXML
    private TextField txtPassword1;
    @FXML
    private TextField txtPassword2;
    @FXML
    private TextField txtPassword3;
    @FXML
    private Button btnShowPass1;
    @FXML
    private Button btnShowPass2;
    @FXML
    private Button btnShowPass3;
    @FXML
    private VBox forgetPassUpVBox;
    @FXML
    private Button btnLandingPage;


    private UserDetails userDetails;
    private ProgressIndicator pi;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screenCal = new ScreenCal();
        checkBox1 = new CheckBox();
        checkBox2 = new CheckBox();
        checkBox3 = new CheckBox();

        txtPassword1.setManaged(false);
        txtPassword1.setVisible(false);
        txtPassword2.setManaged(false);
        txtPassword2.setVisible(false);
        txtPassword3.setManaged(false);
        txtPassword3.setVisible(false);

        txtPassword1.textProperty().bindBidirectional(txtOldPassword.textProperty());
        txtPassword2.textProperty().bindBidirectional(txtNewPassword.textProperty());
        txtPassword3.textProperty().bindBidirectional(txtConfirmNewPassword.textProperty());

        txtPassword1.managedProperty().bind(checkBox1.selectedProperty());
        txtPassword1.visibleProperty().bind(checkBox1.selectedProperty());
        txtPassword2.managedProperty().bind(checkBox2.selectedProperty());
        txtPassword2.visibleProperty().bind(checkBox2.selectedProperty());
        txtPassword3.managedProperty().bind(checkBox3.selectedProperty());
        txtPassword3.visibleProperty().bind(checkBox3.selectedProperty());

        txtOldPassword.managedProperty().bind(checkBox1.selectedProperty().not());
        txtOldPassword.visibleProperty().bind(checkBox1.selectedProperty().not());
        txtNewPassword.managedProperty().bind(checkBox2.selectedProperty().not());
        txtNewPassword.visibleProperty().bind(checkBox2.selectedProperty().not());
        txtConfirmNewPassword.managedProperty().bind(checkBox3.selectedProperty().not());
        txtConfirmNewPassword.visibleProperty().bind(checkBox3.selectedProperty().not());


        pi = new ProgressIndicator();
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        txtOldPassword.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                signUpVBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        userDetails = new UserDetails();
        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(borderPane);
        screenCal.profileAllignement(pane, new StackPane());
        screenCal.changePassVbox(forgetPassUpVBox);

        btnDone.setOnAction(this);
        btnBack.setOnAction(this);
        btnShowPass1.setOnAction(this);
        btnShowPass2.setOnAction(this);
        btnShowPass3.setOnAction(this);
        btnLandingPage.setOnAction(this);

    }

    @Override
    public void changePasswordCompleted(SuccessResponse<ResponseBody> responseInfo) {
        Window owner = pane.getScene().getWindow();

        if (responseInfo.getResponseMetadata().isSuccessful()) {
            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.INFORMATION, owner, Messages.FORM_SUCCESS, Messages.PASSWORD_CHANGE_SUCCESS);
            });
        } else if (responseInfo.getResponseMetadata().isCloudError()) { // Was it a requirement to show the info of OLD password?
            Platform.runLater(() ->
            {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR, Messages.OLD_PASSWORD_NOT_CORRECT);
            });
        } else {
            Platform.runLater(() ->
            {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR, Messages.FAILED_TO_CHANGE_PASSWORD);
            });
        }
    }

    @Override
    public void changePasswordFailed(String message) {
        Platform.runLater(() ->
        {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.ERROR, pane.getScene().getWindow(), Messages.FORM_ERROR, message);
        });
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnDone) {
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);
            if (txtOldPassword.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_OLD_PASSWORD);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtNewPassword.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_PASSWORD);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtConfirmNewPassword.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_NEW_PASSWORD);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (!txtNewPassword.getText().equals(txtConfirmNewPassword.getText())) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.NEW_CONFIRM_NOT_MATCH);
                stackPane.getChildren().remove(pi);
                return;
            }

            ChangePasswordDto changePasswordDto = new ChangePasswordDto(txtOldPassword.getText(), txtNewPassword.getText(), txtConfirmNewPassword.getText());
            new ChangePasswordController(this).start(changePasswordDto);

        } else if (event.getSource() == btnBack) {
            if (Constants.last_url == Page.LANDING_PAGE) {
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);

            }
            else if (Constants.last_url == Page.WEB_VIEW){
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.WEBVIEW_URL, Constants.FxmlUrl.WEBVIEW_CSS);

            }
        } else if (event.getSource() == btnShowPass1) {
            if (checkBox1.isSelected()) {
                checkBox1.setSelected(false);
                txtPassword1.setPrefWidth(txtOldPassword.getPrefWidth());
            } else {
                checkBox1.setSelected(true);
                txtPassword1.setPrefWidth(txtOldPassword.getPrefWidth());
            }
        } else if (event.getSource() == btnShowPass2) {
            if (checkBox2.isSelected()) {
                checkBox2.setSelected(false);
                txtPassword2.setPrefWidth(txtNewPassword.getPrefWidth());
            } else {
                checkBox2.setSelected(true);
                txtPassword2.setPrefWidth(txtNewPassword.getPrefWidth());
            }
        } else if (event.getSource() == btnShowPass3) {
            if (checkBox3.isSelected()) {
                checkBox3.setSelected(false);
                txtPassword3.setPrefWidth(txtConfirmNewPassword.getPrefWidth());
            } else {
                checkBox3.setSelected(true);
                txtPassword3.setPrefWidth(txtConfirmNewPassword.getPrefWidth());
            }
        }else if (event.getSource() == btnLandingPage) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);

        }
    }
}
