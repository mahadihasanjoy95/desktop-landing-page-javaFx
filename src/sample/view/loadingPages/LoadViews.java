package sample.view.loadingPages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadViews {


    public static void loadPages(Node node, Class<?> c, String fxml, String css) {
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(c.getClass().getResource(fxml));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene2 = node.getScene();
        scene2.getStylesheets().clear();
        scene2.getStylesheets().add(css);
        scene2.setRoot(root1);


        final Stage stage = (Stage) scene2.getWindow();
        stage.setScene(scene2);
        stage.setMaximized(true);
        stage.setMinWidth(1200);
        stage.setMinHeight(600);
        stage.show();
    }
}

