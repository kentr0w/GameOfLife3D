package Service;

import Constatnce.Constance;
import Corps.Corps;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Addition {
    private TextArea textArea;
    private Button select, color, zoom_plus, zoom_minus;
    private CheckBox checkBox;
    private AnchorPane anchorPane;
    private Corps corps;

    {
        textArea = new TextArea();
        select = new Button("Reset");
        color = new Button("Color");
        checkBox = new CheckBox("View Mood");
        zoom_plus = new Button("Zoom +");
        zoom_minus = new Button("Zoom -");
        corps = new Corps();
    }
    public Addition() {
        InitializeSize();
        anchorPane = new AnchorPane(textArea, select, zoom_minus, zoom_plus, color, checkBox);
        InitializeAnchor();
    }

    private void InitializeSize(){
        textArea.setPrefHeight(10);
        textArea.setPrefWidth(100);
        zoom_plus.setPrefSize(75,25);
        zoom_minus.setPrefSize(75,25);
    }

    public Button[] getButtons(){
        return new Button[]{select, color, zoom_plus, zoom_minus};
    }

    public TextArea getTextArea(){
        return textArea;
    }
    public CheckBox getCheckBox(){
        return checkBox;
    }

    void InitializeAnchor(){
        anchorPane.setStyle("-fx-background-color: #008080;");
        anchorPane.setPrefSize((Constance.getWIDTH()/4),Constance.getHEIGHT());

        AnchorPane.setTopAnchor(textArea, Constance.TOPTEXT());

        AnchorPane.setRightAnchor(textArea, Constance.RIGHTTEXT);

        AnchorPane.setTopAnchor(select, Constance.TOPSELECT());
        AnchorPane.setRightAnchor(select, Constance.RIGHTSELECT);

        AnchorPane.setTopAnchor(color, Constance.TOPCOLOR());
        AnchorPane.setRightAnchor(color, Constance.RIGHTCOLOR);

        AnchorPane.setTopAnchor(checkBox, Constance.TOPCHECK());
        AnchorPane.setRightAnchor(checkBox, Constance.RIGHTCHECK);

        AnchorPane.setTopAnchor(zoom_plus, Constance.TOPZOOMP());
        AnchorPane.setRightAnchor(zoom_plus, Constance.RIGHTZOOMP);

        AnchorPane.setTopAnchor(zoom_minus,Constance.TOPZOOMM());
        AnchorPane.setRightAnchor(zoom_minus, Constance.RIGHTZOOMM);
    }

    void ChangeSize(){
        AnchorPane.setTopAnchor(textArea, Constance.TOPTEXT());
        AnchorPane.setTopAnchor(select, Constance.TOPSELECT());
        AnchorPane.setTopAnchor(color, Constance.TOPCOLOR());
        AnchorPane.setTopAnchor(checkBox, Constance.TOPCHECK());
        AnchorPane.setTopAnchor(zoom_plus, Constance.TOPZOOMP());
        AnchorPane.setTopAnchor(zoom_minus,Constance.TOPZOOMM());
    }
    AnchorPane GetAnchor(){

        return anchorPane;
    }

    public void setColor(String str){
        anchorPane.setStyle("-fx-background-color: " + str + ";");

    }
}
