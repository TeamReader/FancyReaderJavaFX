package zz.reader.factory;

/**
 * Created by zz on 2016-07-09.
 */
public enum ViewType {
    LOGIN_VIEW("/zz/reader/view/login.fxml"),
    REGISTER_VIEW("/zz/reader/view/register.fxml"),
    READER_VIEW("/zz/reader/view/reader.fxml"),
    MAIN_VIEW("/zz/reader/view/mainLayout.fxml"),
    SHOP_VIEW("/zz/reader/view/shopView.fxml"),
    SHOPCELL_VIEW("/zz/reader/view/shopListCell.fxml"),
    SETTING_VIEW("/zz/reader/view/settingDialog.fxml"),
    LISTCELL_VIEW("/zz/reader/view/listCell.fxml")

    ;
    private String resourcePath;
    private ViewType(String resourcePath){
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
