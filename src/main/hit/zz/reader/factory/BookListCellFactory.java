package zz.reader.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import zz.reader.controller.ListCellViewController;
import zz.reader.model.BookInfo;
import zz.reader.util.ImageUtil;

import java.io.IOException;

/**
 * Created by zz on 2016-07-08.
 */
public class BookListCellFactory extends ListCell<BookInfo> {


    @Override
    protected void updateItem(BookInfo bookInfo, boolean empty) {
        super.updateItem(bookInfo, empty);
        if (bookInfo == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader fxmlLoader = FxmlLoaderFactory.generateLoader(ViewType.LISTCELL_VIEW);
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                ListCellViewController listCellViewController = fxmlLoader.getController();
                listCellViewController.setImage(ImageUtil.getImageFile(bookInfo.getBookName()));
                listCellViewController.setBookName(bookInfo.getBookName());
                listCellViewController.setAuthor(bookInfo.getBookName());
                setGraphic(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
