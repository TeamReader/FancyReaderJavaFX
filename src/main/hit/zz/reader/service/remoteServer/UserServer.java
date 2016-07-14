package zz.reader.service.remoteServer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import zz.reader.constant.ServerConstant;
import zz.reader.model.UserConfig;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by zz on 2016-07-05.
 */
public class UserServer implements CommonServer {
    /**
     * 登陆
     * @param userName
     * @param passWord
     * @return
     */
    public boolean login(String userName, String passWord){
        try {
            String loginUrl = ServerConstant.LOGIN_URL;
            String url = loginUrl + "?" + "userName=" + userName +
                    "&" + "password=" + passWord;
            String s = requestServer(url);
            JSONObject jsonObject = JSON.parseObject(s);
            String result = (String) jsonObject.get("result");
            return Boolean.valueOf(result);
        } catch (SocketTimeoutException s){
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取个人设置
     * @param userName
     * @return
     */
    public UserConfig getPreference(String userName){
        UserConfig userConfig = new UserConfig();
        try {
            String getUserPreferenceUrl = ServerConstant.GET_USER_PREFERENCE;
            getUserPreferenceUrl = getUserPreferenceUrl+"?"+"userName="+userName;
            String userConfigJson = requestServer(getUserPreferenceUrl);
            JSONObject jsonObject = JSON.parseObject(userConfigJson);
            userConfig = new UserConfig.Builder((String)jsonObject.get("userName"),(String)jsonObject.get("password")).
                    fontSize((String) jsonObject.get("fontsizeFx"))
                    .fontColor((String) jsonObject.get("fontcolorFx"))
                    .fontStyle((String) jsonObject.get("fontstyleFx"))
                    .bgColor((String) jsonObject.get("bgFx")).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userConfig;
    }

    /**
     * 注册
     * @param userName
     * @param passWord
     * @return
     */
    public boolean register(String userName, String passWord){
        boolean result = false;
        try {
            String updateConfigUrl = ServerConstant.REGISTER_URL;
            updateConfigUrl = updateConfigUrl+"?"+"userName="+userName+"&"+"password="+passWord;
            String userConfigJson = requestServer(updateConfigUrl);
            JSONObject jsonObject = JSON.parseObject(userConfigJson);
            result = Boolean.valueOf((String) jsonObject.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public boolean pushPerference(UserConfig userConfig){
        boolean result = false;
        try {
            String updateConfigUrl = ServerConstant.UPDATE_USER_PREFERENCE;
            updateConfigUrl = updateConfigUrl+"?"
                    +"userName="+userConfig.getUserName()
                    +"&"
                    +"password="+userConfig.getPassWord()
                    +"&"
                    +"fontstyleApp="+userConfig.getFontStyle()
                    +"&"
                    +"fontsizeApp="+userConfig.getFontSize()
                    +"&"
                    +"fontcolorApp="+userConfig.getFontColor()
                    +"&"
                    +"bgApp="+userConfig.getBgColor()
                    +"&"
                    +"fontstyleFx="+userConfig.getFontStyle()
                    +"&"
                    +"fontsizeFx="+userConfig.getFontSize()
                    +"&"
                    +"fontcolorFx="+userConfig.getFontColor()
                    +"&"
                    +"bgFx="+userConfig.getBgColor();
            updateConfigUrl = StringUtils.replace(updateConfigUrl, " ", "%20");
            updateConfigUrl = StringUtils.replace(updateConfigUrl, "#", "%23");
            String userConfigJson = requestServer(updateConfigUrl);
            JSONObject jsonObject = JSON.parseObject(userConfigJson);
            result = Boolean.valueOf((String) jsonObject.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }






}
