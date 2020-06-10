package sample.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {

    Color color1 = Color.web("#2E00C7", 1.0);

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Circle circle = new Circle(100);
        circle.setCenterX(1000);
        circle.setCenterY(600);
        circle.setFill(color1);
        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(5));
        scaleTransitionImage.setByX(20f);
        scaleTransitionImage.setByY(16f);
        scaleTransitionImage.setCycleCount(1);
        scaleTransitionImage.setNode(circle);
        FadeTransition fadeTransitionImage = new FadeTransition(Duration.seconds(5));
        fadeTransitionImage.setFromValue(1.0f);
        fadeTransitionImage.setToValue(0f);
        fadeTransitionImage.setCycleCount(1);
        ParallelTransition parallelTransitionImage = new ParallelTransition(circle, fadeTransitionImage, scaleTransitionImage);
        parallelTransitionImage.play();


        Image logo = null;
        try {
            logo = new Image(new FileInputStream("src/resources/imgs/logo1.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(logo);
        imageView.setX(400);
        imageView.setY(200);
        imageView.setFitHeight(8);
        imageView.setFitWidth(8);
        ScaleTransition scaleTransitionImage2 = new ScaleTransition(Duration.seconds(5));
        scaleTransitionImage2.setByX(30f);
        scaleTransitionImage2.setByY(30f);
        scaleTransitionImage2.setCycleCount(1);
        RotateTransition rotateTransitionImage1 = new RotateTransition(Duration.seconds(3));
        rotateTransitionImage1.setByAngle(360);
        rotateTransitionImage1.setCycleCount(1);
        rotateTransitionImage1.setAutoReverse(true);
        ParallelTransition parallelTransitionImage1 = new ParallelTransition(imageView, scaleTransitionImage2, rotateTransitionImage1);
        parallelTransitionImage1.play();

        AmbientLight ambient_light = new AmbientLight();
        ambient_light.setLightOn(!ambient_light.isLightOn());

        Text copyRight = new Text("");
        copyRight.setText("  \u00a9 Datasoft Systems Bangladest Ltd.\n\t\tAll Rights Reserved");
        copyRight.setX(300);
        copyRight.setY(400);
        copyRight.setFill(Color.BLACK);
        copyRight.setFont(Font.font ("cursive", FontWeight.BOLD, 10));;
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(8));
        fadeTransition.setFromValue(.3f);
        fadeTransition.setToValue(1.5f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setNode(copyRight);
        fadeTransition.play();


        pane.getChildren().add(circle);
        pane.getChildren().add(imageView);
        pane.getChildren().add(ambient_light);
        pane.getChildren().add(copyRight);
    }
}
