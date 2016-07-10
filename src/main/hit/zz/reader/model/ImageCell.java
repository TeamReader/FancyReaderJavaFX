package zz.reader.model;

import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by zz on 2016-07-05.
 */
public class ImageCell extends TableCell<BookInfo,String> {

    private ImageView imageView;

    public ImageCell(){}

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty){
            setText(null);
            setGraphic(null);
        }else {
            imageView = new ImageView(new Image(item));
            setGraphic(imageView);
        }
    }
}
