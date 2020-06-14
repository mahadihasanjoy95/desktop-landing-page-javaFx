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

        Image logo3 = null;
        try {
            logo3 = new Image(new FileInputStream("src/resources/imgs/logos/Digital-Passbook.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView3 = new ImageView(logo3);
        imageView3.setFitWidth(60);
        imageView3.setFitHeight(60);
        JFXButton jfxButton3 = new JFXButton();
        jfxButton3.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton3.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        jfxButton3.setGraphic(imageView3);
        jfxButton3.setLayoutX(300);


        Image logo4 = null;
        try {
            logo4 = new Image(new FileInputStream("src/resources/imgs/logos/Message-Center.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView4 = new ImageView(logo4);
        imageView4.setFitWidth(60);
        imageView4.setFitHeight(60);
        JFXButton jfxButton4 = new JFXButton();
        jfxButton4.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton4.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        jfxButton4.setGraphic(imageView4);
        jfxButton4.setLayoutX(450);

        Image logo5 = null;
        try {
            logo5 = new Image(new FileInputStream("src/resources/imgs/logos/Issue-Tracing-System.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView5 = new ImageView(logo5);
        imageView5.setFitWidth(60);
        imageView5.setFitHeight(60);
        JFXButton jfxButton5 = new JFXButton();
        jfxButton5.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton5.getStyleClass().addAll("animated-option-button","animated-option-sub-button");
        jfxButton5.setGraphic(imageView5);
        jfxButton5.setLayoutX(600);

        jfxButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w=1;
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

        jfxButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w = 2;
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

        jfxButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w=3;
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

        jfxButton4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w=4;
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

        jfxButton5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w=5;
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
        pane.getChildren().add(jfxButton3);
        pane.getChildren().add(jfxButton4);
        pane.getChildren().add(jfxButton5);


    }
}
