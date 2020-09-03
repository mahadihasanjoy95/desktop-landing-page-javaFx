package sample.view.responsive;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;

import java.util.List;

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
        profileVBox.setPrefHeight((rectangle2D.getHeight()) / 2);
    }

    public void signInVBoxAllignment(VBox signInVBox) {
        signInVBox.setPrefWidth(rectangle2D.getWidth() / 3);
        signInVBox.setPrefHeight((rectangle2D.getHeight()) / 2);
    }

    public void forgetPassVBoxAllignment(VBox signInVBox) {
        signInVBox.setPrefWidth(rectangle2D.getWidth() / 3);
        signInVBox.setPrefHeight((rectangle2D.getHeight()) / 3);
    }

    public void signUpVBoxAllignment(VBox signInVBox) {
        signInVBox.setPrefWidth((rectangle2D.getWidth()*2) / 3);
        signInVBox.setPrefHeight(((rectangle2D.getHeight())*2.2) / 3);
    }

    public void profileAllignement(Pane pane, StackPane stackPane) {
        pane.setPrefWidth(rectangle2D.getWidth() - 10);
        stackPane.setPrefWidth(rectangle2D.getWidth() - 10);
        pane.setPrefHeight(rectangle2D.getHeight() - 70);

    }

    public void landingPageAllignment(AnchorPane anchorPane, ScrollPane scrollPane, GridPane gridPane) {
        anchorPane.setPrefHeight(rectangle2D.getHeight() - 149);
        anchorPane.setPrefWidth(rectangle2D.getWidth() - 1);
        scrollPane.setPrefHeight(rectangle2D.getHeight() - 300);
        gridPane.setPrefWidth(rectangle2D.getWidth());

    }

    public void splashScreenAllignment(VBox vBox)
    {
        vBox.setPrefWidth(rectangle2D.getWidth() - 10);
        vBox.setPrefHeight(rectangle2D.getHeight() - 70);
    }
    public void webViewAllignment(VBox rootVBox, ScrollPane scrollPane){
        scrollPane.setPrefHeight(rectangle2D.getHeight() - 10);
        rootVBox.setPrefWidth(rectangle2D.getWidth() - 3);
        rootVBox.setPrefHeight(rectangle2D.getHeight() - 10);
    }

    public void applicationSize(ImageView imageView){
        imageView.setFitHeight(rectangle2D.getWidth()/13);
        imageView.setFitWidth(rectangle2D.getWidth()/13);
    }
    public void starSize(ImageView appImage, ImageView starImage){
        starImage.setFitHeight(appImage.getFitHeight()/4);
        starImage.setFitWidth(appImage.getFitHeight()/4);
    }
    public void setStarMargin(StackPane gridStackPane, Button button, Button favButton){
        gridStackPane.setMargin(button, new Insets(10, 0, 0, 0));
        gridStackPane.setMargin(favButton, new Insets(0, 0, rectangle2D.getWidth()/16, rectangle2D.getWidth()/16));
    }


}
