package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.BookListCellFactory;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.model.BookInfo;
import zz.reader.util.ImageUtil;

/**
 * Created by zz on 2016-07-08.
 */
public class MainViewController {

    @FXML private ImageView bookImageView;

    @FXML private Label basicInfoLable;

    @FXML private Label bookName;

    @FXML private Label author;

    @FXML private Label description;

    @FXML private MenuItem addLocalFile;

    @FXML private MenuItem openLocalFile;

    @FXML private MenuItem searchCloudBook;

    @FXML private MenuItem managerDownLoad;

    @FXML private MenuItem userSetting;

    @FXML private ListView<BookInfo> bookList;

    @FXML
    private void initialize(){

        bookList.setItems(ClientConstant.getBookInfoData());
        bookList.setCellFactory(bookInfo -> new BookListCellFactory());

        bookImageView.setVisible(false);
        basicInfoLable.setVisible(false);
        bookName.setVisible(false);
        author.setVisible(false);
        description.setVisible(false);

//        bookList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                if (event.getClickCount() == 2){
//
//                }
//            }
//        });

    }

    @FXML
    public void openShop() {
        ViewManager.initLayout(ViewType.SHOP_VIEW);
    }

    @FXML
    public void userConfig() {
        ViewManager.initDialog(ViewType.SETTING_VIEW);
    }

    @FXML
    public void downLoadManager() {

    }

    @FXML
    public void addLocalFile() {

    }

    @FXML
    public void openLocalFile() {

    }

    @FXML
    public void handlerClick() {
        BookInfo selectedItem = bookList.getSelectionModel().getSelectedItem();
        if (selectedItem!=null) {
            bookImageView.setImage(ImageUtil.getImageFile(selectedItem.getBookName()));
            description.setText(selectedItem.getDescription());
            author.setText(selectedItem.getAuthor());
            bookName.setText(selectedItem.getBookName());

            basicInfoLable.setVisible(true);
            bookImageView.setVisible(true);
            bookName.setVisible(true);
            description.setVisible(true);
            author.setVisible(true);
        }
    }
}
