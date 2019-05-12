package sample;

import Constatnce.Constance;
import Corps.Corps;
import Corps.Container;
import Factory.Factory;
import Service.Controller;
import Service.MainScene;
import Service.MinorScene;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


    //TODO
    // Отсановка программы чтобы не портить узор!
    // Генерация новых блоков


    @Override
    public void init(){
        System.out.println("Initialize");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        int count =6;

        double minheight = Constance.getHEIGHT();
        double minwidth = Constance.getWIDTH();

        Factory factory = new Factory();
        factory.setCount(count);

        Corps corps = factory.getCorps();
        ArrayList<Container> boxes = corps.getBoxes();

        factory.setBoxes(boxes);

        Controller controller = new Controller(factory);

        Scene scene = controller.build();

        primaryStage.setTitle("3D");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event ->System.exit(0));
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(minheight);
        primaryStage.setMinWidth(minwidth);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}

