package zz.reader.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang.StringUtils;
import zz.reader.constant.ClientConstant;
import zz.reader.model.UserConfig;
import zz.reader.service.localServer.UserConfigServer;
import zz.reader.service.remoteServer.UserServer;

/**
 * Created by zz on 2016-07-10.
 */
public class SettingViewController {

    @FXML private HBox hbox;

    @FXML private Text exampleText;

    @FXML private Button confirmButton;

    @FXML private Button cancelButton;

    @FXML private ColorPicker fontColorPicker;

    @FXML private TextField fontSizeField;

    @FXML private Slider fontSizeSlide;

    @FXML private ColorPicker bgColorPicker;

    @FXML private ChoiceBox<String> fontStyleChoiceBox;

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize(){
        UserConfig nowUser = ClientConstant.getNowUser();
        fontColorPicker.setValue(Color.valueOf(nowUser.getFontColor()));
        fontSizeField.setText(nowUser.getFontSize());
        bgColorPicker.setValue(Color.valueOf(nowUser.getBgColor()));
        fontSizeSlide.setValue(Double.parseDouble(nowUser.getFontSize()));
        fontSizeField.setText(nowUser.getFontSize());
        fontSizeSlide.setMajorTickUnit(9);
        fontSizeSlide.setMinorTickCount(3);
        fontSizeSlide.setBlockIncrement(1);
        fontSizeSlide.setShowTickLabels(true);
        fontSizeSlide.setShowTickMarks(true);
        fontStyleChoiceBox.setItems(FXCollections.observableArrayList(Font.getFamilies()));

        fontStyleChoiceBox.setValue(nowUser.getFontStyle());

        exampleText.setFont(Font.font(fontStyleChoiceBox.getValue(), Double.parseDouble(nowUser.getFontSize())));
        exampleText.setFill(fontColorPicker.getValue());

        hbox.setStyle("-fx-background-color:"+nowUser.getBgColor());

        fontSizeSlide.valueProperty().addListener((observable, oldValue, newValue) -> {
            fontSizeField.setText(String.valueOf(newValue.intValue()));
            exampleText.setFont(Font.font(newValue.intValue()));
        });

        fontSizeField.textProperty().addListener((observable, oldValue, newValue) -> {

            if (StringUtils.contains(newValue, '.')) {
                newValue = StringUtils.split(newValue, '.')[0];
                fontSizeField.setText(newValue);
            }
            if ((!newValue.equals(""))&&StringUtils.isNumeric(newValue)) {
                int value = Integer.valueOf(newValue);
                if (value > fontSizeSlide.getMax()){
                    fontSizeSlide.setValue(fontSizeSlide.getMax());
                }else fontSizeSlide.setValue(value);
                exampleText.setFont(Font.font(value));
            }else {
                fontSizeField.setText(oldValue);
            }
        });

        fontStyleChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            exampleText.setFont(Font.font(newValue, Double.parseDouble(fontSizeField.getText())));

        });
    }



    @FXML
    public void handlerConfirm() {
        boolean hasChanged = false;

        String  fontColor   = StringUtils.replace(fontColorPicker.getValue().toString(),"0x","#");
        String  fontSize    = fontSizeField.getText();
        String  bgColor     = StringUtils.replace(bgColorPicker.getValue().toString(),"0x","#");
        String  fontStyle   = fontStyleChoiceBox.getValue();

        if (!fontColor.equals(ClientConstant.getNowUser().getFontColor())){
            hasChanged = true;
        }
        if (!fontSize.equals(ClientConstant.getNowUser().getFontSize())){
            hasChanged = true;
        }
        if (!fontStyle.equals(ClientConstant.getNowUser().getFontStyle())){
            hasChanged = true;
        }
        if (!bgColor.equals(ClientConstant.getNowUser().getBgColor())){
            hasChanged =true;
        }

        if (hasChanged){
            UserConfig nowUser = ClientConstant.getNowUser();

            nowUser.setFontColor(fontColor);
            nowUser.setFontSize(fontSize);
            nowUser.setBgColor(bgColor);
            nowUser.setFontStyle(fontStyle);

            UserServer userServer = new UserServer();
            boolean pushSuccess = userServer.pushPerference(nowUser);
            // TODO: 2016-07-13
            UserConfigServer.updateUserConfig(nowUser);
        }
        dialogStage.close();
    }

    @FXML
    public void handlerCancel() {
        dialogStage.close();
    }

    @FXML
    public void handlerFontColorChange() {
        Color value = fontColorPicker.getValue();
        exampleText.setFill(value);
    }

    @FXML
    public void handlerBgColorChange() {
        Color value = bgColorPicker.getValue();
        String color = value.toString();
        color = StringUtils.replace(color, "0x", "#");
        hbox.setStyle("-fx-background-color:"+color);
    }

    private void setStage(Stage stage){
        this.dialogStage = stage;
    }
}

