package sample.view.responsive;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        borderPane.setPrefWidth(rectangle2D.getWidth()-14);
        List<Node> nodes = borderPane.getChildren();
        for (Node node : nodes) {
            HBox hBox = (HBox) node;
            hBox.setPrefWidth((borderPane.getPrefWidth())/ 3);
            hBox.setMaxWidth((borderPane.getPrefWidth())/ 3);
            System.out.println(hBox.getPrefWidth());
//            hBox.setStyle("-fx-border-color: white");
        }

    }

    public void profileVBoxAllignment(VBox profileVBox)
    {
        profileVBox.setPrefWidth(rectangle2D.getWidth()-400);
        profileVBox.setPrefHeight(rectangle2D.getHeight()-400);
    }
}
