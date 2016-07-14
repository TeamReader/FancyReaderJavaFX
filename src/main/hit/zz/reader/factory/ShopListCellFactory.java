package zz.reader.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import zz.reader.constant.ServerConstant;
import zz.reader.controller.ShopListCellViewController;
import zz.reader.model.BookInfo;
import zz.reader.util.ImageUtil;

import java.io.IOException;

/**
 * Created by zz on 2016-07-09.
 */
public class ShopListCellFactory extends ListCell<BookInfo> {
    @Override
    protected void updateItem(BookInfo bookInfo, boolean empty) {
        super.updateItem(bookInfo, empty);
        if (bookInfo == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = FxmlLoaderFactory.generateLoader(ViewType.SHOPCELL_VIEW);
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                ShopListCellViewController shopListCellViewController = fxmlLoader.getController();
                shopListCellViewController.setImage(ImageUtil.displayImage(ServerConstant.IMAGE_DOWNLOAD+bookInfo.getIcoUrl()));
                shopListCellViewController.setBookName(bookInfo.getBookName());
                shopListCellViewController.setBookAuthor(bookInfo.getBookName());
                shopListCellViewController.setBookDescription(bookInfo.getDescription());
                shopListCellViewController.setBookInfo(bookInfo);
                setGraphic(anchorPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
