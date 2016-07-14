package zz.reader.model;

/**
 * Created by zz on 2016-07-04.
 */
public class BookInfo {

    private long id;

    private String bookName;

    private String icoUrl;

    private String author;

    private String description;

    private String bookUrl;

    public BookInfo(String bookName, String icoUrl, String author, String description) {
        this.bookName = bookName;
        this.icoUrl = icoUrl;
        this.author = author;
        this.description = description;
    }

    public BookInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIcoUrl() {
        return icoUrl;
    }

    public void setIcoUrl(String icoUrl) {
        this.icoUrl = icoUrl;
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

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookInfo bookInfo = (BookInfo) o;

        return bookName.equals(bookInfo.bookName);

    }

    @Override
    public int hashCode() {
        return bookName.hashCode();
    }
}
