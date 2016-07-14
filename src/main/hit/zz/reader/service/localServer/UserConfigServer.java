package zz.reader.service.localServer;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import zz.reader.model.UserConfig;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zz on 2016-07-10.
 */
public class UserConfigServer {

    private static final String GET_USERCONFIG_BY_USERNAME = "select * from userconfig where userName = ? ";

    private static final String UPDATE_USERCONFIG = "update userconfig set fontSize = ?, fontStyle = ?, fontColor = ?, bgColor = ? where userName = ?";

    private static final String INSERT_USERCONFIG = "insert into userconfig (userName, passWord, fontSize, fontStyle, fontColor, bgColor) values (?,?,?,?,?,?)";

    public static UserConfig getUserConfigByUserName(String userName){
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        UserConfig userConfig = new UserConfig();
        try {
            userConfig = queryRunner.query(connection, GET_USERCONFIG_BY_USERNAME, new BeanHandler<>(UserConfig.class),userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userConfig;
    }

    public static void updateUserConfig(UserConfig userConfig){
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            int update = queryRunner.update(
                    connection,
                    UPDATE_USERCONFIG,
                    userConfig.getFontSize(),
                    userConfig.getFontStyle(),
                    userConfig.getFontColor(),
                    userConfig.getBgColor(),
                    userConfig.getUserName()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserConfig(UserConfig userConfig){
        Connection connection = DBHelper.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(
                    connection,
                    INSERT_USERCONFIG,
                    userConfig.getUserName(),
                    userConfig.getPassWord(),
                    userConfig.getFontSize(),
                    userConfig.getFontStyle(),
                    userConfig.getFontColor(),
                    userConfig.getBgColor()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserConfig offLineLogin(String userName, String passWord){
        UserConfig userConfigByUserName = getUserConfigByUserName(userName);
        if (userConfigByUserName.getPassWord().equals(passWord)){
            return userConfigByUserName;
        }
        return null;
    }

    public static void main(String args[]){
        UserConfig zz = getUserConfigByUserName("zhangxun1");
        System.out.println(zz.getUserName());
    }
}
