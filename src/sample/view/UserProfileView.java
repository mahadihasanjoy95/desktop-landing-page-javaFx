package sample.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import jfxtras.styles.jmetro8.JMetro;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import sample.data.controller.ProfilePicUploadingController;
import sample.data.controller.ProfileUpdatingController;
import sample.data.dto.SignUpDto;
import sample.data.model.UserDetails;
import sample.helper.SuccessResponse;
import sample.interfaces.ProfilePicUploadingListener;
import sample.interfaces.ProfileUpdatingListener;
import sample.utils.*;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserProfileView implements Initializable, ProfileUpdatingListener, ProfilePicUploadingListener, EventHandler<ActionEvent> {

    private final Stage stage = new Stage();
    private final FileChooser fileChooser = new FileChooser();
    private UserDetails userDetails = new UserDetails();

    @FXML
    private AnchorPane pane;
    @FXML
    private Button btnBack1;
    @FXML
    private Button btnOpen;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmailAddress;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private ComboBox<String> countryList;
    @FXML
    private TextField txtState;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private Button btnUpdate;
    @FXML
    private BorderPane toolBarBorderPane;
    @FXML
    private Circle cir;
    @FXML
    private ToolBar toolbar;
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox profileVBox;
    @FXML
    private Button btnLandingPage;

    private ProgressIndicator pi;
    private ScreenCal screenCal;

    private String userPicURL = "";

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        JMetro jMetro = new JMetro(JMetro.Style.LIGHT);
        jMetro.applyTheme(countryList);
        screenCal = new ScreenCal();
        pi = new ProgressIndicator();
        stackPane.getChildren().add(pi);
        pi.setMaxSize(60, 60);
        stackPane.setAlignment(Pos.CENTER);

        screenCal.profileAllignement(pane, stackPane);
        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(toolBarBorderPane);
        screenCal.profileVBoxAllignment(profileVBox);

        cir.setStroke(Constants.Colors.color5);

        Common.setCountryList(countryList);

        userDetails = SuperApplication.getInstance().getUserDetails();

        if (Objects.nonNull(userDetails.getCountry())) {
            countryList.setOpacity(1);
            countryList.setValue(userDetails.getCountry());
        }

        btnBack1.setOnAction(this);
        btnUpdate.setOnAction(this);
        btnOpen.setOnAction(this);
        btnLandingPage.setOnAction(this);

        loadUserProfileInfo();
    }

    private void loadUserProfileInfo() {
        Platform.runLater(() -> profileInfoUpdate());
        Platform.runLater(() -> Common.setProfilePic(cir, userDetails.getPhoto()));
        Platform.runLater(() -> stackPane.getChildren().remove(pi));
    }

    @Override
    public void uploadingProfilePicCompleted(SuccessResponse<ResponseBody> successResponse) {
        Window owner = pane.getScene().getWindow();

        try {
            userPicURL = successResponse.getResponseData().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SuperApplication.getInstance().getUserDetails().setPhoto(userPicURL);

        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.INFORMATION, owner, Messages.FORM_SUCCESS,
                    Messages.PROFILE_PICTURE_UPDATED);
            return;
        });
    }

    @Override
    public void uploadingProfilePicFailed(String message) {
        Window owner = pane.getScene().getWindow();
        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                    Messages.SERVER_ERROR);
            return;
        });
    }

    @Override
    public void profileUpdatingCompleted(SuccessResponse<UserDetails> result) {
        Window owner = pane.getScene().getWindow();
        this.userDetails = result.getResponseData();
        this.userDetails.setPhoto(userPicURL);
        SuperApplication.getInstance().setUserDetails(this.userDetails);

        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            profileInfoUpdate();
            Common.showAlert(Alert.AlertType.INFORMATION, owner, Messages.FORM_SUCCESS,
                    Messages.PROFILE_UPDATED);
            return;
        });

    }

    @Override
    public void profileUpdatingFailed(String message) {
        Window owner = pane.getScene().getWindow();
        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);
            Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                    message);
            return;
        });
    }

    public void profileInfoUpdate() {
        userPicURL = userDetails.getPhoto();
        txtFirstName.setText(userDetails.getFirstName());
        txtLastName.setText(userDetails.getLastName());
        txtUserName.setText(userDetails.getUserName());
        txtUserName.setEditable(false);
        txtEmailAddress.setText(userDetails.getEmailAddress());
        txtEmailAddress.setEditable(false);
        txtPhoneNumber.setText(userDetails.getPhoneNumber());
        txtAddress.setText(userDetails.getAddress());
        txtZipCode.setText(userDetails.getZipCode());
        txtState.setText(userDetails.getState());
        txtCity.setText(userDetails.getCity());
        countryList.setValue(userDetails.getCountry());
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnBack1) {
            if (Constants.last_url == Page.LANDING_PAGE) {
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);

            } else if (Constants.last_url == Page.WEB_VIEW) {
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.WEBVIEW_URL, Constants.FxmlUrl.WEBVIEW_CSS);

            }
        } else if (event.getSource() == btnOpen) {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                stackPane.getChildren().add(pi);
                pi.setMaxSize(ScreenCal.getScreenResulation().getWidth() / 21, ScreenCal.getScreenResulation().getWidth() / 21);
                stackPane.setAlignment(Pos.CENTER);

                Image image1 = new Image(file.toURI().toString());

                cir.setFill(new ImagePattern(image1));

                final MediaType MEDIA_TYPE_PNG = file.getAbsolutePath().endsWith("JPG") ? MediaType.parse("image/jpeg") : MediaType.parse("image/png");

                RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("userId", userDetails.getUserId().toString())
                        .addFormDataPart("photo", "profile_pic", RequestBody.create(MEDIA_TYPE_PNG, file)).build();
                new ProfilePicUploadingController(this).start(req);

            }
        } else if (event.getSource() == btnUpdate) {
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);
            stackPane.getChildren().add(pi);
            pi.setMaxSize(ScreenCal.getScreenResulation().getWidth() / 21, ScreenCal.getScreenResulation().getWidth() / 21);
            stackPane.setAlignment(Pos.CENTER);

            if (txtFirstName.getText().trim().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_FIRSTNAME);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtLastName.getText().trim().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_LASTNAME);
                stackPane.getChildren().remove(pi);
                return;
            }
            if (txtPhoneNumber.getText().trim().isEmpty()) {
                Common.showAlert(Alert.AlertType.ERROR, owner, Messages.FORM_ERROR,
                        Messages.EMPTY_PHONENUMBER);
                stackPane.getChildren().remove(pi);
                return;
            }


            SignUpDto signUpDto = new SignUpDto(txtFirstName.getText().trim(), txtLastName.getText().trim(), txtUserName.getText().trim(), txtEmailAddress.getText().trim(), "123456789", txtPhoneNumber.getText().trim(), countryList.getValue().toString(), txtAddress.getText().trim(), txtCity.getText().trim(), txtState.getText().trim(), txtZipCode.getText().trim());
            SignUpDto checkSignUpDto = new SignUpDto(userDetails.firstName, userDetails.getLastName(), userDetails.userName, userDetails.getEmailAddress(), "123456789", userDetails.getPhoneNumber(), userDetails.getCountry(), userDetails.getAddress(), userDetails.getCity(), userDetails.getState(), userDetails.getZipCode());
            if (!signUpDto.equals(checkSignUpDto))
                new ProfileUpdatingController(this).start(signUpDto);
            else {
                Common.showAlert(Alert.AlertType.WARNING, owner, "No Changes",
                        "No Changes Detected");
                stackPane.getChildren().remove(pi);
                return;
            }
        } else if (event.getSource() == btnLandingPage) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);

        }
    }
}
