package zz.reader.controller;

import com.sun.javafx.tk.Toolkit;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import zz.reader.constant.ClientConstant;
import zz.reader.factory.ViewType;
import zz.reader.manager.ViewManager;
import zz.reader.model.UserConfig;

import java.io.*;
import java.util.HashMap;

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

    private File file;

    private int linePerpage;

    private int wordNumsPerLine;

    private void init(){
        double height = pagination.getHeight();
        double width = pagination.getWidth();
        int fontwidth = Integer.parseInt(ClientConstant.getNowUser().getFontSize());
        int fontHeight = (int) Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(ClientConstant.getNowUser().getFontStyle(),fontwidth)).getLineHeight();
        if(height == 0){
            height = 700;
        }
        if(width == 0){
            width = 1260;
        }
        linePerpage = (int) (height/(fontHeight+3));
        wordNumsPerLine = (int) (width/fontwidth);
    }

    @FXML
    private void initialize(){
        pageLineBeginMap = new HashMap<>();
        pageLineNumsMap = new HashMap<>();
        int size = pageLineBeginMap.size();
        pagination.setStyle("-fx-background-color:"+ClientConstant.getNowUser().getBgColor());
        init();
        bookNameLable.setText(ClientConstant.nowReadBookName);
        String bookName = ClientConstant.nowReadBookName;
        file = new File("src\\main\\resource\\book\\"+bookName+".txt");
        CalcuThread calcuThread = new CalcuThread();
        calcuThread.start();
        try {
            calcuThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pagination.setPageCount(pageLineBeginMap.size());
        pagination.setCurrentPageIndex(0);
        System.out.println(pagination.getHeight());
        System.out.println(pagination.getWidth());
        pagination.setPageFactory(indexPage -> {
            String pageData1 = createPageData(indexPage);
            Label page = createPage(indexPage, pageData1,ClientConstant.getNowUser());
            return page;
        });

    }

    @FXML
    public void handlerBackButton(){
        ViewManager.initLayout(ViewType.MAIN_VIEW);
    }
    //
//    /**
//     *
//     * @param file 阅读的文件
//     * @param wordNumsPage　每页能够显示的字数
//     * @param pageIndex　需要第几页的数据
//     * @return
//     */
//    public String createPageData(File file, int wordNumsPage, int pageIndex){
//        try {
//            long wordPosition = wordNumsPage*pageIndex;
//            String charset = TextUtil.getCharset(file);
//            BufferedReader bufferedReader = new BufferedReader(
//                    new InputStreamReader(
//                            new FileInputStream(file),charset
//                    ));
//            long skip = bufferedReader.skip(wordPosition);
//            char[] chars = new char[wordNumsPage];
//            int read = bufferedReader.read(chars);
//            System.out.println(read);
////            return String.valueOf(chars).replace("/r","");
//            return String.valueOf(chars);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
//
//    }
    private String createPageData(int pageIndex){
        StringBuilder stringBuilder = new StringBuilder();
        try {
//            String charset = TextUtil.getCharset(file);
            LineNumberReader lineNumberReader = new LineNumberReader(
                    new InputStreamReader(
                            new FileInputStream(file)
                    )
            );
            int lineBegin = pageLineBeginMap.get(pageIndex + 1);
            lineNumberReader.setLineNumber(lineBegin);
            for (int i = 0;i < lineBegin;i++){
                lineNumberReader.readLine();
            }
            int lineNums = pageLineNumsMap.get(pageIndex+1);
            for (int i = 0; i < lineNums; i++){
                String s = lineNumberReader.readLine();
                stringBuilder.append(s).append("\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    /**
     * 创建一页
     * @param pageIndex
     * @param data
     * @return
     */
    public Label createPage(int pageIndex, String data, UserConfig userConfig){
        System.out.println(data);
        Label label = new Label();
        label.setText(data);
        label.setFont(new Font(userConfig.getFontStyle(),Double.valueOf(userConfig.getFontSize())));
        label.setStyle("-fx-background-color:"+userConfig.getBgColor());
        label.setTextFill(Color.valueOf(userConfig.getFontColor()));
        label.setWrapText(true);
        return label;
    }

    private HashMap<Integer,Integer> pageLineBeginMap;
    private HashMap<Integer,Integer> pageLineNumsMap;

    private class CalcuThread extends Thread{

        @Override
        public void run() {
            init();
            //第几页
            int pageNums = 1;
            //从第几行开始显示
            int lineBegin= 0;
            //显示的行数用来判断剩余的行 能否容纳这么多行
            int lineNums = 0;
            // 实际读取的行数
            int trueLineNums = 0;
            int defaultLinePerPage = linePerpage;
            try {
//                String charset = TextUtil.getCharset(file);
                LineNumberReader lineNumberReader = new LineNumberReader(
                        new InputStreamReader(
                                new FileInputStream(file)
                        )
                );
                String tempData;
                while ((tempData = lineNumberReader.readLine())!=null){
                    double lines = Math.ceil(tempData.length()/wordNumsPerLine);
                    if (lines == 0){
                        lines = 1;
                    }
                    if(defaultLinePerPage < lines){
                        //表示剩余的行无法满足容纳者一句话
                        pageLineBeginMap.put(pageNums,lineBegin);
                        pageLineNumsMap.put(pageNums,trueLineNums);
                        lineBegin = lineNumberReader.getLineNumber();
                        trueLineNums = 0;
                        defaultLinePerPage = linePerpage;
                        pageNums++;
                        lineNums = 0;
                        int templines = (int) Math.ceil(tempData.length()/wordNumsPerLine);
                        if (templines == 0){
                            templines = 1;
                        }
                        lineNums += templines;
                        defaultLinePerPage -= templines;
                        continue;
                    }
                    trueLineNums++;
                    lineNums += lines;
                    defaultLinePerPage -= lines;
                }
                //处理最后的
                pageLineBeginMap.put(pageNums,lineBegin);
                pageLineNumsMap.put(pageNums,lineNums);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    /**
//     * 计算每页能够容纳的字符数
//     * 计算每页能够容纳的行数
//     * 根据每行能容纳的字符 以及能够容纳的行数 来计算 到底应该显示多少行
//     *  如何计算：
//     *     默认行数为 a
//     *     每行容纳的字符数为b，
//     *     每读取一行
//     *       计算剩余的行数能否容纳这么多行
//     *       如果这行字符个数是c 大于b 那么根据是多少倍 a就减去多少
//     *       显示的行数+1
//     *     如果a = 0
//     *     结束读取
//     *     记录显示的是从第几行到第几行
//     * 显示的同时，应该记录当前显示的 是从第几行 到第几行
//     * 会记录当前读到哪一行，如果没有 默认为第一行，
//     *
//     * 按照字符和按照行都都会遇到问题
//     *  按照字符 遇到"" 这种是一行直接按照读取一行算，
//     *  按照行 遇到很长的一行 会占用几行
//     *
//     *  办法：
//     *    读取行，后台运行线程 计算每一页应该从第几行开始读取，最好能存入数据库
//     *
//     *
//     * @return
//     */
//
//
////    public String createPageData(File file, int pageIndex,int wordNumsPerLine, int linesPerPage, int perPageEndLineNum){
////        StringBuilder stringBuilder = new StringBuilder();
////        try {
////            displayLineBeginLineNum = perPageEndLineNum + 1;
////            String charset = TextUtil.getCharset(file);
////            LineNumberReader lineNumberReader = new LineNumberReader(
////                    new InputStreamReader(
////                            new FileInputStream(file), charset
////                    )
////            );
////            lineNumberReader.setLineNumber(displayLineBeginLineNum);
////            int defaultLinesNums = linesPerPage;
////            int displayLine = displayLineBeginLineNum;
////            for (;defaultLinesNums>0;){
////                String s = lineNumberReader.readLine();
////                int lines = (int) Math.ceil(s.length()/wordNumsPerLine);
////                if (lines == 0){
////                    lines = 1;
////                }
////                if(defaultLinesNums < lines){
////                    break;
////                }
////                defaultLinesNums = defaultLinesNums - lines;
////                displayLine += lines;
////                stringBuilder.append(s).append("\r\n");
////            }
////            displayLineEndLineNum = displayLine;
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        return stringBuilder.toString();
////    }
//
//    private int numsWordPerPage(){
//        double height = pagination. getHeight();
//        double width = pagination.getWidth();
//
////        int fontSize = Integer.parseInt(ClientConstant.getNowUser().getFontSize());
//
//        int fontSize = 12;
//        int columnFontNums = (int) ((width)/fontSize);
//
//        int line = (int)((height)/fontSize);
//
//        return line*columnFontNums;
//
//    }
//
//    /**
//     * 计算每行能够容纳的字符数
//     * @return
//     */
//    private int wordNumsPerLine(){
//        double width = pagination.getWidth();
//        if (width == 0){
//            width = 1200;
//        }
////        int fontSize = Integer.parseInt(ClientConstant.getNowUser().getFontSize());
//        int fontSize = 12;
//        return (int) ((width)/fontSize);
//
//    }
//
//    /**
//     * 计算每页能够容纳的行数
//     * @return
//     */
//    private int linesPerPage(){
//        double height = pagination.getHeight();
//        if (height == 0){
//            height = 700;
//        }
////        float asdf = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(23)).getLineHeight();
////        float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth("你", Font.font(23));
////        System.out.println("this is "+width);
////
////        System.out.println("this is "+asdf);
////        int fontSize = Integer.parseInt(ClientConstant.getNowUser().getFontSize());
//        int fontSize = 17;
//        return (int) (height/fontSize);
//    }
//
//    public void read(File file){
//
//    }
}
