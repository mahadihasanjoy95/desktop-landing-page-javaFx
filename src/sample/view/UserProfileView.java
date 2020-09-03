package sample.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import sample.data.controller.ProfilePicUploadingController;
import sample.data.controller.ProfileUpdatingController;
import sample.data.controller.UserDetailsController;
import sample.data.dto.SignUpDto;
import sample.data.model.UserDetails;
import sample.helper.Common;
import sample.helper.SuccessResponse;
import sample.interfaces.ProfilePicUploadingListener;
import sample.interfaces.ProfileUpdatingListener;
import sample.interfaces.UserDetailsListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserProfileView implements Initializable, UserDetailsListener, ProfileUpdatingListener, ProfilePicUploadingListener, EventHandler<ActionEvent> {

    private final Stage stage = new Stage();
    private final FileChooser fileChooser = new FileChooser();
    UserDetails userDetails = new UserDetails();

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

    private ProgressIndicator pi;
    private ScreenCal screenCal;

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        screenCal = new ScreenCal();
        pi = new ProgressIndicator();
        stackPane.getChildren().add(pi);
        pi.setMaxSize(60, 60);
        stackPane.setAlignment(Pos.CENTER);

        screenCal.profileAllignement(pane, stackPane);
        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(toolBarBorderPane);
        screenCal.profileVBoxAllignment(profileVBox);

        cir.setStroke(Color.BLUE);
        cir.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

        Common.setCountryList(countryList);
        new UserDetailsController(this).start();

        if (Objects.nonNull(userDetails.getCountry())) {
            countryList.setOpacity(1);
            countryList.setValue(userDetails.getCountry());
        }

        btnBack1.setOnAction(this);
        btnUpdate.setOnAction(this);
        btnOpen.setOnAction(this);
    }


    @Override
    public void userDetailsCompleted(UserDetails userDetails) {
        this.userDetails = userDetails;
        Platform.runLater(() -> profileInfoUpdate());
        Platform.runLater(() -> setPhotoInCircle());
        Platform.runLater(() -> stackPane.getChildren().remove(pi));
    }

    @Override
    public void userDetailsFailed(String message) {
        System.out.println(message);
    }

    @Override
    public void uploadingProfilePicCompleted(SuccessResponse<ResponseBody> successResponse) {
        Window owner = pane.getScene().getWindow();
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

    public void setPhotoInCircle() {
        if (!userDetails.getPhoto().equals("")) {

            Image image = new Image(userDetails.getPhoto());
            if (Objects.nonNull(image)) {
                cir.setFill(new ImagePattern(image));
                cir.setCache(true);
            }
        }

    }

    public void profileInfoUpdate() {
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
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);
        } else if (event.getSource() == btnOpen) {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                stackPane.getChildren().add(pi);
                pi.setMaxSize(60, 60);
                stackPane.setAlignment(Pos.CENTER);

                Image image1 = new Image(file.toURI().toString());

                cir.setFill(new ImagePattern(image1));

                final MediaType MEDIA_TYPE_PNG = file.getAbsolutePath().endsWith("JPG") ? MediaType.parse("image/jpeg") : MediaType.parse("image/png");

                RequestBody req = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("userId", userDetails.getUserId())
                        .addFormDataPart("photo", "profile_pic", RequestBody.create(MEDIA_TYPE_PNG, file)).build();
                new ProfilePicUploadingController(this).start(req);

            }
        } else if (event.getSource() == btnUpdate) {
            Window owner = pane.getScene().getWindow();
            Common.checkInternet(owner);
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);

            SignUpDto signUpDto = new SignUpDto(txtFirstName.getText(), txtLastName.getText(), txtUserName.getText(), txtEmailAddress.getText(), "123456789", txtPhoneNumber.getText(), countryList.getValue().toString(), txtAddress.getText(), txtCity.getText(), txtState.getText(), txtZipCode.getText());
            new ProfileUpdatingController(this).start(signUpDto);
        }
    }
}
