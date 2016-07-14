package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;

/**
 * Created by zz on 2016-07-08.
 */
public class ListCellViewController{

    @FXML
    private ImageView imageView;

    @FXML
    private Label bookName;

    @FXML
    private Label author;

    public ListCellViewController() {

    }

    public void deleteBook(){

    }

    @FXML
    public void initialize(){
        // TODO: 2016-07-10
        System.out.println("initialize");
    }

    public Image getImage() {
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

    public Label getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.setText(author);
    }

    public void handlerClick(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getClickCount());
        if (mouseEvent.getClickCount() >= 2){
            ClientConstant.nowReadBookName = bookName.getText();
            ViewManager.initLayout(ViewType.READER_VIEW);
        }
    }
}
