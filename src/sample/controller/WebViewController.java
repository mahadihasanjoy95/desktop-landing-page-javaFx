package sample.controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebViewController implements Initializable {

    @FXML
    private VBox drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Button teledaktar;

    @FXML
    private Button pay365;

    @FXML
    private Button dokandar;

    @FXML
    private Button backButton;
    @FXML
    private Button home;

    @FXML
    private WebView webview;


    @FXML
    private Text barText;

    @FXML
    private Text copyRight;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        drawerAction();
    }

    private void drawerAction() {

        copyRight.setText("  \u00a9 Datasoft Systems Bangladest Ltd.\n\t\tAll Rights Reserved");
        copyRight.setTranslateY(430);
        copyRight.setFill(Color.WHITE);


        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);
        if (StaticValues.w == 1) {
            webview.getEngine().load("https://teledocbd.org/");
            barText.setText("Dokan-Dar");
            StaticValues.w = 0;
        } else if (StaticValues.w == 2) {
            webview.getEngine().load("https://teledocbd.org/");
            barText.setText("Tele-Daktar");
            StaticValues.w = 0;
        } else if (StaticValues.w == 3) {

            webview.getEngine().load("http://datasoft-bd.com/pay-365/");
            barText.setText("Pay 365");
            StaticValues.w = 0;

        } else if (StaticValues.w == 4) {
            webview.getEngine().load("http://datasoft-bd.com/microfin-360/");
            barText.setText("Microfin 360");
            StaticValues.w = 0;
        } else if (StaticValues.w == 5) {
            webview.getEngine().load("https://teledocbd.org/");
            barText.setText("Tele-Daktar");
            StaticValues.w = 0;
        } else {

        }

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Parent root1 = null;
                try {
                    root1 = FXMLLoader.load(getClass().getResource("/fxml/landing_page.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene2 = new Scene(root1);
                scene2.getStylesheets().add("/styles/landing_page.css");
                Node node = (Node) event.getSource();
                final Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene2);
                stage.show();
            }
        });

        dokandar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                webview.getEngine().load("https://teledocbd.org/");
                barText.setText("Dokan-Dar");
            }
        });

        teledaktar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                webview.getEngine().load("https://teledocbd.org/");
                barText.setText("Tele-Daktar");
            }
        });

        pay365.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                webview.getEngine().load("http://datasoft-bd.com/pay-365/");
                barText.setText("Pay - 365");
            }
        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final WebHistory history = webview.getEngine().getHistory();
                ObservableList<WebHistory.Entry> entryList = history.getEntries();
                int currentIndex = history.getCurrentIndex();

                Platform.runLater(new Runnable() {
                    public void run() {
                        if (entryList.size() > 1) {
                            history.go(-1);
                        }
                    }
                });
            }
        });

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.getTranslateX() != 0) {
                openNav.play();
            } else {
                closeNav.setToX(-(drawer.getWidth()));
                closeNav.play();
            }
        });

    }
}
