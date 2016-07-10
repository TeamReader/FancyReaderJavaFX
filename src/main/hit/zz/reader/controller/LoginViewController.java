package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.service.UserServer;

/**
 * Created by zz on 2016-07-05.
 */
public class LoginViewController {

    private static final String USERNAME_NULL_ERROR = "用户名不能为空";

    private static final String PASSWORD_NULL_ERROR = "密码不能为空";


    @FXML
    private Label loginErrorLable;

    @FXML
    private Label userNameErrorLable;

    @FXML
    private Label passWordErrorLable;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passWordField;

    @FXML
    private Button signInButton;

    @FXML
    private Button registerButton;

    @FXML
    private void handleLogin() {
        String userName = userNameField.getText();
        String passWord = passWordField.getText();
        if (valueIsOk(userName, passWord)) {
            UserServer userServer = new UserServer();
//            boolean loginSuccess = userServer.login(userName, passWord);
            if (true) {
                initMainLayout();
            } else {
                loginErrorLable.setVisible(true);
            }
        }
    }

    @FXML
    private void handleRegister() {
        initRegisterLayout();
    }



    private void initRegisterLayout(){
        ViewManager.initLayout(ViewType.REGISTER_VIEW,AnchorPane.class);
    }


    private void initMainLayout() {
        ViewManager.initLayout(ViewType.MAIN_VIEW,AnchorPane.class);
    }


    private boolean valueIsOk(String userName, String passWord) {
        boolean isOk = true;

        if (userName.equals("")) {
            isOk = false;
            userNameErrorLable.setText(USERNAME_NULL_ERROR);
            userNameErrorLable.setVisible(true);
        }else {
            userNameErrorLable.setVisible(false);
        }

        if (passWord.equals("")) {
            isOk = false;
            passWordErrorLable.setText(PASSWORD_NULL_ERROR);
            passWordErrorLable.setVisible(true);
        }else {
            passWordErrorLable.setVisible(false);
        }

        return isOk;
    }
}
