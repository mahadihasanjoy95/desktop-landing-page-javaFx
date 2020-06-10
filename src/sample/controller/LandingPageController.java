package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingPageController implements Initializable {

    @FXML
    private AnchorPane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawerAction();
    }

    private void drawerAction() {


        Image logo1 = null;
        try {
            logo1 = new Image(new FileInputStream("src/resources/imgs/logos/Chatbot.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView1 = new ImageView(logo1);
        imageView1.setFitWidth(60);
        imageView1.setFitHeight(60);
        JFXButton jfxButton1 = new JFXButton();
        jfxButton1.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton1.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        jfxButton1.setGraphic(imageView1);



        Image logo2 = null;
        try {
            logo2 = new Image(new FileInputStream("src/resources/imgs/logos/Advance-Dashboard.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView2 = new ImageView(logo2);
        imageView2.setFitWidth(60);
        imageView2.setFitHeight(60);
        JFXButton jfxButton2 = new JFXButton();
        jfxButton2.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton2.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        jfxButton2.setGraphic(imageView2);
        jfxButton2.setLayoutX(150);


        jfxButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    root1 = FXMLLoader.load(getClass().getResource("/fxml/webview_nav.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene2 = new Scene(root1);
                scene2.getStylesheets().add("/styles/Styles.css");
                Node node = (Node) event.getSource();
                final Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene2);
                stage.show();
            }
        });
        pane.getChildren().add(jfxButton1);
        pane.getChildren().add(jfxButton2);


    }
}
