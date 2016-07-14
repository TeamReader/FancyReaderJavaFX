package zz.reader.service.localServer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import zz.reader.constant.ClientConstant;
import zz.reader.model.BookInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zz on 2016-07-10.
 */
public class LocalBookServer {

    public static BookInfo getBookInfoById() {
        return null;
    }

    public static void deleteBookById() {

    }

    public static void addBook(BookInfo bookInfo) {
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into local_book_list(bookName, author,description,icoUrl,bookUrl,userName) values(?,?,?,?,?,?)";
        try {
            int update = queryRunner.update(
                    connection,
                    sql,
                    bookInfo.getBookName(),
                    bookInfo.getAuthor(),
                    bookInfo.getDescription(),
                    bookInfo.getIcoUrl(),
                    bookInfo.getBookUrl(),
                    ClientConstant.getNowUser().getUserName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<BookInfo> getAllBookByUserName(String userName) {
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from local_book_list where userName = ? ";
        List<BookInfo> list = null;
        try {
            list = queryRunner.query(connection, sql, new BeanListHandler<BookInfo>(BookInfo.class), userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String args[]) {
        List<BookInfo> zz = getAllBookByUserName("zz");
        for (BookInfo b :
                zz) {
            System.out.println(b.getAuthor());
        }
//        ClientConstant.setNowUser(new UserConfig("zz","zzz"));
//        addBook(new BookInfo("new","new","new","new"));
    }

}
