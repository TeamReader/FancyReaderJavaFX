package zz.reader.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by zz on 2016-07-07.
 */
public class TextUtil {

    public static String getCharset(File file) throws IOException {
        BufferedInputStream bin = new BufferedInputStream(
                new FileInputStream(file));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        //其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII" ;
                break ;
            default:
                code = "GBK";
        }

        return code;
    }
   public static void main(String args[]) throws IOException {
       File file = new File("C:\\Users\\ZoomP\\IdeaProjects\\FancyReaderJavaFX\\src\\main\\resource\\book\\傲慢与偏见.txt");
       String charset = getCharset(file);
       System.out.println(charset);
   }
}
