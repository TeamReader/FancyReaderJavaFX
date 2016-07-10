package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;

/**
 * Created by zz on 2016-07-08.
 */
public class MainViewController {

    @FXML private ImageView bookImageView;

    @FXML private Label basicInfoLable;

    @FXML private Label bookName;

    @FXML private Label bookAuthor;

    @FXML private Label bookDescription;

    @FXML private MenuItem addLocalFile;

    @FXML private MenuItem openLocalFile;

    @FXML private MenuItem searchCloudBook;

    @FXML private MenuItem managerDownLoad;

    @FXML private MenuItem userSetting;

    @FXML private ListView bookList;

    @FXML
    private void initialize(){
        bookImageView.setVisible(false);
        basicInfoLable.setVisible(false);
        bookName.setVisible(false);
        bookAuthor.setVisible(false);
        bookDescription.setVisible(false);

        bookList.setOnMouseClicked(event -> {

        });
    }

    @FXML
    public void openShop() {
        ViewManager.initLayout(ViewType.SHOP_VIEW, AnchorPane.class);
    }

    @FXML
    public void userConfig() {

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

}
