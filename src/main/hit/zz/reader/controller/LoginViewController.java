package zz.reader.controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.model.BookInfo;
import zz.reader.model.UserConfig;
import zz.reader.service.localServer.LocalBookServer;
import zz.reader.service.localServer.UserConfigServer;
import zz.reader.service.remoteServer.UserServer;

import java.util.List;

/**
 * Created by zz on 2016-07-05.
 */
public class LoginViewController {

    private static final String USERNAME_NULL_ERROR = "用户名不能为空";

    private static final String PASSWORD_NULL_ERROR = "密码不能为空";

    private static final String SYNC_USERINFO_ING = "正在同步用户数据...";

    private static final String SYNC_USERIFFO_FAILED = "用户数据同步失败";
    @FXML
    private Label userInfoSyncing;


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
            Task<Boolean> loginTask = new Task<Boolean>() {
                @Override
                protected Boolean call() throws Exception {
                    UserServer userServer = new UserServer();
                    return userServer.login(userName, passWord);
                }
                @Override
                protected void succeeded() {
                    if (this.getValue()) {
                        ClientConstant.setNowUser(new UserConfig(userName,passWord));
                        initLoginSuccess(userName);
                    }else {
                        UserConfig userConfig = UserConfigServer.offLineLogin(userName, passWord);
                        if (userConfig != null){
                            initLoginOffLine(userConfig);
                            initMainLayout();
                        }else{
                            loginFail();
                        }
                    }
                }
            };
            loginTask.run();
//            // TODO: 2016-07-10
//            if (loginTask.getValue()) {
//                ClientConstant.setNowUser(new UserConfig(userName,passWord));
//                initLoginSuccess(userName);
//            } else {
//                //离线
//                UserConfig userConfig = UserConfigServer.offLineLogin(userName, passWord);
//                if (userConfig != null){
//                    initLoginOffLine(userConfig);
//                    initMainLayout();
//                }else{
//                    loginFail();
//                }
//            }
        }
    }


    private class SyncUserInfoService extends Service{
        private String userName;

        public  void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        protected void succeeded() {
            initMainLayout();
        }

        @Override
        protected void failed() {
            super.failed();
            userInfoSyncing.setText(SYNC_USERIFFO_FAILED);
            ClientConstant.primaryStage.getScene().getRoot().setDisable(false);
        }

        @Override
        protected void running() {
            super.running();
            ClientConstant.primaryStage.getScene().getRoot().setDisable(true);
            userInfoSyncing.setText(SYNC_USERINFO_ING);
            userInfoSyncing.setVisible(true);
        }

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void  call() throws Exception {
                    List<BookInfo> localBookList = LocalBookServer.getAllBookByUserName(userName);
                    ClientConstant.initContent(localBookList);


                    boolean hasChanged = false;
                    UserServer userServer = new UserServer();
                    UserConfig remoteUserConfig = userServer.getPreference(userName);
                    UserConfig localUserConfig = UserConfigServer.getUserConfigByUserName(userName);

                    if (!remoteUserConfig.getFontSize().equals(localUserConfig.getFontSize())){
                        localUserConfig.setFontSize(remoteUserConfig.getFontSize());
                        hasChanged = true;
                    }
                    if (!remoteUserConfig.getFontColor().equals(localUserConfig.getFontColor())){
                        localUserConfig.setFontColor(remoteUserConfig.getFontColor());
                        hasChanged = true;
                    }
                    if (!remoteUserConfig.getFontStyle().equals(localUserConfig.getFontStyle())){
                        localUserConfig.setFontStyle(remoteUserConfig.getFontStyle());
                        hasChanged = true;
                    }
                    if (!remoteUserConfig.getBgColor().equals(localUserConfig.getBgColor())){
                        localUserConfig.setBgColor(remoteUserConfig.getBgColor());
                        hasChanged = true;
                    }
                    if (hasChanged){
                        UserConfigServer.updateUserConfig(localUserConfig);
                    }
                    ClientConstant.setNowUser(localUserConfig);
                    return null;
                }
            };
        }
    }

    private void initLoginSuccess(String userName) {
        SyncUserInfoService syncUserInfoService = new SyncUserInfoService();
        syncUserInfoService.setUserName(userName);
        syncUserInfoService.start();
    }

    private void initLoginOffLine(UserConfig userConfig){
        List<BookInfo> allBookByUserName = LocalBookServer.getAllBookByUserName(userConfig.getUserName());
        ClientConstant.initContent(allBookByUserName);
        ClientConstant.setNowUser(userConfig);
    }



    @FXML
    private void handleRegister() {
        initRegisterLayout();
    }



    private void initRegisterLayout(){
        ViewManager.initLayout(ViewType.REGISTER_VIEW);
    }


    private void initMainLayout() {
        ViewManager.initLayout(ViewType.MAIN_VIEW);
    }

    private void loginFail(){
        loginErrorLable.setVisible(true);
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
