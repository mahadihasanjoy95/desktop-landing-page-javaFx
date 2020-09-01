package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.utils.Constants;

import java.io.IOException;

public class Main extends Application {


    private double xOffset = 0;
    private double yOffset = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Pane root = FXMLLoader.load(getClass().getResource(Constants.FxmlUrl.SPLASH_SCREEN_URL));

        //grab your root here
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        Scene scene = new Scene(root, ((int) Screen.getPrimary().getBounds().getWidth()) - 10, ((int) Screen.getPrimary().getBounds().getHeight()) - 70);
        scene.getStylesheets().add(Constants.FxmlUrl.SPLASH_SCREEN_CSS);
        primaryStage.setTitle("Super Application");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMinWidth(1270);
        primaryStage.setMinHeight(650);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> {
            try {
                loadSecond(primaryStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    public void loadSecond(Stage pro) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource(Constants.FxmlUrl.LOGIN_URL));
        Scene scene2 = new Scene(root1);
        scene2.getStylesheets().add(Constants.FxmlUrl.LOGIN_CSS);
        pro.setScene(scene2);
    }
}
