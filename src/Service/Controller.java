package Service;

import ColorPackage.ColorScene;
import Constatnce.Constance;
import Corps.Corps;
import Factory.Factory;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Controller {
    private MainScene mainScene;
    private MinorScene minorScene;
    private Addition addition;
    private Corps corps;
    private TextArea textArea;
    private Button select, color, zoom_plus, zoom_minus;
    private CheckBox checkBox;
    private ArrayList<Box> boxes;

    public Controller(Factory factory){
        mainScene = factory.GetMain();
        minorScene = factory.GetMinor();
        addition = factory.GetAddition();
        corps = factory.getCorps();
        this.textArea = addition.getTextArea();
        this.select = addition.getButtons()[0];
        this.color = addition.getButtons()[1];
        this.zoom_plus = addition.getButtons()[2];
        this.zoom_minus = addition.getButtons()[3];
        this.checkBox = addition.getCheckBox();
        InitializeEvent();
    }

    public Scene build(){
        mainScene.InitializePane(minorScene, addition);
        return mainScene.GetScene();
    }

    private void Refactor(int count){
        corps.setCount(count);
        minorScene.setBoxes(corps.getBoxes(), count);
    }

    private void InitializeEvent(){
        select.setOnAction(event -> {
            int a = Integer.parseInt((textArea.getText()));
            Refactor(a);
            mainScene.SetSubscene(minorScene.GetSubScene());
            System.out.println("Event");
        });

        zoom_plus.setOnAction(e->{
            minorScene.ZoomPlus();
        });

        zoom_minus.setOnAction(e->{
            minorScene.ZoomMinus();
        });
        color.setOnAction(event -> {
            ColorScene colorScene = new ColorScene();
            Color color = colorScene.getColor();
            String str = colorScene.getName();
            System.out.println(color.toString());

            if(str=="фон1")
                minorScene.SetColor(color);
            if(str=="фон2")
                addition.setColor(ColorScene.toRGBCode(color));
            if(str=="фон3")
                corps.SetColor(color);

        });
        checkBox.setOnAction(event -> {
            boxes = corps.getBoxes();
            if(checkBox.isSelected()) {
                for (Box obj : corps.getBoxes()) {
                    obj.setOnMousePressed(event1 -> {
                        System.out.println("hi");
                    });
                }
                System.out.println("dont work");
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
                System.out.println(" work");

            }
        });
    }
}
