package Factory;

import Corps.Corps;
import Logic.Logic;
import Service.Addition;
import Service.MainScene;
import Service.MinorScene;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import Corps.Container;

import java.util.ArrayList;

public class Factory {

    private static MinorScene minorScene;
    private static MainScene mainScene;
    private static Addition addition;
    private static ArrayList<Container> boxes;
    private static Logic logic;
    private Corps corps;
    private int count;

    public Factory(){
    }

    public void setCount(int count){
        this.count = count;
    }
    public void setBoxes(ArrayList<Container> boxes){
        Factory.boxes = boxes;
    }

    public MinorScene GetMinor(){
        if(minorScene == null)
            minorScene = new MinorScene(Factory.boxes);
        return minorScene;
    }


    public MainScene GetMain(){
        if(mainScene == null)
            mainScene = new MainScene();
        return mainScene;
    }

    public Addition GetAddition(){
        if(addition == null)
            addition = new Addition();
        return addition;
    }
    public Corps getCorps(){
        if(corps == null)
            corps = new Corps();
        return corps;
    }

    public Logic getLogic(){
        if(logic == null)
            logic = new Logic(corps.getBoxes());
        return logic;
    }
}