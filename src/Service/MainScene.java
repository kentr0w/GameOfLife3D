package Service;

import Constatnce.Constance;
import Factory.Factory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;

import java.util.ArrayList;

public class MainScene {

    private Scene result;
    private BorderPane pane;
    private MinorScene minorScene;
    private Addition addition;

    public MainScene(){
    }

    public void InitializePane(MinorScene minorScene,Addition addition) {
        this.minorScene = minorScene;
        this.addition = addition;
        pane = new BorderPane();
        pane.setLeft(minorScene.GetSubScene());
        pane.setPrefSize(Constance.getWIDTH(), Constance.getHEIGHT());
        pane.setRight(addition.GetAnchor());
        result = new Scene(pane);
        InitializeScene();
    }

    private void InitializeScene(){

        result.widthProperty().addListener(new ChangeListener<Number>() {
            int b =0;
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int a = newValue.intValue();
                if(b!=0) {
                    Constance.setWIDTH((a-b));
                    minorScene.SetWidth((a-b));
                }
                b = a;
            }
        });
        result.heightProperty().addListener(new ChangeListener<Number>() {
            int d=0;
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int c = newValue.intValue();
                if(d!=0) {
                    Constance.setHEIGHT((c-d));
                    minorScene.SetHeigth((c - d));
                    addition.ChangeSize();
                }
                d=c;
            }
        });
    }
    public Scene GetScene(){
        return result;
    }

    public void SetSubscene(SubScene subScene){
        this.pane.setLeft(subScene);
        InitializeScene();
    }
}
