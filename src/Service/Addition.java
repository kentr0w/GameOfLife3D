package Service;

import Constatnce.Constance;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class Addition {
    private TextArea textArea;
    private Button build, color, zoom_plus, zoom_minus, reset, run;
    private CheckBox checkBox;
    private AnchorPane anchorPane;
    private Slider slider;

    {
        textArea = new TextArea();
        build = new Button("Build");
        color = new Button("Color");
        checkBox = new CheckBox("View Mode");
        zoom_plus = new Button("+");
        zoom_minus = new Button("-");
        slider = new Slider();
        reset = new Button("Reset");
        run = new Button("Run");
    }
    public Addition() {
        InitializeSize();
        anchorPane = new AnchorPane(textArea, slider, build, run, color, zoom_plus, zoom_minus, checkBox, reset);
        InitializeAnchor();
    }

    private void InitializeSize(){
        textArea.setPrefHeight(10);
        textArea.setPrefWidth(140);
        zoom_plus.setPrefSize(30,25);
        zoom_minus.setPrefSize(30,25);

        slider.setPrefWidth(170);
        slider.setMin(5);
        slider.setMax(25);
        slider.setValue(5);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(false);
    }

    public Button[] getButtons(){
        return new Button[]{build, color, zoom_plus, zoom_minus, run, reset};
    }

    public TextArea getTextArea(){
        return textArea;
    }
    public CheckBox getCheckBox(){
        return checkBox;
    }

    void InitializeAnchor(){
        anchorPane.setStyle("-fx-background-color: #008080;");
        anchorPane.setPrefSize((1.3*Constance.getWIDTH()/4),Constance.getHEIGHT());

        AnchorPane.setTopAnchor(textArea, Constance.TOPTEXT());

        AnchorPane.setRightAnchor(textArea, Constance.RIGHTTEXT);

        AnchorPane.setTopAnchor(build, Constance.TOPSELECT());
        AnchorPane.setRightAnchor(build, Constance.RIGHTSELECT);

        AnchorPane.setTopAnchor(color, Constance.TOPCOLOR());
        AnchorPane.setRightAnchor(color, Constance.RIGHTCOLOR);

        AnchorPane.setTopAnchor(checkBox, Constance.TOPCHECK());
        AnchorPane.setRightAnchor(checkBox, Constance.RIGHTCHECK);

        AnchorPane.setTopAnchor(zoom_plus, Constance.TOPZOOMP());
        AnchorPane.setRightAnchor(zoom_plus, Constance.RIGHTZOOMP);

        AnchorPane.setTopAnchor(zoom_minus,Constance.TOPZOOMM());
        AnchorPane.setRightAnchor(zoom_minus, Constance.RIGHTZOOMM);

        AnchorPane.setTopAnchor(slider, Constance.TOPSLIDER());
        AnchorPane.setRightAnchor(slider, Constance.RIGHTSLIDER);

        AnchorPane.setTopAnchor(run, Constance.TOPRUN());
        AnchorPane.setRightAnchor(run, Constance.RIGHTRUN);

        AnchorPane.setTopAnchor(reset, Constance.TOPRESET());
        AnchorPane.setRightAnchor(reset, Constance.RIGHTRESET);
    }

    void ChangeSize(){
        AnchorPane.setTopAnchor(textArea, Constance.TOPTEXT());
        AnchorPane.setTopAnchor(build, Constance.TOPSELECT());
        AnchorPane.setTopAnchor(color, Constance.TOPCOLOR());
        AnchorPane.setTopAnchor(checkBox, Constance.TOPCHECK());
        AnchorPane.setTopAnchor(zoom_plus, Constance.TOPZOOMP());
        AnchorPane.setTopAnchor(zoom_minus,Constance.TOPZOOMM());
        AnchorPane.setTopAnchor(run, Constance.TOPRUN());
        AnchorPane.setTopAnchor(reset, Constance.TOPRESET());
        AnchorPane.setTopAnchor(slider, Constance.TOPSLIDER());

    }
    AnchorPane GetAnchor(){

        return anchorPane;
    }

    public void setColor(String str){
        anchorPane.setStyle("-fx-background-color: " + str + ";");

    }

    public double getSlidet(){
        return slider.getValue();
    }
}
