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
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Window;
import sample.data.dto.LogInDto;
import sample.data.model.LogInFailedResponse;
import sample.data.model.LogInResponse;
import sample.helper.Common;
import sample.interfaces.LogInListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInView implements Initializable, LogInListener, EventHandler<ActionEvent> {

    @FXML
    private AnchorPane pane;
    @FXML
    private StackPane stackPane;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button signUp;
    @FXML
    private Button btnForgetPassword;
    @FXML
    private Button login;
    @FXML
    private VBox parentVbox;
    @FXML
    private VBox signInVBox;
    @FXML
    private ToolBar toolbar;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox loginHbox;
    @FXML
    private HBox signUpHBox;
    private ProgressIndicator pi;
    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screenCal = new ScreenCal();

        pi = new ProgressIndicator();

        pane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 10);
        pane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 70);
        toolbar.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 1);
        stackPane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 1);
        stackPane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 70);
        borderPane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 70);
        screenCal.signInVBoxAllignment(signInVBox);

        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        Image logo1 = null;
        try {
            logo1 = new Image(new FileInputStream("src/resources/imgs/background_login.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        txtEmailAddress.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                signInVBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });
        txtEmailAddress.setText("raman@gmail.com");
        txtPassword.setText("12345678");

        login.setOnAction(this);
        signUp.setOnAction(this);
        btnForgetPassword.setOnAction(this);
    }

    @Override
    public void logInCompleted(Object result) {
        if (result instanceof LogInResponse) {
            loadNextPage();
        } else if (result instanceof LogInFailedResponse) {
            Window owner = pane.getScene().getWindow();
            LogInFailedResponse logInFailedResponse = (LogInFailedResponse) result;

            Platform.runLater(() ->
            {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        logInFailedResponse.getMessage());
            });
        } else {
            Window owner = pane.getScene().getWindow();
            Platform.runLater(() ->
            {
                stackPane.getChildren().remove(pi);
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.LOGIN_FAILED);
            });
        }
    }

    @Override
    public void logInFailed(String message) {
        Window owner = pane.getScene().getWindow();
        Platform.runLater(() -> {
            Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                    Messages.SERVER_ERROR);
            stackPane.getChildren().remove(pi);
            return;
        });
    }

    public void loadNextPage() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);
            }
        });
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == login) {
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

            String emailAddress = txtEmailAddress.getText();
            String password = txtPassword.getText();

            LogInDto logInDto = new LogInDto(emailAddress, password);
            new sample.data.controller.LogInController(this).start(logInDto);
        } else if (event.getSource() == signUp) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.SIGNUP_URL, Constants.FxmlUrl.SIGNUP_CSS);
        } else if (event.getSource() == btnForgetPassword) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.FORGET_PASSWORD_URL, Constants.FxmlUrl.FORGET_PASSWORD_CSS);
        }


    }


}


