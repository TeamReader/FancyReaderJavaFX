package zz.reader.application;

/**
 * Created by zz on 2016-07-05.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        ClientConstant.init(primaryStage);
        primaryStage.setTitle("TeamReader");
        initLoginLayout();
    }

    private void initLoginLayout() {
        ViewManager.initLayout(ViewType.LOGIN_VIEW);
//        ViewManager.initLayout(ViewType.READER_VIEW);
//        ViewManager.initLayout(ViewType.SHOP_VIEW);
//        ViewManager.initLayout(ViewType.MAIN_VIEW);
    }
}
