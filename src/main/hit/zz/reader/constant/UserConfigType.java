package zz.reader.constant;

/**
 * Created by zz on 2016-07-10.
 */
public enum UserConfigType {
    FONT_STYLE("fontStyle"),
    FONT_SIZE("fontSize"),
    FONT_COLOR("fontColor"),
    BG_COLOR("bgColor");
    private String description;
    private UserConfigType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
