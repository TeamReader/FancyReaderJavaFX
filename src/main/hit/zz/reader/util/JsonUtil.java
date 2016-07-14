package zz.reader.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2016-07-06.
 */
public class JsonUtil {
    /**
     * 字符串转换为实体类
     *
     */
    public static <T> T StringToEntity(String str, Class<T> classT) {
        T t = JSON.parseObject(str, classT);
        return t;
    }

    /**
     * 字符串转换为实体集合
     *
     */
    public static <T> ArrayList<T> StringToEntityList(String str, Class<T> classT) {
        List<T> lst = JSON.parseArray(str, classT);
        return (ArrayList<T>) lst;
    }

    /**
     * 实体类转换为字符串
     *
     */
    public static <T> String EntityToString(T data) {
        String str = JSON.toJSONString(data);
        return str;
    }

    public static JSONObject readJsonFile(File file){
        String s = readFile(file);
        return JSON.parseObject(s);
    }

    private static String readFile(File file){
        StringBuilder fileContent = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = bufferedReader.readLine()) != null){
                fileContent.append(tempString);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }






}
