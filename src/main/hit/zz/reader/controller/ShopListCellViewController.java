package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import zz.reader.constant.ClientConstant;
import zz.reader.model.BookInfo;
import zz.reader.service.localServer.LocalBookServer;
import zz.reader.service.remoteServer.BookServer;
import zz.reader.service.remoteServer.DownLoadService;

/**
 * Created by zz on 2016-07-09.
 */
public class ShopListCellViewController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label bookName;
    @FXML
    private Label author;
    @FXML
    private Label description;
    @FXML
    private Button downLoadButton;

    private BookInfo bookInfo;

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    @FXML
    public void handlerDownLoad() {
        DownLoadService downLoadService = new DownLoadService();

        downLoadService.setOnSucceeded(event -> {
            BookServer bookServer = new BookServer();
            bookServer.addBook(bookInfo.getBookName(),ClientConstant.getNowUser().getUserName());
            LocalBookServer.addBook(bookInfo);

            ClientConstant.getBookInfoData().add(bookInfo);
        });

        downLoadService.setBookInfo(bookInfo);
        downLoadService.setOnFailed(event ->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
        });
        downLoadService.start();
    }

    public Image getImageView() {
        return imageView.getImage();
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public String getBookName() {
        return bookName.getText();
    }

    public void setBookName(String bookName) {
        this.bookName.setText(bookName);
    }

    public String getBookAuthor() {
        return author.getText();
    }

    public void setBookAuthor(String bookAuthor) {
        this.author.setText(bookAuthor);
    }

    public String getBookDescription() {
        return description.getText();
    }

    public void setBookDescription(String bookDescription) {
        this.description.setText(bookDescription);
    }

    public Button getDownLoadButton() {
        return downLoadButton;
    }

    public void setDownLoadButton(Button downLoadButton) {
        this.downLoadButton = downLoadButton;
    }
}
