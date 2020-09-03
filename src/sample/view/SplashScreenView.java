package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import sample.database.DatabaseManager;
import sample.view.responsive.ScreenCal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenView implements Initializable {

    @FXML
    private VBox vBox;
    @FXML
    private StackPane stackPane;
    private DatabaseManager databaseManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseManager = new DatabaseManager();


//        System.out.println(databaseManager.getAllBookmarks().size());
//        databaseManager.deleteBookmarks("67", Long.valueOf(73));
//        System.out.println(databaseManager.getAllBookmarks().size());

        ScreenCal.getScreenResulation();
        vBox.setPrefWidth(((int) Screen.getPrimary().getBounds().getWidth()) - 10);
        vBox.setPrefHeight(((int) Screen.getPrimary().getBounds().getHeight()) - 70);
//        Circle circle = new Circle(100);
//        circle.setCenterX(((int) Screen.getPrimary().getBounds().getWidth()));
//        circle.setCenterY(((int) Screen.getPrimary().getBounds().getHeight()));
//        circle.setFill(Constants.Colors.color1);
//        ScaleTransition scaleTransitionImage = new ScaleTransition(Duration.seconds(5));
//        scaleTransitionImage.setByX(20f);
//        scaleTransitionImage.setByY(16f);
//        scaleTransitionImage.setCycleCount(1);
//        scaleTransitionImage.setNode(circle);
//        FadeTransition fadeTransitionImage = new FadeTransition(Duration.seconds(5));
//        fadeTransitionImage.setFromValue(1.0f);
//        fadeTransitionImage.setToValue(0f);
//        fadeTransitionImage.setCycleCount(1);
//        ParallelTransition parallelTransitionImage = new ParallelTransition(circle, fadeTransitionImage, scaleTransitionImage);
//        parallelTransitionImage.play();


        Image logo = null;
        try {
            logo = new Image(new FileInputStream("src/resources/imgs/main_logo.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(logo);
        imageView.setX((((int) Screen.getPrimary().getBounds().getWidth()) - 10) / 2);
        imageView.setY(((((int) Screen.getPrimary().getBounds().getHeight()) - 70) / 2));
        imageView.setFitHeight(140);
        imageView.setFitWidth(140);
        imageView.setPreserveRatio(true);
//        ScaleTransition scaleTransitionImage2 = new ScaleTransition(Duration.seconds(5));
//        scaleTransitionImage2.setByX(15f);
//        scaleTransitionImage2.setByY(15f);
//        scaleTransitionImage2.setCycleCount(1);
//        RotateTransition rotateTransitionImage1 = new RotateTransition(Duration.seconds(3));
//        rotateTransitionImage1.setByAngle(360);
//        rotateTransitionImage1.setCycleCount(1);
//        rotateTransitionImage1.setAutoReverse(true);
//        ParallelTransition parallelTransitionImage1 = new ParallelTransition(imageView, scaleTransitionImage2, rotateTransitionImage1);
//        parallelTransitionImage1.play();

        AmbientLight ambient_light = new AmbientLight();
        ambient_light.setLightOn(!ambient_light.isLightOn());


//        pane.getChildren().add(circle);
        stackPane.getChildren().add(imageView);
//        vBox.getChildren().add(ambient_light);
    }
}
