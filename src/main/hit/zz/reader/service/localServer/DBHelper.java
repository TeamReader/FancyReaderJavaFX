package zz.reader.service.localServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zz on 2016-07-10.
 */
public class DBHelper {
    private final static String url = "jdbc:mysql://localhost:3306/reader";
    private final static String user = "root";
    private final static String password = "root";
    private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            // TODO: 2016-07-10
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
