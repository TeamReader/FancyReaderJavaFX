package zz.reader.service.localServer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import zz.reader.constant.ClientConstant;
import zz.reader.model.BookInfo;
import zz.reader.model.ReadIndex;

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

    public static void deleteBook(BookInfo bookInfo) {
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "delete from local_book_list where id = ?";
        try {
            int update = queryRunner.update(
                    connection,
                    sql,
                    bookInfo.getId()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ReadIndex getReadIndex(String bookName){
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * from readindex where userName = ? and bookName = ?";
        ReadIndex readIndex = new ReadIndex();
        try {
            readIndex = queryRunner.query(
                    connection,
                    sql,
                    new BeanHandler<ReadIndex>(ReadIndex.class),
                    ClientConstant.getNowUser().getUserName(),
                    bookName
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  readIndex;
    }

    public static void addReadIndex(int lineNum, String bookName){
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String update = "update readindex set lineNum = ? where userName = ? and bookName = ?";
        try {
            int updateResult = queryRunner.update(
                    connection,
                    update,
                    lineNum,
                    ClientConstant.getNowUser().getUserName(),
                    bookName
            );
            if (updateResult!=1){
                String insert = "insert into readindex(bookName,lineNum,userName) values(?,?,?)";
                int insertResult = queryRunner.update(
                        connection,
                        insert,
                        bookName,
                        lineNum,
                        ClientConstant.getNowUser().getUserName()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
