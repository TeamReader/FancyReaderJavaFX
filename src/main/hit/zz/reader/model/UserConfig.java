package zz.reader.model;

/**
 * Created by zz on 2016-07-04.
 */
public class UserConfig {

    private String userName;

    private String passWord;

    private String fontStyle;

    private String fontSize;

    private String fontColor;

    private String bgColor;

    public UserConfig(String userName, String passWord, String fontStyle, String fontSize, String fontColor, String bgColor) {
        this.userName = userName;
        this.passWord = passWord;
        this.fontStyle = fontStyle;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
    }

    public UserConfig() {
    }

    public UserConfig(String userName, String passWord) {
        this.passWord = passWord;
        this.userName = userName;
    }

    public static class Builder{
        private  String userName;

        private  String passWord;

        private  String fontStyle;

        private  String fontSize;

        private  String fontColor;

        private  String bgColor;

        public Builder(String userName, String passWord){
            this.userName = userName;
            this.passWord = passWord;
        }

        public Builder fontStyle(String fontStyle){
            this.fontStyle = fontStyle;
            return this;
        }

        public Builder fontSize(String fontSize){
            this.fontSize = fontSize;
            return this;
        }

        public Builder fontColor(String fontColor){
            this.fontColor = fontColor;
            return this;
        }

        public Builder bgColor(String bgColor){
            this.bgColor = bgColor;
            return this;
        }

        public UserConfig build(){
            return new UserConfig(this);
        }
    }

    private UserConfig(Builder builder){
        userName = builder.userName;
        passWord = builder.passWord;
        fontStyle = builder.fontStyle;
        fontSize = builder.fontSize;
        fontColor = builder.fontColor;
        bgColor = builder.bgColor;
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

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
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
