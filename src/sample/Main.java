package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        Scene scene = new Scene(root, 1276, 650);
        scene.getStylesheets().add("/styles/Styles.css");
        primaryStage.setTitle("Transition");
        primaryStage.setScene(scene);
        primaryStage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
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
        Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene2 = new Scene(root1);
        scene2.getStylesheets().add("/styles/Styles.css");
        pro.setScene(scene2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
