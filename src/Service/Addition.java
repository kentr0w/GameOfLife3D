package Service;

import Constatnce.Constance;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class Addition {
    private TextArea textArea;
    private Button build, color, zoom_plus, zoom_minus, reset, run, pause, hide, rules;
    private CheckBox checkBox;
    private AnchorPane anchorPane;
    private Slider slider, speed;
    private Label label_count, label_far, label_speed, label_alivemin, label_alivemax, label_createmin, label_createmax;
    private Spinner<Integer> createmin,createmax, alivemin, alivemax;


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
        label_count = new Label("Длина грани");
        label_far = new Label("Расстояние");
        label_speed = new Label("Скорость");
        speed = new Slider();
        pause = new Button("Pause");
        hide = new Button("...");
        createmin = new Spinner<>();
        createmax = new Spinner<>();
        alivemax = new Spinner<>();
        alivemin = new Spinner<>();
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(3,26,9);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,20,6);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(3,26,8);
        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(2,20,5);
        createmin.setValueFactory(valueFactory2);
        createmax.setValueFactory(valueFactory1);
        alivemin.setValueFactory(valueFactory4);
        alivemax.setValueFactory(valueFactory3);
        label_alivemax = new Label("a");
        label_alivemin = new Label("b");
        label_createmax = new Label("c");
        label_createmin = new Label("d");
        rules  = new Button("Rules");
    }
    public Addition() {
        InitializeSize();
        anchorPane = new AnchorPane(textArea, slider, build, run, color, zoom_plus, zoom_minus, checkBox, reset,
                label_count, label_far, label_speed, speed, pause, hide, createmin, createmax, alivemax, alivemin, label_alivemax,
                label_alivemin,label_createmax, label_createmin, rules);
        InitializeAnchor();
    }

    public int getCreateMin(){
        return createmin.getValue();
    }
    public int getCreateMax(){
        return  createmax.getValue();
    }
    public int getAliveMin(){
        return alivemin.getValue();
    }
    public int getAliveMax(){
        return alivemax.getValue();
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

        speed.setPrefWidth(170);
        speed.setMin(1);
        speed.setMax(10);
        speed.setValue(5);
        speed.setShowTickLabels(true);
        speed.setShowTickMarks(false);
        hide.setPrefSize(10, 20);
        createmin.setPrefSize(60,20);
        createmax.setPrefSize(60,20);
        alivemax.setPrefSize(60,20);
        alivemin.setPrefSize(60,20);

    }

    public AnchorPane GetAnchor(){
        return anchorPane;
    }
    public Button[] getButtons(){
        return new Button[]{build, color, zoom_plus, zoom_minus, run, reset, pause, hide, rules};
    }

    public Spinner[] getSpinner(){
        return new Spinner[]{createmin,createmax, alivemin,alivemax};
    }

    public TextArea getTextArea(){
        return textArea;
    }
    public CheckBox getCheckBox(){
        return checkBox;
    }

    void InitializeAnchor(){
        anchorPane.setStyle("-fx-background-color: #B0C4DE;");
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

        AnchorPane.setTopAnchor(label_count, Constance.TOPLABEL1());
        AnchorPane.setRightAnchor(label_count, Constance.RIGHTLABEL1);

        AnchorPane.setTopAnchor(label_far, Constance.TOPLABEL2());
        AnchorPane.setRightAnchor(label_far, Constance.RIGHTLABEL2);

        AnchorPane.setTopAnchor(label_speed, Constance.TOPLABEL3());
        AnchorPane.setRightAnchor(label_speed, Constance.RIGHTLABEL3);

        AnchorPane.setTopAnchor(speed, Constance.TOPSPEED());
        AnchorPane.setRightAnchor(speed, Constance.RIGHTSPEED);

        AnchorPane.setTopAnchor(pause, Constance.TOPPAUSE());
        AnchorPane.setRightAnchor(pause, Constance.RIGHTPAUSE);

        AnchorPane.setTopAnchor(hide, Constance.TOPHIDE());


        AnchorPane.setTopAnchor(createmax, Constance.TOPCREATEMAX());
        AnchorPane.setRightAnchor(createmax, Constance.RIGHTCREATMAX);

        AnchorPane.setTopAnchor(createmin, Constance.TOPCREATEMIN());
        AnchorPane.setRightAnchor(createmin, Constance.RIGHTCREATEMIN);

        AnchorPane.setTopAnchor(alivemax, Constance.TOPALIVEMAX());
        AnchorPane.setRightAnchor(alivemax, Constance.RIGHTALIVEMAX);

        AnchorPane.setTopAnchor(alivemin, Constance.TOPALIVEMIN());
        AnchorPane.setRightAnchor(alivemin, Constance.RIGHTALIVEMIN);

        AnchorPane.setTopAnchor(label_alivemax, Constance.TOPLABEL4());
        AnchorPane.setRightAnchor(label_alivemax, Constance.RIGHTLABEL4);

        AnchorPane.setTopAnchor(label_alivemin, Constance.TOPLABEL5());
        AnchorPane.setRightAnchor(label_alivemin, Constance.RIGHTLABEL5);

        AnchorPane.setTopAnchor(label_createmax, Constance.TOPLABEL6());
        AnchorPane.setRightAnchor(label_createmax, Constance.RIGHTLABEL6);

        AnchorPane.setTopAnchor(label_createmin, Constance.TOPLABEL7());
        AnchorPane.setRightAnchor(label_createmin, Constance.RIGHTLABEL7);

        AnchorPane.setTopAnchor(rules, Constance.TOPRULES());
        AnchorPane.setRightAnchor(rules, Constance.RIGHTRULES);
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
        AnchorPane.setTopAnchor(label_count, Constance.TOPLABEL1());
        AnchorPane.setTopAnchor(label_speed, Constance.TOPLABEL2());
        AnchorPane.setTopAnchor(label_far, Constance.TOPLABEL3());
        AnchorPane.setTopAnchor(pause, Constance.TOPPAUSE());
        AnchorPane.setTopAnchor(speed, Constance.TOPSPEED());
        AnchorPane.setTopAnchor(createmax, Constance.TOPCREATEMAX());
        AnchorPane.setTopAnchor(createmin, Constance.TOPCREATEMIN());
        AnchorPane.setTopAnchor(alivemax, Constance.TOPALIVEMAX());
        AnchorPane.setTopAnchor(alivemin, Constance.TOPALIVEMIN());
        AnchorPane.setTopAnchor(label_alivemax, Constance.TOPLABEL4());
        AnchorPane.setTopAnchor(label_alivemin, Constance.TOPLABEL5());
        AnchorPane.setTopAnchor(label_createmax, Constance.TOPLABEL6());
        AnchorPane.setTopAnchor(label_createmin, Constance.TOPLABEL7());
        AnchorPane.setTopAnchor(rules, Constance.TOPRULES());
    }

    public void setColor(String str){
        anchorPane.setStyle("-fx-background-color: " + str + ";");

    }

    public Slider[] getSlidet(){
        return new Slider[]{slider, speed};
    }

    public void Hide(){
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(hide);
        anchorPane.setPrefWidth(10);
    }
    public void Show(){
        anchorPane.getChildren().addAll(textArea, slider, build, run, color, zoom_plus, zoom_minus, checkBox, reset,
                label_count, label_far, label_speed, speed, pause, createmin, createmax, alivemax, alivemin, label_alivemax,
                label_alivemin,label_createmax, label_createmin, rules);
        anchorPane.setPrefWidth((1.3*Constance.getWIDTH()/4));

        AnchorPane.setRightAnchor(textArea, Constance.RIGHTTEXT);

        AnchorPane.setRightAnchor(build, Constance.RIGHTSELECT);

        AnchorPane.setRightAnchor(color, Constance.RIGHTCOLOR);

        AnchorPane.setRightAnchor(checkBox, Constance.RIGHTCHECK);

        AnchorPane.setRightAnchor(zoom_plus, Constance.RIGHTZOOMP);

        AnchorPane.setRightAnchor(zoom_minus, Constance.RIGHTZOOMM);

        AnchorPane.setRightAnchor(slider, Constance.RIGHTSLIDER);

        AnchorPane.setRightAnchor(run, Constance.RIGHTRUN);

        AnchorPane.setRightAnchor(reset, Constance.RIGHTRESET);

        AnchorPane.setRightAnchor(label_count, Constance.RIGHTLABEL1);

        AnchorPane.setRightAnchor(label_far, Constance.RIGHTLABEL2);

        AnchorPane.setRightAnchor(label_speed, Constance.RIGHTLABEL3);

        AnchorPane.setRightAnchor(speed, Constance.RIGHTSPEED);

        AnchorPane.setRightAnchor(pause, Constance.RIGHTPAUSE);

        AnchorPane.setRightAnchor(createmax, Constance.RIGHTCREATMAX);

        AnchorPane.setRightAnchor(createmin, Constance.RIGHTCREATEMIN);

        AnchorPane.setRightAnchor(alivemax, Constance.RIGHTALIVEMAX);

        AnchorPane.setRightAnchor(alivemin, Constance.RIGHTALIVEMIN);

        AnchorPane.setRightAnchor(label_alivemax, Constance.RIGHTLABEL4);

        AnchorPane.setRightAnchor(label_alivemin, Constance.RIGHTLABEL5);

        AnchorPane.setRightAnchor(label_createmax, Constance.RIGHTLABEL6);

        AnchorPane.setRightAnchor(label_createmin, Constance.RIGHTLABEL7);

        AnchorPane.setRightAnchor(rules, Constance.RIGHTRULES);

    }
}
