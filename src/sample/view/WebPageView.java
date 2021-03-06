package sample.view;

import com.jfoenix.controls.JFXHamburger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.data.model.ApplicationInfo;
import sample.data.model.Bookmarks;
import sample.data.model.UserDetails;
import sample.database.DatabaseManager;
import sample.utils.Common;
import sample.utils.Constants;
import sample.utils.Page;
import sample.utils.SuperApplication;
import sample.view.loadingPages.LoadViews;
import sample.view.responsive.ScreenCal;

import java.net.URL;
import java.util.*;

public class WebPageView implements Initializable, EventHandler<ActionEvent> {

    private List<Bookmarks> bookmarksArrayList;
    private DatabaseManager databaseManager;
    private UserDetails userDetails;
    @FXML
    private AnchorPane pane;
    @FXML
    private Circle cir;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Text barText;
    @FXML
    private WebView webview;
    @FXML
    private VBox drawer;
    @FXML
    private ToolBar toolbar;
    @FXML
    private MenuButton profileView;
    @FXML
    private MenuItem btnProfile;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private MenuItem btnSettings;
    @FXML
    private StackPane stackPane;
    @FXML
    private VBox rootVBox;
    @FXML
    private GridPane gridPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button btnLandingPage;

    private Button home = new Button();
    private Button fav = new Button();
    private ProgressIndicator pi;
    private Button prevButton;
    private ScreenCal screenCal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        screenCal = new ScreenCal();
        bookmarksArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager();
        pi = new ProgressIndicator();
        userDetails = SuperApplication.getInstance().getUserDetails();
        drawerAction();
        cir.setStroke(Constants.Colors.color5);
        Platform.runLater(() -> Common.setProfilePic(cir, userDetails.getPhoto()));
    }

    private void drawerAction() {

        Platform.runLater(() -> {
            webview.setContextMenuEnabled(false);
            webview.getEngine().setJavaScriptEnabled(true);
            webview.getEngine().load(Constants.CURRENT_URL);
            barText.setText(Constants.CURRENT_APP_NAME);
            pane.getChildren().remove(pi);
        });

        screenCal.profileAllignement(pane, stackPane);
        screenCal.webViewAllignment(rootVBox, scrollPane);
        screenCal.toolbarAllignment(toolbar);
        screenCal.toolBarBorderPaneAllignment(borderPane);
        gridPane.setMargin(webview, new Insets(0, 0, 0, 316));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Image homeLogo = new Image(Constants.ImageUrl.HOME_ICON);

        ImageView homeImageView = new ImageView(homeLogo);
        homeImageView.setFitHeight(40);
        homeImageView.setFitWidth(40);
        home.setGraphic(homeImageView);
        home.setId("navButton");
        home.setText("  Home");
        home.setStyle("-fx-background-color: #FFFFFF; ");
        home.setTextFill(Constants.Colors.color4);
        home.setMaxWidth(Double.MAX_VALUE);
        home.setAlignment(Pos.BASELINE_LEFT);
        drawer.getChildren().add(home);
        bookmarksArrayList = databaseManager.getUserWiseBookmarks(userDetails.getUserId().toString());
        List<ApplicationInfo> bookMarkList = new ArrayList<>();
        List<ApplicationInfo> applicationInfoList = new ArrayList<>(SuperApplication.getInstance().getApplicationInfoList());
        if (!bookmarksArrayList.isEmpty()) {
            for (ApplicationInfo applicationInfo : applicationInfoList) {
                if (bookmarksArrayList.contains(new Bookmarks(applicationInfo.getId()))) {
                    bookMarkList.add(applicationInfo);
                }
            }
        }

        applicationInfoList.removeAll(bookMarkList);
        applicationInfoList.addAll(0, bookMarkList);
        setIconsInNav(applicationInfoList);


        home.setOnAction(this);

        btnProfile.setOnAction(this);
        btnSettings.setOnAction(this);
        btnLogout.setOnAction(this);
        btnLandingPage.setOnAction(this);
        cir.setOnMouseClicked(event -> {
            if (profileView.isShowing())
                profileView.hide();
            else
                profileView.fire();
        });

        TranslateTransition openNav = new TranslateTransition(new Duration(350), scrollPane);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), scrollPane);

        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            if (scrollPane.getTranslateX() != 0) {
                openNav.play();
                gridPane.setMargin(webview, new Insets(0, 0, 0, drawer.getPrefWidth()));

            } else {
                closeNav.setToX(-(scrollPane.getWidth()));
                closeNav.play();
                gridPane.setMargin(webview, new Insets(0, 0, 0, 0));
            }
        });
    }

    private void setIconsInNav(List<ApplicationInfo> applicationInfos) {

        for (ApplicationInfo applicationInfo : applicationInfos) {
            Button button = new Button();
            button.setId("navButton");
            button.setTextFill(Constants.Colors.color4);
            button.setOnAction(event -> {
                Window owner = pane.getScene().getWindow();
                Common.checkInternet(owner);
                gridPane.getChildren().clear();
                gridPane.getChildren().add(webview);
                if (Objects.nonNull(prevButton)) {
                    prevButton.setTextFill(Constants.Colors.color4);
                    prevButton.setStyle("-fx-background-color: #FFFFFF; ");
                }

                final WebEngine webengine = webview.getEngine();
                webengine.getLoadWorker().stateProperty().addListener(
                        (ov, oldState, newState) -> {
                            if (newState == State.SUCCEEDED) {
                            }
                        });
                webengine.load(applicationInfo.getWebUrl());
                Constants.CURRENT_URL = applicationInfo.getWebUrl();
                Constants.CURRENT_APP_NAME = applicationInfo.getApplicationName();
                barText.setText(applicationInfo.getApplicationName());
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-background-color: #0B33AD; ");
                pane.getChildren().remove(pi);
                prevButton = button;
            });


            setupImage(applicationInfo, button, new ImageView());
            String appName = "  " + Constants.CURRENT_APP_NAME;
            if (appName.equals(button.getText())) {
                button.setTextFill(Color.WHITE);
                button.setStyle("-fx-background-color: #0B33AD; ");
                prevButton = button;
            }
        }
    }

    private void setupImage(ApplicationInfo applicationInfo, Button btn, ImageView imageView) {
        Image logo = new Image(Constants.ImageUrl.DEFAULT_ICON);
        if (!applicationInfo.getAsset().isEmpty()) {
            logo = new Image(applicationInfo.getAsset());
        }
        imageView = new ImageView(logo);
        imageView.setCache(true);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color:#FFFFFF;");

        btn.setGraphic(imageView);
        btn.setText("  " + applicationInfo.getApplicationName());
        btn.setAlignment(Pos.BASELINE_LEFT);
        drawer.getChildren().add(btn);
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == home) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);
        } else if (event.getSource() == btnLogout) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setGraphic(null);
            ButtonType buttonTypeOne = new ButtonType("Yes");
            ButtonType buttonTypeCancel = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                SuperApplication.getInstance().setUserDetails(null);
                SuperApplication.getInstance().setApplicationInfoList(null);
                //TODO: Have to expired token here
                LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS);
            }


        } else if (event.getSource() == btnProfile) {
            Constants.last_url = Page.WEB_VIEW;
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.USER_PROFILE_URL, Constants.FxmlUrl.USER_PROFILE_CSS);
        } else if (event.getSource() == btnSettings) {
            Constants.last_url = Page.WEB_VIEW;
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.PASSWORD_CHANGE_URL, Constants.FxmlUrl.PASSWORD_CHANGE_CSS);
        } else if (event.getSource() == fav) {
            if (Objects.nonNull(prevButton)) {
                prevButton.setTextFill(Constants.Colors.color4);
                prevButton.setStyle("-fx-background-color: #FFFFFF; ");
            }
            fav.setTextFill(Color.WHITE);
            fav.setStyle("-fx-background-color: #0B33AD; ");
            gridPane.getChildren().clear();
            prevButton = fav;
        } else if (event.getSource() == btnLandingPage) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);
        }
    }
}
