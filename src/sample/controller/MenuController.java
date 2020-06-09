package sample.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Pane menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawerAction();
    }

    private void drawerAction() {
        Button dokanDarButton = new Button("DokanDar");
        dokanDarButton.setTranslateX(1276);
        dokanDarButton.setTranslateY(20);
        dokanDarButton.setMaxHeight(20);
        dokanDarButton.setMaxWidth(20);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(3));
        transition.setToX(10);
        transition.setByY(10);
        transition.setNode(dokanDarButton);
        transition.play();



        menuPane.getChildren().add(dokanDarButton);

    }
}
