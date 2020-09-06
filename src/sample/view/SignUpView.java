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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Window;
import jfxtras.styles.jmetro8.JMetro;
import sample.data.dto.SignUpDto;
import sample.helper.Common;
import sample.interfaces.SignUpListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpView implements Initializable, SignUpListener, EventHandler<ActionEvent> {

    @FXML
    private AnchorPane pane;
    @FXML
    private StackPane stackPane;
    @FXML
    private Button login;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox countryList;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtCity;
    @FXML
    private Button register;
    @FXML
    private Button btnShowPass;
    @FXML
    private VBox signInVBox;
    @FXML
    private HBox passHbox;
    private ProgressIndicator pi;
    @FXML
    private TextField txtPassword1;


    private ScreenCal screenCal;
    private CheckBox checkBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        screenCal = new ScreenCal();
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
        screenCal.signUpVBoxAllignment(signInVBox);

        Common.setCountryList(countryList);
        JMetro jMetro = new JMetro(JMetro.Style.LIGHT);
        jMetro.applyTheme(countryList);

        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        txtFirstName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                signInVBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        register.setOnAction(this);
        login.setOnAction(this);
        btnShowPass.setOnAction(this);
    }

    @Override
    public void signUpCompleted(int code) {
        Window owner = pane.getScene().getWindow();

        if (code == Constants.SUCCESS_RESPONSE_CODE_202 || code == Constants.SUCCESS_RESPONSE_CODE_200 || code == Constants.SUCCESS_RESPONSE_CODE_201 || code == Constants.SUCCESS_RESPONSE_CODE_204) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    stackPane.getChildren().remove(pi);
                    Common.showAlert(Alert.AlertType.INFORMATION, owner, Messages.FORM_SUCCESS,
                            Messages.SIGNUP_SUCCESS);
                    LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.SIGNUP_URL, Constants.FxmlUrl.SIGNUP_CSS);
                }
            });
        } else if (code == Constants.SUCCESS_RESPONSE_CODE_409) {
            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.USER_NAME_EMAIL_EXISTS);
                return;
            });
        } else {
            Platform.runLater(() -> {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.SIGNUP_FAILED);
                return;
            });
        }
    }

    @Override
    public void signUpFailed(String message) {
        Window owner = pane.getScene().getWindow();
        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                    Messages.SIGNUP_FAILED);
            return;
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == register) {

            System.out.println(txtPassword1.getText());
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);
            if (txtFirstName.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_FIRSTNAME);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtLastName.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_LASTNAME);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtUserName.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_USERNAME);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtEmailAddress.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_EMAIL);
                stackPane.getChildren().remove(pi);
                return;
            } else if (!Common.emailValidator(txtEmailAddress.getText())) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.INVALID_EMIL_FORMAT);
                stackPane.getChildren().remove(pi);
                return;
            }

            if (txtPassword.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_PASSWORD);
                stackPane.getChildren().remove(pi);
                return;
            }

            if (txtPhoneNumber.getText().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_PHONENUMBER);
                stackPane.getChildren().remove(pi);
                return;
            }


            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String userName = txtUserName.getText();
            String emailAddress = txtEmailAddress.getText();
            String password = txtPassword.getText();
            String phoneNumber = txtPhoneNumber.getText();
            String address = txtAddress.getText();
            String zipCode = txtZipCode.getText();
            String country = (String) countryList.getValue();
            String state = txtState.getText();
            String city = txtCity.getText();

            SignUpDto signUpDto = new SignUpDto(firstName, lastName, userName, emailAddress, password, phoneNumber, country, address, city, state, zipCode);
            new sample.data.controller.SignUpController(this).start(signUpDto);
        } else if (event.getSource() == login) {
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
