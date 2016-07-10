package zz.reader.factory;

import javafx.fxml.FXMLLoader;

/**
 * Created by zz on 2016-07-09.
 */
public class FxmlLoaderFactory {

    public static FXMLLoader generateLoader(ViewType viewType){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(FxmlLoaderFactory.class.getResource(viewType.getResourcePath()));
        return fxmlLoader;
    }
}

