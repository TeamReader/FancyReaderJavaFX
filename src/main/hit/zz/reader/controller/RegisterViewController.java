package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.model.UserConfig;
import zz.reader.service.localServer.UserConfigServer;
import zz.reader.service.remoteServer.UserServer;

import java.util.regex.Pattern;

/**
 * Created by zz on 2016-07-07.
 */
public class RegisterViewController {

    private final static String USERNAME_NULL_ERROR = "用户名不能为空";

    private final static String PASSWORD_NULL_ERROR = "密码不能为空";

    private final static String SEC_PASSWORD_NULL_ERROR = "请确认密码";

    private final static String PASSWORD_LENGTH_ERROR = "密码长度不小于6位";

    private final static String USER_PATTERN_ERROR = "用户名格式错误";

    private final static String REGISTER_FAIL = "用户注册失败";

    private final static String SEC_PASSWORD_NOT_RIGHT = "两次密码不同";

    private final static String USERNAME_PATTERN = "[a-z|A-z]+[a-zA-z0-9|_]*";
    @FXML
    private Label userNameErrorLabel;

    @FXML
    private Label registerErrorLabel;

    @FXML
    private Label secPassWordErrorLabel;

    @FXML
    private Label passWordErrorLabel;

    @FXML
    private PasswordField secPassWordField;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Button registerButton;

    @FXML
    private void initialize(){

    }

    @FXML
    private void handlerRegister(){
        registerErrorLabel.setVisible(false);
        String userName = userNameField.getText();
        String passWord= passWordField.getText();
        String secPassWord = secPassWordField.getText();
        if (valueIsOk(userName,passWord,secPassWord)){
            UserServer userServer = new UserServer();
            boolean registerSuccess = userServer.register(userName, passWord);
            if (registerSuccess){
                UserConfig userConfig = new UserConfig.Builder(userName, passWord)
                        .fontSize(ClientConstant.DEFAULT_FONT_SIZE)
                        .fontStyle(ClientConstant.DEFAULT_FONT_STYLE)
                        .fontColor(ClientConstant.DEFAULT_FONT_COLOR)
                        .bgColor(ClientConstant.DEFAULT_BG_COLOR)
                        .build();
                if (userServer.pushPerference(userConfig)){
                    UserConfigServer.addUserConfig(userConfig);
                }else {
                    // TODO: 2016-07-13 设置同步失败
                }
                initLoginLayout();
            }else {
                // TODO: 2016-07-09 error 注册失败
                registerErrorLabel.setVisible(true);
            }
        }
    }

    private void initLoginLayout(){
        ViewManager.initLayout(ViewType.LOGIN_VIEW);
    }



    private boolean valueIsOk(String userName, String passWord, String secPassWord){
        boolean isOk = true;

        if (userName.equals("")){
            isOk = false;
            userNameErrorLabel.setText(USERNAME_NULL_ERROR);
            userNameErrorLabel.setVisible(true);
        }else if (!Pattern.compile(USERNAME_PATTERN).matcher(userName).matches()){
            isOk = false;
            userNameErrorLabel.setText(USER_PATTERN_ERROR);
            userNameErrorLabel.setVisible(true);
        }else userNameErrorLabel.setVisible(false);


        if (passWord.equals("")){
            isOk = false;
            passWordErrorLabel.setText(PASSWORD_NULL_ERROR);
            passWordErrorLabel.setVisible(true);
        }else if (!(passWord.length()>6)){
            isOk = false;
            passWordErrorLabel.setText(PASSWORD_LENGTH_ERROR);
            passWordErrorLabel.setVisible(true);
        }else passWordErrorLabel.setVisible(false);

        if (secPassWord.equals("")){
            isOk = false;
            secPassWordErrorLabel.setText(SEC_PASSWORD_NULL_ERROR);
            secPassWordErrorLabel.setVisible(true);
        }else if ((!passWord.equals(secPassWord))){
            isOk = false;
            secPassWordErrorLabel.setText(SEC_PASSWORD_NOT_RIGHT);
            secPassWordErrorLabel.setVisible(true);
        }else secPassWordErrorLabel.setVisible(false);

        return isOk;
    }
}
