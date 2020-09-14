package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.database.DatabaseManager;
import sample.utils.Constants;
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
    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        databaseManager = new DatabaseManager();
        databaseManager.createTable();
        screenCal = new ScreenCal();
        screenCal.splashScreenAllignment(vBox);

        Image image = new Image(Constants.ImageUrl.MAIN_LOGO);
        ImageView imageView = new ImageView(image);
        screenCal.splashScreenImage(imageView);
        imageView.setPreserveRatio(true);

        AmbientLight ambient_light = new AmbientLight();
        ambient_light.setLightOn(!ambient_light.isLightOn());

        stackPane.getChildren().add(imageView);

    }
}
