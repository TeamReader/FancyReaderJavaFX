package zz.reader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by zz on 2016-07-09.
 */
public class ShopListCellViewController {
    @FXML
    private ImageView imageView;
    @FXML
    private Label bookName;
    @FXML
    private Label bookAuthor;
    @FXML
    private Label bookDescription;
    @FXML
    private Button downLoadButton;


    public void handlerDownLoad(ActionEvent actionEvent) {

    }
}
