package sample.view;

import com.jfoenix.controls.JFXHamburger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;
import sample.data.controller.UserDetailsController;
import sample.data.model.ApplicationInfo;
import sample.data.model.Bookmarks;
import sample.data.model.UserDetails;
import sample.database.DatabaseManager;
import sample.helper.Common;
import sample.interfaces.UserDetailsListener;
import sample.utils.Constants;
import sample.utils.Messages;
import sample.utils.SuperApplication;
import sample.view.loadingPages.LoadViews;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class WebPageView implements Initializable, UserDetailsListener, EventHandler<ActionEvent> {

    int bRow = 0;
    int bColumn = 0;
    List<Bookmarks> bookmarksArrayList;
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

    private Button home = new Button();
    private Button fav = new Button();
    private ProgressIndicator pi;
    private Button prevButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookmarksArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager();
        pi = new ProgressIndicator();
        userDetails = new UserDetails();
        drawerAction();
        cir.setStroke(Color.BLUE);
        cir.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

    }

    private void drawerAction() {

        Platform.runLater(() -> {
            webview.getEngine().load(Constants.CURRENT_URL);
            barText.setText(Constants.CURRENT_APP_NAME);
            pane.getChildren().remove(pi);
        });

        pane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        pane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 10);
        scrollPane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 10);
        stackPane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        stackPane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 10);
        rootVBox.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        rootVBox.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 10);
        toolbar.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        toolbar.setMinWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        borderPane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 15);
        gridPane.setMargin(webview, new Insets(0, 0, 0, 316));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        new UserDetailsController(this).start();

        setIconsInNav();
        home.setOnAction(this);
        fav.setOnAction(this);

        btnProfile.setOnAction(this);
        btnSettings.setOnAction(this);
        btnLogout.setOnAction(this);
        cir.setOnMouseClicked(event -> {
            if (profileView.isShowing())
                profileView.hide();
            else
                profileView.fire();
        });

        TranslateTransition openNav = new TranslateTransition(new Duration(350), scrollPane);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), scrollPane);
//        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
//        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//            transition.setRate(transition.getRate() * -1);
//            transition.play();

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

    private void setIconsInNav() {

        Image homeLogo = null;
        try {
            homeLogo = new Image(new FileInputStream("src/resources/imgs/home.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView homeImageView = new ImageView(homeLogo);
        homeImageView.setFitHeight(40);
        homeImageView.setFitWidth(40);
        home.setGraphic(homeImageView);
        home.setText("  Home");
        home.setStyle("-fx-background-color: #FFFFFF; ");
        home.setTextFill(Constants.Colors.color4);
        home.setMaxWidth(Double.MAX_VALUE);
        home.setAlignment(Pos.BASELINE_LEFT);
        drawer.getChildren().add(home);

        Image favLogo = null;
        try {
            favLogo = new Image(new FileInputStream("src/resources/imgs/fav_icon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView favImageView = new ImageView(favLogo);
        favImageView.setFitHeight(40);
        favImageView.setFitWidth(40);
        fav.setGraphic(favImageView);
        fav.setText("  Favourite");
        fav.setStyle("-fx-background-color: #FFFFFF; ");
        fav.setTextFill(Constants.Colors.color4);
        fav.setMaxWidth(Double.MAX_VALUE);
        fav.setAlignment(Pos.BASELINE_LEFT);
        drawer.getChildren().add(fav);

        for (ApplicationInfo applicationInfo : SuperApplication.getInstance().getApplicationInfoList()) {
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
        Image logo = null;
        try {
            logo = new Image(new FileInputStream("src/resources/imgs/web_icon/default_icon.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
    public void userDetailsCompleted(UserDetails userDetails) {
        this.userDetails = userDetails;
        /**
         * This method fetch all bookmarks by particular user form local database
         */
        Platform.runLater(() -> {
            bookmarksArrayList = databaseManager.getUserWiseBookmarks(userDetails.getUserId());
        });
        Platform.runLater(() -> setProfilePhoto());
    }

    @Override
    public void userDetailsFailed(String message) {

    }

    public void setProfilePhoto() {
        if (!userDetails.getPhoto().equals("")) {
            Image image = new Image(userDetails.getPhoto());
            if (Objects.nonNull(image)) {
                cir.setFill(new ImagePattern(image));
                cir.setCache(true);
            }
        }
        cir.setCache(true);
        profileView.setStyle("-fx-background-color: transparent;");
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == home) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LANDING_PAGE_URL, Constants.FxmlUrl.LANDING_PAGE_CSS);
        } else if (event.getSource() == btnLogout) {
            Window owner = scrollPane.getScene().getWindow();
            Common.showAlert(Alert.AlertType.WARNING, owner, Messages.FORM_SUCCESS,
                    Messages.SIGNOUT_SUCCESS);
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS);
        } else if (event.getSource() == btnProfile) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.USER_PROFILE_URL, Constants.FxmlUrl.USER_PROFILE_CSS);
        } else if (event.getSource() == btnSettings) {
            LoadViews.loadPages(pane, this.getClass(), Constants.FxmlUrl.PASSWORD_CHANGE_URL, Constants.FxmlUrl.PASSWORD_CHANGE_CSS);
        }else if (event.getSource() == fav) {
            if (Objects.nonNull(prevButton)) {
                prevButton.setTextFill(Constants.Colors.color4);
                prevButton.setStyle("-fx-background-color: #FFFFFF; ");
            }
            fav.setTextFill(Color.WHITE);
            fav.setStyle("-fx-background-color: #0B33AD; ");
            gridPane.getChildren().clear();
            for (Bookmarks bookmarks: bookmarksArrayList)
            {

            }
            prevButton = fav;
        }
    }
}
