package zz.reader.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import zz.reader.constant.ServerConstant;
import zz.reader.model.UserConfig;
import zz.reader.util.JsonUtil;

import java.io.IOException;

/**
 * Created by zz on 2016-07-05.
 */
public class UserServer implements CommonServer {

    public boolean login(String userName, String passWord){
        try {
            String loginUrl = ServerConstant.LOGIN_URL;
            String url = loginUrl + "?" + "userName=" + userName +
                    "&" + "password=" + passWord;
            String s = requestServer(url);
            JSONObject jsonObject = JSON.parseObject(s);
            String result = (String) jsonObject.get("result");
            return Boolean.valueOf(result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserConfig updateConfig(String userName){
        UserConfig userConfig = new UserConfig();
        try {
            String updateConfigUrl = ServerConstant.UPDATE_CONFIG_URL;
            updateConfigUrl = updateConfigUrl+"?"+"userName="+userName;
            String userConfigJson = requestServer(updateConfigUrl);
            userConfig = JsonUtil.StringToEntity(userConfigJson, UserConfig.class);
            return userConfig;
        } catch (IOException e) {
            e.printStackTrace();
            return userConfig;
        }
    }

    public boolean register(String userName, String passWord){
        // TODO: 2016-07-09
        return false;

    }

}
