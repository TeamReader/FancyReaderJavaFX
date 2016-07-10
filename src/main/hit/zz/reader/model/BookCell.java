package zz.reader.model;

import javafx.scene.image.Image;

/**
 * Created by zz on 2016-07-08.
 */
public class BookCell {

    private Image image;

    private String bookName;

    private String author;

    private String description;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
