package zz.reader.model;

import javafx.beans.property.StringProperty;

/**
 * Created by zz on 2016-07-04.
 */
public class BookInfo {

    private StringProperty bookName;

    private StringProperty IconUrl;

    private StringProperty author;

    private StringProperty description;

    private StringProperty bookUrl;

    public String getBookName() {
        return bookName.get();
    }

    public StringProperty bookNameProperty() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }

    public String getIconUrl() {
        return IconUrl.get();
    }

    public StringProperty iconUrlProperty() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.IconUrl.set(iconUrl);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }


    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getBookUrl() {
        return bookUrl.get();
    }

    public StringProperty bookUrlProperty() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl.set(bookUrl);
    }
}
