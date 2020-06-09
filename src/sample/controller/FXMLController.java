package sample.controller;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLController implements Initializable {
    
    @FXML
    private Button menu;
    @FXML
    private VBox drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Button teledaktar;

    @FXML
    private Button dokandar;

    @FXML
    private WebView webview;

    @FXML
    private Button history;

    @FXML
    private ToolBar toolbar;

    @FXML
    private Text barText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        drawerAction();
    }  
    
    private void drawerAction() {



        TranslateTransition openNav = new TranslateTransition(new Duration(350), drawer);
        openNav.setToX(0);
        TranslateTransition closeNav = new TranslateTransition(new Duration(350), drawer);

        dokandar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                webview.getEngine().load("https://dokaandar.com/");
                barText.setText("DokanDar");
            }
        });

        teledaktar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                webview.getEngine().load("https://teledocbd.org/");
                barText.setText("Tele Doctor");
            }
        });

        history.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                WebEngine engine = webview.getEngine();
                WebHistory history = engine.getHistory();
                ObservableList<WebHistory.Entry> url = history.getEntries();
                if (url!=null)
                {
                    webview.getEngine().load(url.get(url.size()-2).toString());
                }
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
