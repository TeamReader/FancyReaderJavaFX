package zz.reader.service.remoteServer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import zz.reader.constant.ServerConstant;
import zz.reader.model.BookInfo;
import zz.reader.util.JsonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zz on 2016-07-05.
 */
public class BookServer implements CommonServer {
    /**
     * 个人图书列表加载
     * @param userName
     * @return
     */
    public List<BookInfo> loadBookList(String userName){
        List<BookInfo> list = new ArrayList<>();
        try {
            String loadBookListUrl = ServerConstant.BOOK_LIST;
            loadBookListUrl = loadBookListUrl+"?"+"userName="+userName;
            String userConfigJson = requestServer(loadBookListUrl);
            list = JsonUtil.StringToEntityList(userConfigJson, BookInfo.class);

//            JSONObject jsonObject = JSON.parseObject(userConfigJson);
//            JSONArray books = jsonObject.getJSONArray("books");
//            for (Object o :
//                    books) {
//                list.add((BookInfo) o);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除书籍
     * @param bookName
     * @param userName
     */
    public boolean deleteBook(String bookName, String userName){
        boolean result = false;
        try {
            String deleteBookUrl = ServerConstant.DELETE_BOOK;
            deleteBookUrl = deleteBookUrl+"?bookName="+bookName+"&userName="+userName;
            String deleteSuccess = requestServer(deleteBookUrl);
            JSONObject jsonObject = JSON.parseObject(deleteSuccess);
            result = Boolean.valueOf((String) jsonObject.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加书籍
     * @param bookName
     * @param userName
     */
    public boolean addBook(String bookName, String userName){
        boolean result = false;
        try {
            String addBookUrl = ServerConstant.ADD_BOOK;
            addBookUrl = addBookUrl+"?bookName="+bookName+"&userName="+userName;
            String addSuccess = requestServer(addBookUrl);
            JSONObject jsonObject = JSON.parseObject(addSuccess);
            result = Boolean.valueOf((String) jsonObject.get("result"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 记录阅读位置
     * @param bookName
     * @param userName
     * @param lineNums
     */
    public void recodeIndex(String bookName, String userName, int lineNums){
        try {
            String addBookUrl = ServerConstant.RECORD_INDEX;
            addBookUrl = addBookUrl+"?bookName="+bookName+"&userName="+userName+"&lineNum="+lineNums;
            String addSuccess = requestServer(addBookUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<BookInfo> searchBook(String bookName){
        List<BookInfo> list = new ArrayList<>();
        try {
            String searchBookUrl = ServerConstant.SEARCH_BOOK;
            searchBookUrl = searchBookUrl+"?"+"bookName="+bookName;
            String userConfigJson = requestServer(searchBookUrl);
            list = JsonUtil.StringToEntityList(userConfigJson, BookInfo.class);
//            JSONObject jsonObject = JSON.parseObject(userConfigJson);
//            JSONArray books = jsonObject.;
//            for (Object o :
//                    books) {
//                list.add((BookInfo) o);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void downLoadBook(String bookName, String url){
        try {
            String s = requestServer(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
