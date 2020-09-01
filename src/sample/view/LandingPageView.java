package sample.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Window;
import sample.data.controller.ApplicationListController;
import sample.data.controller.UserDetailsController;
import sample.data.dto.ApplicationListDto;
import sample.data.model.ApplicationInfo;
import sample.data.model.Bookmarks;
import sample.data.model.UserDetails;
import sample.database.DatabaseManager;
import sample.helper.Common;
import sample.interfaces.ApplicationListListener;
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

public class LandingPageView implements Initializable, ApplicationListListener, UserDetailsListener, EventHandler<ActionEvent> {

    int bRow = 0;
    int bColumn = 0;
    List<Bookmarks> bookmarksArrayList;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private ToolBar toolbar;
    @FXML
    private MenuButton profileView;
    @FXML
    private GridPane gridpane;
    @FXML
    private GridPane gridpane1;
    @FXML
    private MenuItem btnProfile;
    @FXML
    private MenuItem btnSettings;
    @FXML
    private MenuItem btnLogout;
    @FXML
    private Circle cir;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ScrollPane gScrollPane;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button btnNotification;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtSearch;
    private UserDetails userDetails;
    private ProgressIndicator pi;
    private DatabaseManager databaseManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        bookmarksArrayList = new ArrayList<>();
        databaseManager = new DatabaseManager();
        pi = new ProgressIndicator();
        stackPane.getChildren().add(pi);
        pi.setMaxSize(60, 60);
        stackPane.setAlignment(Pos.CENTER);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        gScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        gScrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");

        cir.setStroke(Color.BLUE);
        cir.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
        anchorpane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 149);
        anchorpane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        toolbar.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 3);
        borderPane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 15);
        scrollPane.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 300);

        gridpane.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()));

        userDetails = new UserDetails();
        final int numCols = 6;
        final int numRows = 3;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            colConst.setHalignment(HPos.CENTER);
            gridpane.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            gridpane.getRowConstraints().add(rowConst);
        }


        new ApplicationListController(this).start(new ApplicationListDto("1", "-1", "hdpi"));
        new UserDetailsController(this).start();

        btnLogout.setOnAction(this);
        btnSettings.setOnAction(this);
        btnProfile.setOnAction(this);
        btnNotification.setOnAction(this);
        btnSearch.setOnAction(this);
        cir.setOnMouseClicked(event -> {
            if (profileView.isShowing())
                profileView.hide();
            else
                profileView.fire();
        });

        txtSearch.setOnKeyReleased(keyEvent -> {
            if (txtSearch.getText().isEmpty())
                searchApps();
        });

        txtSearch.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                stackPane.getChildren().add(pi);

                searchApps();
            }
        });
    }

    private void searchApps() {
        List<ApplicationInfo> list = new ArrayList<>();

        String searchText = txtSearch.getText().toLowerCase();
        for (ApplicationInfo applicationInfo : SuperApplication.getInstance().getApplicationInfoList()) {
            if (applicationInfo.getApplicationName().toLowerCase().contains(searchText)) {
                list.add(applicationInfo);
            }
        }

        setIcons(list);
    }

    private void removeBookmarks(Button favButton, ApplicationInfo applicationInfo) {

        bookmarksArrayList.removeIf(e -> e.getId().equals(applicationInfo.getId()));
        databaseManager.deleteBookmarks(userDetails.getUserId(), applicationInfo.getId());
        Image logo3 = null;
        try {
            logo3 = new Image(new FileInputStream("src/resources/imgs/star1.png"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        ImageView imageView3 = new ImageView(logo3);
        imageView3.setCache(true);
        imageView3.setFitHeight(30);
        imageView3.setFitWidth(30);
        imageView3.setPreserveRatio(true);
        favButton.setGraphic(imageView3);
        ObservableList<Node> btnChilds = gridpane1.getChildren();
        for (Node btn : btnChilds) {
            Button button = (Button) btn;
            if (button.getId().equals(applicationInfo.getId().toString())) {
                gridpane1.getChildren().remove(btn);
            }
        }
    }

    private void loadWebView() {
        LoadViews.loadPages(anchorpane, this.getClass(), Constants.FxmlUrl.WEBVIEW_URL, Constants.FxmlUrl.WEBVIEW_CSS);
    }

    public void setIcons(List<ApplicationInfo> list) {
        gridpane.getChildren().clear();
        int row = 0;
        int column = 0;
        for (ApplicationInfo applicationInfo : list) {
            VBox vBox = new VBox();
            Button button = new Button();


            Image logo1 = null;

            try {
                logo1 = new Image(new FileInputStream("src/resources/imgs/web_icon/default_icon.png"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (!applicationInfo.getAsset().isEmpty()) {
                logo1 = new Image(applicationInfo.getAsset());
            }

            ImageView imageView = new ImageView(logo1);
            imageView.setCache(true);
            imageView.setFitHeight(110);
            imageView.setFitWidth(110);
            imageView.setPreserveRatio(true);
            button.setGraphic(imageView);

            StackPane gridStackPane = new StackPane();
            gridStackPane.setPrefHeight(gridpane.getPrefHeight() - 30);
            Button favButton = new Button();
            ImageView imageView1 = null;

            if (bookmarksArrayList.contains(new Bookmarks(applicationInfo.getId()))) {
                Platform.runLater(() -> setBookMarks(applicationInfo));

                Image logo2 = null;
                try {
                    logo2 = new Image(new FileInputStream("src/resources/imgs/star.png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageView1 = new ImageView(logo2);
            } else {
                Image logo2 = null;
                try {
                    logo2 = new Image(new FileInputStream("src/resources/imgs/star1.png"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imageView1 = new ImageView(logo2);

            }
            imageView1.setCache(true);
            imageView1.setFitHeight(30);
            imageView1.setFitWidth(30);
            imageView1.setPreserveRatio(true);
            favButton.setGraphic(imageView1);
            favButton.setId("favButton");

            favButton.setOnAction((ActionEvent e) -> {

                if (bookmarksArrayList.contains(new Bookmarks(applicationInfo.getId()))) {
                    removeBookmarks(favButton, applicationInfo);
                } else {
                    /**
                     * This portion create a new bookmarks object and store bookmarks on local database
                     */
                    Bookmarks bookmarks = new Bookmarks();
                    bookmarks.setUserId(userDetails.getUserId());
                    bookmarks.setId(applicationInfo.getId());
                    databaseManager.addBookmarks(bookmarks);

                    Image logo3 = null;
                    try {
                        logo3 = new Image(new FileInputStream("src/resources/imgs/star.png"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    ImageView imageView3 = new ImageView(logo3);
                    imageView3.setCache(true);
                    imageView3.setFitHeight(30);
                    imageView3.setFitWidth(30);
                    imageView3.setPreserveRatio(true);
                    favButton.setGraphic(imageView3);
                    setBookMarks(applicationInfo);
                    bookmarksArrayList.add(bookmarks);
                }
            });

            gridStackPane.setMargin(button, new Insets(10, 0, 0, 0));
            gridStackPane.setMargin(favButton, new Insets(0, 0, 90, 90));
            gridStackPane.getChildren().addAll(button, favButton);
            vBox.getChildren().add(gridStackPane);

            favButton.setVisible(false);

            button.setOnMouseEntered(e -> {
                favButton.setVisible(true);
            });
            vBox.setOnMouseExited(e -> {
                favButton.setVisible(false);
            });
            Text text = new Text();
            text.setId("text");
            text.setText(applicationInfo.getApplicationName());
            text.setWrappingWidth(150);
            text.setFill(Color.WHITE);
            vBox.setMargin(text, new Insets(10, 0, 0, 0));
            vBox.setPadding(new Insets(10, 10, 10, 10));
            vBox.getChildren().add(text);
            vBox.setAlignment(Pos.TOP_CENTER);
            vBox.setLayoutY(+700);
            vBox.setId("vbox");
            gridpane.add(vBox, row, column);
            row++;
            if (row != 0 && row % 6 == 0) {
                column++;
                row = 0;
            }

            button.setOnMouseClicked(event -> {
                applicationClick(button, event, applicationInfo, favButton);
            });
        }
        Platform.runLater(() -> {
            stackPane.getChildren().remove(pi);

        });
    }

    /**
     * When user click on application this method will triggered
     *
     * @param button
     * @param event
     * @param applicationInfo
     */
    public void applicationClick(Button button, MouseEvent event, ApplicationInfo applicationInfo, Button favButton) {
        if (event.getButton() == MouseButton.SECONDARY) {
            Window owner = anchorpane.getScene().getWindow();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem menuItem1 = new MenuItem("App Info.");
            menuItem1.setId("appInfo");
            contextMenu.getItems().addAll(menuItem1);
            button.setContextMenu(contextMenu);
            menuItem1.setOnAction((ActionEvent e) -> {
                Common.showAlert(Alert.AlertType.INFORMATION, owner, Messages.FORM_SUCCESS,
                        applicationInfo.getApplicationName() + "\n" + applicationInfo.getWebUrl() + "\n" + applicationInfo.getVersion());
                return;
            });


        } else if (event.getButton() == MouseButton.PRIMARY) {

            pi = new ProgressIndicator();
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);

            Window owner = anchorpane.getScene().getWindow();
            Common.checkInternet(owner);
            Constants.CURRENT_URL = applicationInfo.getWebUrl();
            Constants.CURRENT_APP_NAME = applicationInfo.getApplicationName();
            loadWebView();
        }

    }


    /**
     * This method set every bookmarks & also store this on local database
     *
     * @param applicationInfo
     */
    public void setBookMarks(ApplicationInfo applicationInfo) {
        Button btnBookMarks = new Button();
        Image logo2 = null;
        try {
            logo2 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_495@2x.png"));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        if (!applicationInfo.getAsset().isEmpty()) {
            logo2 = new Image(applicationInfo.getAsset());
        }
        ImageView imageView2 = new ImageView(logo2);
        imageView2.setCache(true);
        imageView2.setFitHeight(50);
        imageView2.setFitWidth(50);
        imageView2.setPreserveRatio(true);
        btnBookMarks.setGraphic(imageView2);
        btnBookMarks.setId(applicationInfo.getId().toString());
        btnBookMarks.setMaxHeight(10);
        btnBookMarks.setMaxWidth(10);
        btnBookMarks.setPadding(Insets.EMPTY);

        /**
         * Checking already in bookmarks grid-pane or, not.
         */
        Node searchNode = null;
        ObservableList<Node> btnChilds = gridpane1.getChildren();
        for (Node btn : btnChilds) {
            if (btn.getId().equals(applicationInfo.getId().toString())) {
                searchNode = btn;
            }
        }
        if (!gridpane1.getChildren().contains(searchNode)) {
            gridpane1.add(btnBookMarks, bColumn, bRow);
            bColumn++;
        }
        /**
         * Bookmarks button action and load web-view page
         */
        btnBookMarks.setOnAction((ActionEvent bookMarksAction) -> {
            pi = new ProgressIndicator();
            stackPane.getChildren().add(pi);
            pi.setMaxSize(60, 60);
            stackPane.setAlignment(Pos.CENTER);

            Window owner4 = anchorpane.getScene().getWindow();
            Common.checkInternet(owner4);
            Constants.CURRENT_URL = applicationInfo.getWebUrl();
            Constants.CURRENT_APP_NAME = applicationInfo.getApplicationName();
            loadWebView();
        });
    }


    public void setProfilePic() {
        if (!userDetails.getPhoto().equals("")) {
            try{
                Image image = new Image(userDetails.getPhoto());
                if (Objects.nonNull(image)) {
                    cir.setFill(new ImagePattern(image));
                    cir.setCache(true);
                }
            }catch (Exception ex)
            {
                System.err.println("Pro Pic Error");
            }

        }
        cir.setCache(true);
        profileView.setStyle("-fx-background-color: transparent;");
    }

    @Override
    public void applicationListCompleted(List<ApplicationInfo> applicationInfoList) {
        SuperApplication.getInstance().setApplicationInfoList(applicationInfoList);
        Platform.runLater(() -> setIcons(applicationInfoList));
    }

    @Override
    public void applicationListFailed(String message) {
        System.out.println(message);
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

        Platform.runLater(() -> setProfilePic());
    }

    @Override
    public void userDetailsFailed(String message) {

    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == btnLogout) {
            Window owner = gridpane.getScene().getWindow();
            Common.showAlert(Alert.AlertType.WARNING, owner, Messages.FORM_SUCCESS, Messages.SIGNOUT_SUCCESS);
            //TODO: Have to expired token here
            LoadViews.loadPages(anchorpane, this.getClass(), Constants.FxmlUrl.LOGIN_URL, Constants.FxmlUrl.LOGIN_CSS);
        } else if (event.getSource() == btnProfile) {
            LoadViews.loadPages(anchorpane, this.getClass(), Constants.FxmlUrl.USER_PROFILE_URL, Constants.FxmlUrl.USER_PROFILE_CSS);
        } else if (event.getSource() == btnSettings) {
            LoadViews.loadPages(anchorpane, this.getClass(), Constants.FxmlUrl.PASSWORD_CHANGE_URL, Constants.FxmlUrl.PASSWORD_CHANGE_CSS);
        } else if (event.getSource() == btnNotification) {
            Window owner = gridpane.getScene().getWindow();
            Common.showAlert(Alert.AlertType.WARNING, owner, Messages.FORM_SUCCESS, "Under Development");
        } else if (event.getSource() == btnSearch) {
            stackPane.getChildren().add(pi);
            searchApps();
        }
    }
}