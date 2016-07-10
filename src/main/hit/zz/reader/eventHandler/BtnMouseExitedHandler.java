package zz.reader.eventHandler;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * Created by zz on 2016-07-06.
 */
public class BtnMouseExitedHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        Button source = (Button) event.getSource();
        source.setEffect(null);
    }
}
