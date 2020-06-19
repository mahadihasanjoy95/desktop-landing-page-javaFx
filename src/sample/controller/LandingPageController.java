package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LandingPageController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button searchButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawerAction();
    }

    private void drawerAction() {

        Image logo1 = null;
        try {
            logo1 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_495@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView1 = new ImageView(logo1);
        imageView1.setFitWidth(80);
        imageView1.setFitHeight(80);
        JFXButton jfxButton1 = new JFXButton();
        jfxButton1.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton1.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton1.setGraphic(imageView1);
        jfxButton1.setLayoutY(550);
        jfxButton1.setLayoutX(50);
        Text text1 = new Text("Dokan-Dar");
        text1.setFill(Color.WHITE);
        text1.setX(58);
        text1.setY(662);
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        TranslateTransition transition1b = new TranslateTransition();
        transition1b.setDuration(Duration.seconds(1.5));
        transition1b.setByY(-450);
        transition1b.setNode(jfxButton1);
        transition1b.play();
        TranslateTransition transition1t = new TranslateTransition();
        transition1t.setDuration(Duration.seconds(1.5));
        transition1t.setByY(-450);
        transition1t.setNode(text1);
        transition1t.play();

        Image logo2 = null;
        try {
            logo2 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_499@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView2 = new ImageView(logo2);
        imageView2.setFitWidth(80);
        imageView2.setFitHeight(80);
        JFXButton jfxButton2 = new JFXButton();
        jfxButton2.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton2.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton2.setGraphic(imageView2);
        jfxButton2.setLayoutX(200);
        jfxButton2.setLayoutY(550);
        Text text2 = new Text("Tele-Daktar");
        text2.setFill(Color.WHITE);
        text2.setX(208);
        text2.setY(662);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        TranslateTransition transition2b = new TranslateTransition();
        transition2b.setDuration(Duration.seconds(1.6));
        transition2b.setByY(-450);
        transition2b.setNode(jfxButton2);
        transition2b.play();
        TranslateTransition transition2t = new TranslateTransition();
        transition2t.setDuration(Duration.seconds(1.6));
        transition2t.setByY(-450);
        transition2t.setNode(text2);
        transition2t.play();

        Image logo3 = null;
        try {
            logo3 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_362@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView3 = new ImageView(logo3);
        imageView3.setFitWidth(80);
        imageView3.setFitHeight(80);
        JFXButton jfxButton3 = new JFXButton();
        jfxButton3.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton3.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton3.setGraphic(imageView3);
        jfxButton3.setLayoutX(350);
        jfxButton3.setLayoutY(550);
        Text text3 = new Text("Tele-Daktar");
        text3.setFill(Color.WHITE);
        text3.setX(358);
        text3.setY(662);
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition3b = new TranslateTransition();
        transition3b.setDuration(Duration.seconds(1.7));
        transition3b.setByY(-450);
        transition3b.setNode(jfxButton3);
        transition3b.play();
        TranslateTransition transition3t = new TranslateTransition();
        transition3t.setDuration(Duration.seconds(1.7));
        transition3t.setByY(-450);
        transition3t.setNode(text3);
        transition3t.play();

        Image logo4 = null;
        try {
            logo4 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_371@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView4 = new ImageView(logo4);
        imageView4.setFitWidth(80);
        imageView4.setFitHeight(80);
        JFXButton jfxButton4 = new JFXButton();
        jfxButton4.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton4.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton4.setGraphic(imageView4);
        jfxButton4.setLayoutX(500);
        jfxButton4.setLayoutY(550);
        Text text4 = new Text("Tele-Daktar");
        text4.setFill(Color.WHITE);
        text4.setX(508);
        text4.setY(662);
        text4.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition4b = new TranslateTransition();
        transition4b.setDuration(Duration.seconds(1.8));
        transition4b.setByY(-450);
        transition4b.setNode(jfxButton4);
        transition4b.play();
        TranslateTransition transition4t = new TranslateTransition();
        transition4t.setDuration(Duration.seconds(1.8));
        transition4t.setByY(-450);
        transition4t.setNode(text4);
        transition4t.play();

        Image logo5 = null;
        try {
            logo5 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_500@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView5 = new ImageView(logo5);
        imageView5.setFitWidth(80);
        imageView5.setFitHeight(80);
        JFXButton jfxButton5 = new JFXButton();
        jfxButton5.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton5.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton5.setGraphic(imageView5);
        jfxButton5.setLayoutX(650);
        jfxButton5.setLayoutY(550);
        Text text5 = new Text("Tele-Daktar");
        text5.setFill(Color.WHITE);
        text5.setX(658);
        text5.setY(662);
        text5.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition5b = new TranslateTransition();
        transition5b.setDuration(Duration.seconds(1.9));
        transition5b.setByY(-450);
        transition5b.setNode(jfxButton5);
        transition5b.play();
        TranslateTransition transition5t = new TranslateTransition();
        transition5t.setDuration(Duration.seconds(1.9));
        transition5t.setByY(-450);
        transition5t.setNode(text5);
        transition5t.play();


        Image logo6 = null;
        try {
            logo6 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_398@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView6 = new ImageView(logo6);
        imageView6.setFitWidth(80);
        imageView6.setFitHeight(80);
        JFXButton jfxButton6 = new JFXButton();
        jfxButton6.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton6.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton6.setGraphic(imageView6);
        jfxButton6.setLayoutX(808);
        jfxButton6.setLayoutY(550);
        Text text6 = new Text("Tele-Daktar");
        text6.setFill(Color.WHITE);
        text6.setX(808);
        text6.setY(662);
        text6.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition6b = new TranslateTransition();
        transition6b.setDuration(Duration.seconds(2));
        transition6b.setByY(-450);
        transition6b.setNode(jfxButton6);
        transition6b.play();
        TranslateTransition transition6t = new TranslateTransition();
        transition6t.setDuration(Duration.seconds(2));
        transition6t.setByY(-450);
        transition6t.setNode(text6);
        transition6t.play();


        Image logo7 = null;
        try {
            logo7 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_407@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView7 = new ImageView(logo7);
        imageView7.setFitWidth(80);
        imageView7.setFitHeight(80);
        JFXButton jfxButton7 = new JFXButton();
        jfxButton7.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton7.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton7.setGraphic(imageView7);
        jfxButton7.setLayoutX(958);
        jfxButton7.setLayoutY(550);
        Text text7 = new Text("Tele-Daktar");
        text7.setFill(Color.WHITE);
        text7.setX(958);
        text7.setY(662);
        text7.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition7b = new TranslateTransition();
        transition7b.setDuration(Duration.seconds(2.1));
        transition7b.setByY(-450);
        transition7b.setNode(jfxButton7);
        transition7b.play();
        TranslateTransition transition7t = new TranslateTransition();
        transition7t.setDuration(Duration.seconds(2.1));
        transition7t.setByY(-450);
        transition7t.setNode(text7);
        transition7t.play();

        Image logo8 = null;
        try {
            logo8 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_434@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView8 = new ImageView(logo8);
        imageView8.setFitWidth(80);
        imageView8.setFitHeight(80);
        JFXButton jfxButton8 = new JFXButton();
        jfxButton8.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton8.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton8.setGraphic(imageView8);
        jfxButton8.setLayoutX(1108);
        jfxButton8.setLayoutY(550);
        Text text8 = new Text("Tele-Daktar");
        text8.setFill(Color.WHITE);
        text8.setX(1108);
        text8.setY(662);
        text8.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition8b = new TranslateTransition();
        transition8b.setDuration(Duration.seconds(2.2));
        transition8b.setByY(-450);
        transition8b.setNode(jfxButton8);
        transition8b.play();
        TranslateTransition transition8t = new TranslateTransition();
        transition8t.setDuration(Duration.seconds(2.2));
        transition8t.setByY(-450);
        transition8t.setNode(text8);
        transition8t.play();

        Image logo9 = null;
        try {
            logo9 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_498@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView9 = new ImageView(logo9);
        imageView9.setFitWidth(80);
        imageView9.setFitHeight(80);
        JFXButton jfxButton9 = new JFXButton();
        jfxButton9.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton9.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton9.setGraphic(imageView9);
        jfxButton9.setLayoutX(50);
        jfxButton9.setLayoutY(550);
        Text text9 = new Text("Tele-Daktar");
        text9.setFill(Color.WHITE);
        text9.setX(50);
        text9.setY(662);
        text9.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition9b = new TranslateTransition();
        transition9b.setDuration(Duration.seconds(1.8));
        transition9b.setByY(-300);
        transition9b.setNode(jfxButton9);
        transition9b.play();
        TranslateTransition transition9t = new TranslateTransition();
        transition9t.setDuration(Duration.seconds(1.8));
        transition9t.setByY(-300);
        transition9t.setNode(text9);
        transition9t.play();

        Image logo10 = null;
        try {
            logo10 = new Image(new FileInputStream("src/resources/imgs/web_icon/Group_461@2x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView10 = new ImageView(logo10);
        imageView10.setFitWidth(80);
        imageView10.setFitHeight(80);
        JFXButton jfxButton10 = new JFXButton();
        jfxButton10.setButtonType(JFXButton.ButtonType.RAISED);
        jfxButton10.getStyleClass().addAll("animated-option-button", "animated-option-sub-button");
        jfxButton10.setGraphic(imageView10);
        jfxButton10.setLayoutX(208);
        jfxButton10.setLayoutY(550);
        Text text10 = new Text("Tele-Daktar");
        text10.setFill(Color.WHITE);
        text10.setX(208);
        text10.setY(662);
        text10.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TranslateTransition transition10b = new TranslateTransition();
        transition10b.setDuration(Duration.seconds(1.9));
        transition10b.setByY(-300);
        transition10b.setNode(jfxButton10);
        transition10b.play();
        TranslateTransition transition10t = new TranslateTransition();
        transition10t.setDuration(Duration.seconds(1.9));
        transition10t.setByY(-300);
        transition10t.setNode(text10);
        transition10t.play();


        jfxButton1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root1 = null;
                try {
                    StaticValues.w = 1;
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
                    StaticValues.w = 3;
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
                    StaticValues.w = 4;
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
                    StaticValues.w = 5;
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
        pane.getChildren().add(jfxButton6);
        pane.getChildren().add(jfxButton7);
        pane.getChildren().add(jfxButton8);
        pane.getChildren().add(jfxButton9);
        pane.getChildren().add(jfxButton10);
        pane.getChildren().add(text1);
        pane.getChildren().add(text2);
        pane.getChildren().add(text3);
        pane.getChildren().add(text4);
        pane.getChildren().add(text5);
        pane.getChildren().add(text6);
        pane.getChildren().add(text7);
        pane.getChildren().add(text8);
        pane.getChildren().add(text9);
        pane.getChildren().add(text10);
    }
}
