package zz.reader.factory;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import zz.reader.controller.ListCellViewController;
import zz.reader.util.ImageUtil;

import java.io.IOException;

/**
 * Created by zz on 2016-07-08.
 */
public class BookListCellFactory extends ListCell<String> {


    @Override
    protected void updateItem(String bookName, boolean empty) {
        super.updateItem(bookName, empty);
        if (bookName == null || empty) {
            setText(null);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/zz/reader/view/listCell.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                ListCellViewController listCellViewController = fxmlLoader.getController();
                listCellViewController.setImage(ImageUtil.get(bookName));
                listCellViewController.setBookName(bookName);
                listCellViewController.setBookDescription("this is 描述");
                setGraphic(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
