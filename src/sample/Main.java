package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
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



    static double  WIDTH, HEIGHT;
    private Scene scene;
    PerspectiveCamera camera;
    private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final double TURN_FACTOR = 0.5;
    int a = 70;
    int b = -40;
    int c = -100;
    private Group root, root1;
    int count = 4;
    public SubScene subScene;
    public Scene sceneResult;
    PhongMaterial phongMaterial;

    private List<Box> boxes = new ArrayList<>();

    public SubScene createContent() throws Exception {

        VBox strings = new VBox();
        Button but = new Button("gfhbr");
        strings.getChildren().add(but);


        camera = new PerspectiveCamera(true);
        camera.setFarClip(2000); // чтобы не пропадали объекты
        camera.setNearClip(1);
        camera.getTransforms().addAll(
                new Rotate(a, Rotate.Y_AXIS),
                new Rotate(b, Rotate.X_AXIS),
                new Rotate(0, Rotate.Z_AXIS),
                new Translate(0, 0, c)); // приближает и отдаляет камеру

        root = new Group();
        root1 = new Group();
        root.getChildren().add(camera);

        root1.getChildren().add(strings);

        double x,y,z;
        int step=5, k_x=0, k_y=0, k_z=0;
        y=z=step/1.9;
        x =step/1.9;
        phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.GREEN);

        for(int i=0; i<count*count*count; ++i){
            Box obj = new Box(4,4,4);
            obj.setOnMousePressed((MouseEvent e)->{
                if(obj.getMaterial() == (null))
                    obj.setMaterial(phongMaterial);
                else{
                    obj.setMaterial(null);
                }
            });
            if(i!=0 && i%count==0){
                    if (y > 0)
                        y = -y;
                    else {
                        y = -y;
                        y += step;
                        ++k_y;
                    }
                    if (k_y + 1 > count / 2) {
                        y = step / 1.9;
                        k_y = 0;
                    }
            }
            if(i!=0 && i%(count*count)==0){
                if (y > 0)
                    y = -y;
                else {
                    y = -y;
                    y += step;
                    ++k_y;
                }
                if (k_y + 1 > count / 2) {
                    y = step / 1.9;
                    k_y = 0;
                }

                if(z>0)
                    z=-z;
                else {
                    z=-z;
                    z += step;
                    ++k_z;
                }
                if (k_z + 1 > count / 2) {
                    z = step / 1.9;
                    k_z = 0;
                }
            }

            obj.setTranslateX(x);
            obj.setTranslateY(y);
            obj.setTranslateZ(z);
            boxes.add(obj);
            root.getChildren().add(obj);
            if(x>0)
                x=-x;
            else {
                x=-x;
                x += step;
                ++k_x;
            }
            if(k_x+1>count/2) {
                x = step/1.9;
                k_x=0;
            }
        }

        final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);

        final PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setDiffuseColor(Color.DARKBLUE);
        blueMaterial.setSpecularColor(Color.BLUE);


        final Box xAxis = new Box(240.0, 0.5, 0.5);
        final Box yAxis = new Box(0.5, 240.0, 0.5);
        final Box zAxis = new Box(0.5, 0.5, 240.0);

        xAxis.setMaterial(redMaterial);
        yAxis.setMaterial(greenMaterial);
        zAxis.setMaterial(blueMaterial);


        root.getChildren().addAll(xAxis, yAxis, zAxis);

        // Use a SubScene
        subScene = new SubScene(root, (3*WIDTH)/4, HEIGHT, true,
                SceneAntialiasing.BALANCED);
        subScene.setFill(Color.PINK);
        subScene.setCamera(camera);

        root1.getChildren().add(root);


        return subScene;
        //return new Group(subScene);
    }


    private double mousePosX, mousePosY = 0;
    private void handleMouseEvents(Stage primaryStage) {
        subScene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        primaryStage.addEventHandler(ScrollEvent.SCROLL, event -> {


            camera.getTransforms().clear();

            double q  = event.getDeltaY();
            if(q>0)
                c+=5;
            else
                c-=5;

            camera.getTransforms().addAll(
                    new Rotate(a, Rotate.Y_AXIS),
                    new Rotate(b, Rotate.X_AXIS),
                    new Rotate(0, Rotate.Z_AXIS),
                    new Translate(0, 0, c));

            System.out.println(c);


        });

        subScene.setOnMouseDragged((MouseEvent me) -> {

            double dx = (mousePosX - me.getSceneX()) ;
            double dy = (mousePosY - me.getSceneY());
            if(dx<0 && dx!=0)
                a-=2;
            else if(dx!=0)
                a+=2;
            if(dy>0 && dy!=0)
                b+=2;
            else if(dy!=0)
                b-=2;
            a=a%360;
            b=b%360;
            if (me.isPrimaryButtonDown()) {
                camera.getTransforms().clear();
                camera.getTransforms().addAll(
                        new Rotate(a, Rotate.Y_AXIS),
                        new Rotate(b, Rotate.X_AXIS),
                        new Translate(0, 0, c));

            }
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });
    }



    @Override
    public void init(){
        System.out.println("Initialize");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


        WIDTH = Screen.getPrimary().getBounds().getWidth()/2;
        HEIGHT = Screen.getPrimary().getBounds().getHeight()/2;



        BorderPane pane = new BorderPane();

        SubScene subscene = (createContent());
        pane.setLeft(subscene);


        TextArea textArea = new TextArea();
        textArea.setPrefHeight(10);
        textArea.setPrefWidth(100);

        Button select = new Button("Reset");
        select.setOnAction(e->{
        });

        Button color = new Button("Color");
        color.setOnAction(event -> {
        });


        CheckBox view = new CheckBox("View Mood");
        view.setOnAction(event -> {
            if(view.isSelected()) {
                for (Box obj : boxes) {
                    obj.setOnMousePressed(event1 -> {

                    });
                }
                System.out.println("now dont working");
            }
            else{
                for (Box obj : boxes) {
                    obj.setOnMousePressed(event1 -> {
                        if(obj.getMaterial() == (null))
                            obj.setMaterial(phongMaterial);
                        else
                            obj.setMaterial(null);
                    });
                }
                System.out.println("now  working");
            }
        });

        Button zoom_plus = new Button("Zoom +");
        zoom_plus.setOnAction(e->{

        });
        zoom_plus.setPrefSize(75,25);

        Button zoom_min = new Button("Zoom -");
        zoom_min.setOnAction(e->{

        });
        zoom_min.setPrefSize(75,25);




        AnchorPane anchorPane = new AnchorPane(textArea, select, zoom_min, zoom_plus, color, view);
        anchorPane.setStyle("-fx-background-color: #008080;");
        anchorPane.setPrefSize((WIDTH/4),HEIGHT);

        AnchorPane.setTopAnchor(textArea, HEIGHT - 350);
        AnchorPane.setRightAnchor(textArea, 45.0);

        AnchorPane.setTopAnchor(select, HEIGHT-300);
        AnchorPane.setRightAnchor(select, 68.0);

        AnchorPane.setTopAnchor(color, HEIGHT-220);
        AnchorPane.setRightAnchor(color, 68.0);

        AnchorPane.setTopAnchor(view, HEIGHT - 170);
        AnchorPane.setRightAnchor(view, 48.0);

        AnchorPane.setTopAnchor(zoom_plus, HEIGHT - zoom_plus.getPrefHeight()-20);
        AnchorPane.setRightAnchor(zoom_plus, 100.0);

        AnchorPane.setTopAnchor(zoom_min, HEIGHT-zoom_min.getPrefHeight()-20);
        AnchorPane.setRightAnchor(zoom_min, 15.0);


        pane.setRight(anchorPane);
        pane.setPrefSize(WIDTH,HEIGHT);


        sceneResult = new Scene(pane);

        sceneResult.widthProperty().addListener(new ChangeListener<Number>() {
            int b =0;
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int a = newValue.intValue();
                if(b!=0) {
                    WIDTH+=(a-b);
                    subscene.setWidth(subscene.getWidth() + (a-b));
                }
                b = a;
            }
        });
        sceneResult.heightProperty().addListener(new ChangeListener<Number>() {
            int d=0;
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int c = newValue.intValue();
                if(d!=0) {
                    HEIGHT+=(c-d);
                    subscene.setHeight(subscene.getHeight() + (c - d));
                    for(Node obj : anchorPane.getChildren()){
                        AnchorPane.setTopAnchor(obj, AnchorPane.getTopAnchor(obj) + (c-d)/2.5);
                    }
                    anchorPane.setPrefHeight(anchorPane.getHeight() + (c-d));
                }
                d=c;
            }
        });

        primaryStage.setTitle("3D");
        primaryStage.setResizable(false);
        handleMouseEvents(primaryStage);
        primaryStage.setScene(sceneResult);

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);

        primaryStage.setResizable(true);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
for(int i=0; i<count*count; ++i){
        Box obj = new Box(4,4,4);
        obj.setOnMousePressed((MouseEvent e)->{
        if(obj.getMaterial() == (null))
        obj.setMaterial(phongMaterial);
        else{
        }
        });
        if(i==1) {
        root.getChildren().add(obj);
        boxes.add(obj);
        continue;
        }

        if(i!=0 && i%count==0){
        if(y>0)
        y=-y;
        else {
        y=-y;
        y += step;
        }
        if(y/step>count/2)
        y=0;
        }
        if(i!=0 && i%(count*count)==0){
        if(y>0)
        y=-y;
        else {
        y=-y;
        y += step;
        }
        if(y/step>count/2)
        y=0;

        if(z>0)
        z=-z;
        else {
        z=-z;
        z += step;
        }
        if(z/step>count/2)
        z=0;
        }

        obj.setTranslateX(x);
        obj.setTranslateY(y);
        obj.setTranslateZ(z);
        boxes.add(obj);
        root.getChildren().add(obj);
        if(x>0)
        x=-x;
        else {
        x=-x;
        x += step;
        }
        if(x/step>count/2)
        x=0;
        }
        */
