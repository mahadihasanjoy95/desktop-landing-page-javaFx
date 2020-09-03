package sample.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AmbientLight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
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

    private ScreenCal screenCal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        screenCal = new ScreenCal();
        screenCal.splashScreenAllignment(vBox);


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

        AmbientLight ambient_light = new AmbientLight();
        ambient_light.setLightOn(!ambient_light.isLightOn());

        stackPane.getChildren().add(imageView);

    }
}
