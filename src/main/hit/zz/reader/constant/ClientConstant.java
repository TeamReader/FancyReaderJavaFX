package zz.reader.constant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import zz.reader.model.BookInfo;
import zz.reader.model.UserConfig;

import java.util.List;

/**
 * Created by zz on 2016-07-05.
 */
public class ClientConstant {

    public static final String DEFAULT_FONT_SIZE = "12";

    public static final String DEFAULT_FONT_STYLE = "Microsoft YaHei UI";

    public static final String DEFAULT_FONT_COLOR = "#000000ff";

    public static final String DEFAULT_BG_COLOR = "#ffffffff";

    private static ObservableList<BookInfo> bookInfoData;

    public static Stage primaryStage;

    private static UserConfig nowUser;

    public static String nowReadBookName;

    private static boolean hasInit = false;

    public static void init(Stage primaryStage){
        if (!hasInit){
            ClientConstant.primaryStage = primaryStage;
        }
    }

    public static void initContent(List<BookInfo> bookInfos){
        bookInfoData = FXCollections.observableArrayList(bookInfos);
    }

    public static void setNowUser(UserConfig nowUser) {
        ClientConstant.nowUser = nowUser;
    }

    public static UserConfig getNowUser() {
        return nowUser;
    }

    public static ObservableList<BookInfo> getBookInfoData() {
        return bookInfoData;
    }
}
