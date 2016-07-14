package zz.reader.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import zz.reader.factory.ShopListCellFactory;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.model.BookInfo;
import zz.reader.service.remoteServer.BookServer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zz on 2016-07-09.
 */
public class ShopViewController {

    @FXML
    private Button refreshButton;
    @FXML
    private Button backButton;
    @FXML
    private ListView<BookInfo> shopList;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;

    private BookServer bookServer;

    private ObservableList<BookInfo> bookInfos;

    @FXML
    private void initialize(){
        bookServer = new BookServer();
        BookServer bookServer = new BookServer();
        List<BookInfo> list = bookServer.searchBook("");
        bookInfos = FXCollections.observableArrayList(list);
        shopList.setItems(bookInfos);
        shopList.setCellFactory(param -> new ShopListCellFactory());
    }


    public void handlerSearch() {
        ObservableList<BookInfo> searchResult = FXCollections.observableArrayList();
        String bookName = searchField.getText();
        searchResult.addAll(bookInfos.stream().filter(b -> b.getBookName().equals(bookName)).collect(Collectors.toList()));
        shopList.getItems().clear();
        shopList.setItems(searchResult);
    }

    public void handlerBack() {
        ViewManager.initLayout(ViewType.MAIN_VIEW);
    }

    public void handlerRefresh() {
        List<BookInfo> list = bookServer.searchBook("");
    }
}
