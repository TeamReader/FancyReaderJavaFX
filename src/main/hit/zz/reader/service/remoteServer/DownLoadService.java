package zz.reader.service.remoteServer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import org.apache.commons.lang.StringUtils;
import zz.reader.constant.ServerConstant;
import zz.reader.model.BookInfo;
import zz.reader.service.localServer.LocalBookServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zz on 2016-07-12.
 */
public class DownLoadService extends Service<Void>{

    private ProgressBar mProgressBar;

    private BookInfo bookInfo;

    /** 如何处理书的问题
     *  首先用户从商店下载书籍放到本地文件夹book中，然后将这本书的名字放入该用户的数据库中
     *      如果用户想要下载的书籍，本地已经存在，那么不下载，只是将这本书放入用户的数据库中
     *  用户登录的时候从数据库中读取该用户的书籍列表，并且显示在街面上
     *      判断这本书在本都的书库中是否存在，如果存在才显示，如果不存在，商店中下载该书籍
     *
     * @param bookInfo
     */
    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                downLoadBook(bookInfo.getBookUrl());
                downLoadImage(bookInfo.getIcoUrl());
                return null;
            }};
    }

    private void downLoadBook(String bookUrl){
        try {
            bookUrl = ServerConstant.DOWNLOAD_BOOK+"?bookUrl="+bookUrl+".txt";
            URL urlObj = new URL(bookUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", ServerConstant.USER_AGENT);
            InputStream inputStream = urlConnection.getInputStream();
            File file = new File("src\\main\\resource\\book\\"+bookInfo.getBookName()+".txt");
            boolean newFile = file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
            LocalBookServer.addBook(bookInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downLoadImage(String imageUrl){
        try {
            imageUrl = ServerConstant.IMAGE_DOWNLOAD + imageUrl;
            URL urlObj = new URL(imageUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", ServerConstant.USER_AGENT);
            InputStream inputStream = urlConnection.getInputStream();
            String[] split = StringUtils.split(imageUrl, ".");
            String imageType = split[split.length-1];
            File file = new File("src\\main\\resource\\image\\"+bookInfo.getBookName()+"."+imageType);
            if (!file.exists()){
                boolean newFile = file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
