package zz.reader.constant;

import javafx.stage.Stage;

/**
 * Created by zz on 2016-07-05.
 */
public class ClientConstant {

//    private ObservableList<BookInfo> bookInfoData = FXCollections.observableArrayList();

    public static Stage primaryStage;


    private static boolean hasInit = false;

    public static void init(Stage primaryStage){
        if (!hasInit){
            ClientConstant.primaryStage = primaryStage;
        }
    }

}
