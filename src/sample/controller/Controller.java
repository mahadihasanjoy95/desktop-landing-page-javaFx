package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    Color color1 = Color.web("#4A88AD", 1.0);
    Color color2 = Color.web("#0F75BD", 1.0);
    Color color3 = Color.web("#54A1CB", 1.0);
    Color color4 = Color.web("#346D88", 1.0);
    Color color5 = Color.web("#59A5D7", 1.0);
    Color color6 = Color.web("#5CB772", 1.0);
    Color color7 = Color.web("#4E989B", 1.0);
    Color color8 = Color.web("#4689A4", 1.0);
    Color color9 = Color.web("#3DA0E7", 1.0);
    Color color10 = Color.web("#5CB772", 1.0);
    Color color11 = Color.web("#5CB772", 1.0);
    @FXML
    private Pane pane;

//    @FXML
//    private void nextButton(ActionEvent actionEvent) throws IOException {
//        Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        Scene scene2 = new Scene(root1);
//        scene2.getStylesheets().add("/styles/Styles.css");
//        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        window.setScene(scene2);
//        window.show();
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawerAction();
    }

    private void drawerAction() {

        Rectangle rectangle = new Rectangle(700, 142, 35, 35);
        rectangle.setArcHeight(8);
        rectangle.setArcWidth(8);
        rectangle.setFill(Color.AQUAMARINE);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(3));
        transition.setToX(-690);
        transition.setByY(10);
        transition.setNode(rectangle);
        transition.play();

        Rectangle rectangle11 = new Rectangle(830, 142, 35, 35);
        rectangle11.setArcHeight(8);
        rectangle11.setArcWidth(8);
        rectangle11.setFill(Color.AQUAMARINE);
        TranslateTransition transition11 = new TranslateTransition();
        transition11.setDuration(Duration.seconds(3));
        transition11.setToX(-782);
        transition11.setByY(10);
        transition11.setNode(rectangle11);
        transition11.play();


        Rectangle rectangle1 = new Rectangle(20, 0, 35, 35);
        rectangle1.setArcHeight(8);
        rectangle1.setArcWidth(8);
        rectangle1.setFill(Color.AQUAMARINE);
        TranslateTransition transition1 = new TranslateTransition();
        transition1.setDuration(Duration.seconds(3));
        transition1.setToX(10);
        transition1.setByY(400);
        transition1.setNode(rectangle1);
        transition1.play();


        FileInputStream input = null;
        try {
            input = new FileInputStream("src/resources/imgs/img1.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImagePattern image_pattern = new ImagePattern(image);

        Rectangle rectangle2 = new Rectangle(700, 250, 35, 35);
        rectangle2.setArcHeight(8);
        rectangle2.setArcWidth(8);
        rectangle2.setFill(image_pattern);
        TranslateTransition transition2 = new TranslateTransition();
        transition2.setDuration(Duration.seconds(3));
        transition2.setToX(-690);
        transition2.setByY(10);
        transition2.setNode(rectangle2);
        transition2.play();

        Rectangle rectangle3 = new Rectangle(800, 250, 35, 35);
        rectangle3.setArcHeight(8);
        rectangle3.setArcWidth(8);
        rectangle3.setFill(Color.AQUAMARINE);
        TranslateTransition transition3 = new TranslateTransition();
        transition3.setDuration(Duration.seconds(3));
        transition3.setToX(-768);
        transition3.setByY(10);
        transition3.setNode(rectangle3);
        transition3.play();

        Rectangle rectangle4 = new Rectangle(700, 0, 35, 35);
        rectangle4.setArcHeight(8);
        rectangle4.setArcWidth(8);
        rectangle4.setFill(color1);
        TranslateTransition transition4 = new TranslateTransition();
        transition4.setDuration(Duration.seconds(3));
        transition4.setToX(-690);
        transition4.setByY(0);
        transition4.setNode(rectangle4);
        transition4.play();

        Rectangle rectangle5 = new Rectangle(800, 0, 35, 35);
        rectangle5.setArcHeight(8);
        rectangle5.setArcWidth(8);
        rectangle5.setFill(color2);
        TranslateTransition transition5 = new TranslateTransition();
        transition5.setDuration(Duration.seconds(3));
        transition5.setToX(-752);
        transition5.setByY(0);
        transition5.setNode(rectangle5);
        transition5.play();

        Rectangle rectangle12 = new Rectangle(850, 28, 35, 35);
        rectangle12.setArcHeight(8);
        rectangle12.setArcWidth(8);
        rectangle12.setFill(color3);
        TranslateTransition transition12 = new TranslateTransition();
        transition12.setDuration(Duration.seconds(3));
        transition12.setToX(-764);
        transition12.setByY(10);
        transition12.setNode(rectangle12);
        transition12.play();

        Rectangle rectangle13 = new Rectangle(880, 0, 35, 35);
        rectangle13.setArcHeight(8);
        rectangle13.setArcWidth(8);
        rectangle13.setFill(color1);
        TranslateTransition transition13 = new TranslateTransition();
        transition13.setDuration(Duration.seconds(3));
        transition13.setToX(-756);
        transition13.setByY(0);
        transition13.setNode(rectangle13);
        transition13.play();

        Rectangle rectangle6 = new Rectangle(800, 28, 35, 35);
        rectangle6.setArcHeight(8);
        rectangle6.setArcWidth(8);
        rectangle6.setFill(color3);
        TranslateTransition transition6 = new TranslateTransition();
        transition6.setDuration(Duration.seconds(2.9));
        transition6.setToX(-790);
        transition6.setByY(10);
        transition6.setNode(rectangle6);
        transition6.play();

        Rectangle rectangle7 = new Rectangle(830, 28, 35, 35);
        rectangle7.setArcHeight(8);
        rectangle7.setArcWidth(8);
        rectangle7.setFill(color1);
        TranslateTransition transition7 = new TranslateTransition();
        transition7.setDuration(Duration.seconds(3));
        transition7.setToX(-782);
        transition7.setByY(10);
        transition7.setNode(rectangle7);
        transition7.play();

        Rectangle rectangle14 = new Rectangle(900, 28, 35, 35);
        rectangle14.setArcHeight(8);
        rectangle14.setArcWidth(8);
        rectangle14.setFill(color4);
        TranslateTransition transition14 = new TranslateTransition();
        transition14.setDuration(Duration.seconds(3));
        transition14.setToX(-738);
        transition14.setByY(10);
        transition14.setNode(rectangle14);
        transition14.play();

        FileInputStream input1 = null;
        try {
            input1 = new FileInputStream("src/resources/imgs/img2.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image1 = new Image(input1);
        ImagePattern image_pattern1 = new ImagePattern(image1);

        Rectangle rectangle15 = new Rectangle(976, 28, 35, 35);
        rectangle15.setArcHeight(8);
        rectangle15.setArcWidth(8);
        rectangle15.setFill(image_pattern1);
        TranslateTransition transition15 = new TranslateTransition();
        transition15.setDuration(Duration.seconds(3));
        transition15.setToX(-738);
        transition15.setByY(10);
        transition15.setNode(rectangle15);
        transition15.play();

        Rectangle rectangle16 = new Rectangle(850, 66, 35, 35);
        rectangle16.setArcHeight(8);
        rectangle16.setArcWidth(8);
        rectangle16.setFill(color7);
        TranslateTransition transition16 = new TranslateTransition();
        transition16.setDuration(Duration.seconds(3));
        transition16.setToX(-764);
        transition16.setByY(10);
        transition16.setNode(rectangle16);
        transition16.play();

        Rectangle rectangle17 = new Rectangle(880, 66, 35, 35);
        rectangle17.setArcHeight(8);
        rectangle17.setArcWidth(8);
        rectangle17.setFill(color8);
        TranslateTransition transition17 = new TranslateTransition();
        transition17.setDuration(Duration.seconds(3));
        transition17.setToX(-756);
        transition17.setByY(10);
        transition17.setNode(rectangle17);
        transition17.play();


        Rectangle rectangle8 = new Rectangle(800, 66, 35, 35);
        rectangle8.setArcHeight(8);
        rectangle8.setArcWidth(8);
        rectangle8.setFill(color5);
        TranslateTransition transition8 = new TranslateTransition();
        transition8.setDuration(Duration.seconds(3));
        transition8.setToX(-790);
        transition8.setByY(10);
        transition8.setNode(rectangle8);
        transition8.play();

        Rectangle rectangle9 = new Rectangle(830, 66, 35, 35);
        rectangle9.setArcHeight(8);
        rectangle9.setArcWidth(8);
        rectangle9.setFill(color6);
        TranslateTransition transition9 = new TranslateTransition();
        transition9.setDuration(Duration.seconds(3));
        transition9.setToX(-782);
        transition9.setByY(10);
        transition9.setNode(rectangle9);
        transition9.play();

        Rectangle rectangle10 = new Rectangle(800, 104, 35, 35);
        rectangle10.setArcHeight(8);
        rectangle10.setArcWidth(8);
        rectangle10.setFill(Color.GREEN);
        TranslateTransition transition10 = new TranslateTransition();
        transition10.setDuration(Duration.seconds(3));
        transition10.setToX(-790);
        transition10.setByY(10);
        transition10.setNode(rectangle10);
        transition10.play();

        Rectangle scaleRectangle = new Rectangle(300, 200, 50, 50);
        scaleRectangle.setArcHeight(8);
        scaleRectangle.setArcWidth(8);
        scaleRectangle.setFill(color9);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(5));
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0f);
        fadeTransition.setCycleCount(1);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(5));
        scaleTransition.setByX(20f);
        scaleTransition.setByY(16f);
        scaleTransition.setCycleCount(1);

        ParallelTransition parallelTransition = new ParallelTransition(scaleRectangle, fadeTransition, scaleTransition);
        parallelTransition.play();


        pane.getChildren().add(rectangle);
        pane.getChildren().add(rectangle2);
        pane.getChildren().add(rectangle1);
        pane.getChildren().add(rectangle3);
        pane.getChildren().add(rectangle4);
        pane.getChildren().add(rectangle5);
        pane.getChildren().add(rectangle6);
        pane.getChildren().add(rectangle7);
        pane.getChildren().add(rectangle8);
        pane.getChildren().add(rectangle9);
        pane.getChildren().add(rectangle10);
        pane.getChildren().add(rectangle11);
        pane.getChildren().add(rectangle12);
        pane.getChildren().add(rectangle13);
        pane.getChildren().add(rectangle14);
        pane.getChildren().add(rectangle15);
        pane.getChildren().add(rectangle16);
        pane.getChildren().add(rectangle17);
        pane.getChildren().add(scaleRectangle);


    }


//    @FXML
//    private void nextButton(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add("/styles/Styles.css");
//        Stage primaryStage = new Stage();
//        primaryStage.setTitle("JavaFX and Maven");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//    }

}