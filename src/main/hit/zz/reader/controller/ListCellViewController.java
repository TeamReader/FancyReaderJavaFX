package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by zz on 2016-07-08.
 */
public class ListCellViewController{

    @FXML
    private ImageView imageView;

    @FXML
    private Label bookName;

    @FXML
    private Label bookDescription;

    @FXML
    private Label bookAuthor;

    public ListCellViewController() {

    }

    public void deleteBook(){

    }

    @FXML
    public void initialize(){
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

    public String getBookDescription() {
       return bookDescription.getText();
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription.setText(bookDescription);
    }
}
