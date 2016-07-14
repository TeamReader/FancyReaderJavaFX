package zz.reader.manager;

import com.alibaba.fastjson.JSONObject;
import zz.reader.constant.UserConfigType;
import zz.reader.model.UserConfig;
import zz.reader.util.JsonUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by zz on 2016-07-10.
 */
public class UserManager {

    private static void loadUserConfig(String userName, String passWord){
        UserConfig userConfig = new UserConfig();
        File file = new File(UserManager.class.getResource("/config/"+userName+".json").getFile());
        if (file.exists()){
            JSONObject jsonObject = JsonUtil.readJsonFile(file);
            String fontStyle = (String) jsonObject.get(UserConfigType.FONT_STYLE.getDescription());
            userConfig.setFontStyle(fontStyle);
            String fontColor = (String) jsonObject.get(UserConfigType.FONT_COLOR.getDescription());
            userConfig.setFontColor(fontColor);
            String fontSize = (String) jsonObject.get(UserConfigType.FONT_SIZE.getDescription());
            userConfig.setFontSize(fontSize);
            String bgColor = (String) jsonObject.get(UserConfigType.BG_COLOR.getDescription());
            userConfig.setBgColor(bgColor);
        }else {
            try {
                boolean newFile = file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
//            file.
        }
    }

    public static void main(String args[]){

    }
}
