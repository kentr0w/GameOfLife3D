package sample;

import Constatnce.Constance;
import Control.Controller;
import Corps.Corps;
import Corps.Container;
import Factory.Factory;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {


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

        Scene scene = controller.Build();

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

