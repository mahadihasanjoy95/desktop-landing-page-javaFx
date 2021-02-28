package sample.view.responsive;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.util.List;

/**
 * This class help to make responsive desktop component for all PC regulations.
 *
 * @author Mahadi Hasan Joy
 * @version 1.0
 * @since 02-09-2020
 */
public class ScreenCal {

    private Rectangle2D rectangle2D;

    public ScreenCal() {
        this.rectangle2D = Screen.getPrimary().getBounds();
    }

    public static Rectangle2D getScreenResulation() {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        return screenBounds;
    }

    public void toolbarAllignment(ToolBar toolBar) {
        toolBar.setPrefWidth(rectangle2D.getWidth());
        toolBar.setPrefHeight((rectangle2D.getHeight() * 7.5) / 100);
    }

    public void toolBarBorderPaneAllignment(BorderPane borderPane) {

        borderPane.setPrefWidth(rectangle2D.getWidth() - 14);
        List<Node> nodes = borderPane.getChildren();
        for (Node node : nodes) {
            HBox hBox = (HBox) node;
            hBox.setPrefWidth((borderPane.getPrefWidth()) / 3);
            hBox.setMaxWidth((borderPane.getPrefWidth()) / 3);
        }

    }

    public void profileVBoxAllignment(VBox profileVBox) {
        profileVBox.setPrefWidth((rectangle2D.getWidth() * 2) / 3);
        profileVBox.setPrefHeight((rectangle2D.getHeight()) * 1.1 / 2);
    }

    public void signInVBoxAllignment(VBox signInVBox, VBox logoVbox) {
        signInVBox.setPrefWidth(rectangle2D.getWidth() / 3);
        signInVBox.setPrefHeight((rectangle2D.getHeight()) * 1.1 / 2);
        logoVbox.setPrefWidth(rectangle2D.getWidth());


    }

    public void forgetPassVBoxAllignment(VBox signInVBox) {
        signInVBox.setPrefWidth((rectangle2D.getWidth() * 1.3) / 3);
        signInVBox.setPrefHeight((rectangle2D.getHeight()) / 3);
    }

    public void signUpVBoxAllignment(VBox signInVBox) {
        signInVBox.setPrefWidth((rectangle2D.getWidth() * 2) / 3);
        signInVBox.setPrefHeight(((rectangle2D.getHeight()) * 2.1) / 3);
    }

    public void profileAllignement(Pane pane, StackPane stackPane) {
        pane.setPrefWidth(rectangle2D.getWidth() - 10);
        stackPane.setPrefWidth(rectangle2D.getWidth() - 10);
        pane.setPrefHeight(rectangle2D.getHeight() - 70);

    }

    public void landingPageAllignment(AnchorPane anchorPane, ScrollPane scrollPane, GridPane gridPane) {
        anchorPane.setPrefHeight(rectangle2D.getHeight() - 149);
        anchorPane.setPrefWidth(rectangle2D.getWidth() - 1);
        scrollPane.setPrefHeight((rectangle2D.getHeight() * 2) / 3);
        gridPane.setPrefWidth(rectangle2D.getWidth());

    }

    public void splashScreenAllignment(VBox vBox) {
        vBox.setPrefWidth(rectangle2D.getWidth() - 10);
        vBox.setPrefHeight(rectangle2D.getHeight() - 70);
    }

    public void webViewAllignment(VBox rootVBox, ScrollPane scrollPane) {
        scrollPane.setPrefHeight(rectangle2D.getHeight() - 10);
        rootVBox.setPrefWidth(rectangle2D.getWidth() - 3);
        rootVBox.setPrefHeight(rectangle2D.getHeight() - 10);
    }

    public void applicationSize(ImageView imageView) {
        imageView.setFitHeight(rectangle2D.getWidth() / 13);
        imageView.setFitWidth(rectangle2D.getWidth() / 13);
    }

    public void starSize(ImageView starImage) {
        starImage.setFitHeight(rectangle2D.getWidth() / 40);
        starImage.setFitWidth(rectangle2D.getWidth() / 40);
    }

//    public void setStarMargin(StackPane gridStackPane, Button button, Button favButton) {
//        gridStackPane.setMargin(button, new Insets(10, 0, 0, 0));
//        gridStackPane.setMargin(favButton, new Insets(0, 0, rectangle2D.getWidth() / 16, rectangle2D.getWidth() / 14));
//    }

    public void changePassVbox(VBox vBox) {
        vBox.setPrefWidth(rectangle2D.getWidth() / 3);
        vBox.setPrefHeight(rectangle2D.getHeight() * 1.1 / 2);
    }

    public void splashScreenImage(ImageView imageView) {
        imageView.setX((rectangle2D.getWidth() - 10) / 2);
        imageView.setY((rectangle2D.getHeight() - 70) / 2);
        imageView.setFitHeight(rectangle2D.getWidth() / 9);
        imageView.setFitWidth(rectangle2D.getWidth() / 9);
    }

    public void signInBorderPane(BorderPane borderPane) {
        borderPane.setPrefWidth(rectangle2D.getWidth() - 10);
        borderPane.setPrefHeight(rectangle2D.getWidth() - 70);
    }

    public void booKmarksImage(ImageView bookMarkImage, Button buttonBookmarks) {
        bookMarkImage.setFitHeight(rectangle2D.getWidth() / 27);
        bookMarkImage.setFitWidth(rectangle2D.getWidth() / 27);
        buttonBookmarks.setMaxHeight(rectangle2D.getWidth() / 34);
        buttonBookmarks.setMaxWidth(rectangle2D.getWidth() / 34);
    }

    public void booKmarksCancelImage(ImageView cancelImageView) {
        cancelImageView.setFitHeight(rectangle2D.getWidth() / 68);
        cancelImageView.setFitWidth(rectangle2D.getWidth() / 68);
    }
}
