package zz.reader.model;

/**
 * Created by zz on 2016-07-04.
 */
public class UserConfig {

    private String userName;

    private String passWord;

    private String fontStyle;

    private int fontSize;

    private String fontColor;

    private String bgColor;

    public UserConfig(String userName, String passWord, String fontStyle, int fontSize, String fontColor, String bgColor) {
        this.userName = userName;
        this.passWord = passWord;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
    }

    public UserConfig() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
}
