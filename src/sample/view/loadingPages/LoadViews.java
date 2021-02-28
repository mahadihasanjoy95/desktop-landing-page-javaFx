package sample.view.loadingPages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class LoadViews {


    /**
     * This is the method is responsible for loading/ switching pages
     *
     * @param node
     * @param c
     * @param fxml
     * @param css
     * @author Mahadi Hasan Joy
     * @version 1.0
     * @since 07-06-2020
     */
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
    }
}

