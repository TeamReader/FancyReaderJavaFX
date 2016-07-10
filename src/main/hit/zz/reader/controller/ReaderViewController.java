package zz.reader.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import zz.reader.model.UserConfig;
import zz.reader.util.TextUtil;

import java.io.*;

/**
 * Created by zz on 2016-07-06.
 */
public class ReaderViewController {

    @FXML
    private Label bookNameLable;

    @FXML
    private Pagination pagination;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button settingButton;
    
    @FXML
    private void initialize(){
        pagination.setCurrentPageIndex(0);
        System.out.println(pagination.getHeight());
        System.out.println(pagination.getWidth());
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                return null;
            }
        });
        settingButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println(pagination.getWidth());
            System.out.println(pagination.getHeight());
        });
        File file = new File("src\\main\\hit\\zz\\reader\\1.txt");

        pagination.setPageFactory(indexPage -> {
            int numsWordPerPage = numsWordPerPage();
            String pageData = createPageData(file, numsWordPerPage, indexPage);
            Label page = createPage(indexPage, pageData, 0, new UserConfig("zz", "pp", Font.getDefault().getStyle(), 12, Color.BLACK.toString(), Color.WHITE.toString()));
            return page;
        });

    }

    private File file;

    /**
     * 创建一页
     * @param pageIndex
     * @param data
     * @param lineSpacing
     * @return
     */
    public Label createPage(int pageIndex, String data, double lineSpacing, UserConfig userConfig){
//        Text text = new Text();
//        text.setWrappingWidth(pagination.getWidth());
//        text.setLineSpacing(lineSpacing);
//        text.setText(data);
//        text.setFill(Color.valueOf(userConfig.getFontColor()));
//        text.setFont(Font.font(userConfig.getFontStyle(), FontPosture.REGULAR,userConfig.getFontSize()));
//        return text;
        System.out.println(data);
        Label label = new Label();
        label.setText(data);
        label.setFont(new Font(userConfig.getFontStyle(),userConfig.getFontSize()));
        label.setStyle("-fx-background-color:white");
        label.setTextFill(Color.valueOf(userConfig.getFontColor()));
        label.setWrapText(true);
        return label;
    }

//    public String createPageDate(File file, int linePerPage, int pageIndex,int temp) throws FileNotFoundException {
//        BufferedReader bufferedReader = new BufferedReader(
//                new InputStreamReader(
//                        new FileInputStream(file)
//                ));
//        LineNumberReader()
//    }

    /**
     *
     * @param file 阅读的文件
     * @param wordNumsPage　每页能够显示的字数
     * @param pageIndex　需要第几页的数据
     * @return
     */
    public String createPageData(File file, int wordNumsPage, int pageIndex){
        try {
            long wordPosition = wordNumsPage*pageIndex;
            String charset = TextUtil.getCharset(file);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file),charset
                    ));
            long skip = bufferedReader.skip(wordPosition);
            char[] chars = new char[wordNumsPage];
            int read = bufferedReader.read(chars);
            System.out.println(read);
//            return String.valueOf(chars).replace("/r","");
            return String.valueOf(chars);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 计算每页能够容纳的字符数
     * @return
     */
    public int numsWordPerPage(){
        double height = pagination. getHeight();
        double width = pagination.getWidth();
        if (height==0 && width ==0 ){
            height = 700;
            width  = 1200;
        }
        double size = Font.getDefault().getSize();
        int columnFontNums = (int) ((width-500)/size);
        int line = (int)((height-300)/size);
        return line*columnFontNums;
    }

    public int linesPerPage(){
        return 10;
    }

}
