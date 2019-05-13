package Control;


import ColorPackage.ColorScene;
import Constatnce.Constance;
import Corps.Corps;
import Factory.Factory;
import Logic.Logic;
import Service.Addition;
import Service.MainScene;
import Service.MinorScene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.shape.Box;
import Corps.Container;
import java.util.ArrayList;

public class Controller {
    private MainScene mainScene;
    private MinorScene minorScene;
    private Addition addition;
    private Corps corps;
    private Logic logic;
    private TextArea textArea;
    private Button select, color, zoom_plus, zoom_minus, run, reset, pause, test, rules;
    private CheckBox checkBox;
    private ArrayList<Container> boxes;
    private int k, a, b, c, d;
    private boolean ishide = false;



    public Controller(Factory factory){
        mainScene = factory.GetMain();
        minorScene = factory.GetMinor();
        addition = factory.GetAddition();
        corps = factory.getCorps();
        logic = factory.getLogic();
        this.textArea = addition.getTextArea();
        this.select = addition.getButtons()[0];
        this.color = addition.getButtons()[1];
        this.zoom_plus = addition.getButtons()[2];
        this.zoom_minus = addition.getButtons()[3];
        this.run = addition.getButtons()[4];
        this.reset = addition.getButtons()[5];
        this.checkBox = addition.getCheckBox();
        this.pause = addition.getButtons()[6];
        this.test = addition.getButtons()[7];
        this.rules = addition.getButtons()[8];
        InitializeEvent();
    }

    public Scene Build(){
        mainScene.InitializePane(minorScene, addition);
        return mainScene.GetScene();
    }

    private void Refactor(){
        corps.setCount();
        minorScene.setBoxes(corps.getBoxes());
    }

    private void InitializeEvent(){
        select.setOnAction(event -> {
            try{
                k = Integer.parseInt((textArea.getText()));
                if(k < 0)
                    throw new Exception();
                Constance.SETCOUNT(k);
                Constance.setStep((int)addition.getSlidet()[0].getValue());
                Refactor();
                mainScene.SetSubscene(minorScene.GetSubScene());
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Что то пошло не так, перечитайте правила");
                alert.showAndWait();
            }
        });

        zoom_plus.setOnAction(event->{
            minorScene.ZoomPlus();
        });

        zoom_minus.setOnAction(event->{
            minorScene.ZoomMinus();
        });
        color.setOnAction(event -> {
            ColorScene colorScene = new ColorScene();
            String str = colorScene.getName();

            if(str==null)
                return;
            switch (str) {
                case "Фон за кубиком":
                    addition.setColor(ColorScene.toRGBCode(Constance.getPhongMaterialMain().getDiffuseColor()));
                    break;
                case "Фон панели":
                    minorScene.SetColor();
                    break;
                case "Фон кубиков":
                    corps.SetColor();
                    break;
            }
        });
        checkBox.setOnAction(event -> {
            boxes = corps.getBoxes();
            if(checkBox.isSelected()) {
                for (Box obj : corps.getBoxes()) {
                    obj.setOnMousePressed(event1 -> {
                    });
                }
            }
            else{
                for (Box obj : boxes) {
                    obj.setOnMousePressed(event1 -> {
                        if(obj.getMaterial() == (null))
                            corps.setMaterial(obj);
                        else
                            obj.setMaterial(null);
                    });
                }
            }
        });

        run.setOnAction(event -> {
            try {
                if(corps.getBoxes().size()==0)
                    throw new Exception();
                Constance.SPEED = 1000 / (int) addition.getSlidet()[1].getValue();
                a = addition.getCreateMin();
                b = addition.getCreateMax();
                c = addition.getAliveMin();
                d = addition.getAliveMax();
                if (!isCorrect(a, b, c, d))
                    throw new Exception();
                Constance.CREATEMIN = (a);
                Constance.CREATEMAX = (b);
                Constance.ALIVEMIN = (c);
                Constance.ALIVEMAX = (d);
                corps.BeforRun();
                logic.Setup(corps.getBoxes());
                logic.setChoses(corps.getChoses());
                logic.StartLogic();
            }
            catch (Exception ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Что то пошло не так, перечитайте правила");
                alert.showAndWait();
            }
        });

        reset.setOnAction(event -> {
            logic.Clean();
            corps.Clean();
            Refactor();
            mainScene.SetSubscene(minorScene.GetSubScene());
        });

        pause.setOnAction(event -> {
            logic.Clean();
        });

        test.setOnAction(event -> {
            if(!ishide) {
                addition.Hide();
                minorScene.Hide();
                ishide = true;
            }
            else {
                addition.Show();
                minorScene.Show();
                ishide = false;
            }
        });

        rules.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rules");
            alert.setHeaderText("Правила");
            alert.setContentText("a - Максимальное количество 'живых' соседей, чтобы клетка стала живой\n " +
                    "b - Минимальное количество 'живых' соседей, чтобы клетка стала живой\n " +
                    "c - Максимальное количество 'живых' соседей, чтобы клетка продолжала жить\n" +
                    "d - Минимальное количество 'живых' соседей, чтобы клетка продолжала жить\n");
            alert.showAndWait();
        });

        addition.getSlidet()[0].valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Constance.setStep(newValue.intValue());
                Refactor();
                mainScene.SetSubscene(minorScene.GetSubScene());
            }
        });
    }

    private boolean isCorrect(int a, int b, int c, int d){
        if(b<=a)
            return false;
        return d > c;
    }
}

