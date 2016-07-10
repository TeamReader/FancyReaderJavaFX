package main.hit.zz.reader; /**
 * Created by zz on 2016-07-03.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Text extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        DropShadow dropShadow = new DropShadow();
        Label label = new Label("asdfadsf");
        label.setOnMouseEntered(event -> {
            label.setEffect(dropShadow);
            label.setScaleX(1.5);
            label.setScaleY(1.5);
        });
        label.setOnMouseExited(event -> {
            label.setScaleX(1);
            label.setScaleY(1);
            label.setEffect(null);
        });
        Scene scene = new Scene(gridPane,500,200);
        gridPane.add(label,1,1);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
