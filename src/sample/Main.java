package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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


        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (!canExit()) {
                    event.consume();
                    primaryStage.show();
                }
            }
        });

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

    /**
     * This method load next scene after a pause transition
     *
     * @return
     * @author Mahadi Hasan Joy
     * @version 1.0
     * @since 06-07-2020
     */
    public void loadSecond(Stage pro) throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource(Constants.FxmlUrl.LOGIN_URL));
        Scene scene2 = new Scene(root1);
        scene2.getStylesheets().add(Constants.FxmlUrl.LOGIN_CSS);
        pro.setScene(scene2);
    }

    /**
     * This method will check where user really want to terminate application or, not
     *
     * @return
     * @author Mahadi Hasan Joy
     * @version 1.0
     * @since 08-09-2020
     */
    private boolean canExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Quit this?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            return true;
        } else {
            return false;
        }
    }
}
