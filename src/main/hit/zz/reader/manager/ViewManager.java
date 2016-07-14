package zz.reader.manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import zz.reader.constant.ClientConstant;
import zz.reader.controller.SettingViewController;
import zz.reader.factory.FxmlLoaderFactory;
import zz.reader.factory.ViewType;

import java.io.IOException;

/**
 * Created by zz on 2016-07-09.
 */
public class ViewManager{

    public  static <T extends Pane> void initLayout(ViewType viewType){
        try {
            FXMLLoader fxmlLoader = FxmlLoaderFactory.generateLoader(viewType);
            AnchorPane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            ClientConstant.primaryStage.setScene(scene);
            ClientConstant.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Pane> void initDialog(ViewType viewType){
        try {
            FXMLLoader fxmlLoader = FxmlLoaderFactory.generateLoader(viewType);
            T pane = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("个人设置");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(ClientConstant.primaryStage);
            stage.setResizable(false);
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            SettingViewController controller = fxmlLoader.getController();
            controller.setDialogStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
