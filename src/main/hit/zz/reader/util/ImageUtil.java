package zz.reader.util;

import javafx.scene.image.Image;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by zz on 2016-07-08.
 */
public class ImageUtil {


    public static Image getImageFile(String bookName) {
        // TODO: 2016-07-10 最好多线程去获取
        Image image = null;
        try {
            File file = new File("src\\main\\resource\\image");
            File[] files = file.listFiles();
            assert files != null;
            for (File f :
                    files) {
                String fileName = StringUtils.split(f.getName(), ".")[0];
                if (fileName.equals(bookName)) {
                    image = new Image(new FileInputStream(f));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Image displayImage(String url){
        Image image = new Image(url);
        return image;
    }
}
