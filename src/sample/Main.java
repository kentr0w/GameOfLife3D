package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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



    static double WIDTH, HEIGHT;
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
    int count = 13;
    public SubScene subScene;
    public Scene sceneResult;

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
        int step=5;
        y=z=0;
        x =step;
        PhongMaterial phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.GREEN);

        for(int i=1; i<count*count*count+1; ++i){
            Box obj = new Box(4,4,4);
            obj.setOnMousePressed((MouseEvent e)->{
                if(obj.getMaterial() == (null))
                    obj.setMaterial(phongMaterial);
            });
            if(i==1) {
                root.getChildren().add(obj);
                boxes.add(obj);
                continue;
            }

            if(i%count==0){
                if(y>0)
                    y=-y;
                else {
                    y=-y;
                    y += step;
                }
                if(y/step>count/2)
                    y=0;
            }
            if(i%(count*count)==0){
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
        subScene = new SubScene(root, WIDTH/2-100, HEIGHT/2 +100, true,
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


        WIDTH = Screen.getPrimary().getBounds().getWidth();
        HEIGHT = Screen.getPrimary().getBounds().getHeight();

        SubScene scene = (createContent());
        BorderPane pane = new BorderPane();
        pane.setCenter(scene);
        TextArea textArea = new TextArea();

        pane.setStyle("-fx-background-color: #008080;");

        textArea.setPrefHeight(10);
        textArea.setPrefWidth(100);

        Button select = new Button("Reset");
        select.setOnAction(e->{
            System.out.println(sceneResult.getHeight());
        });

        Button zoom_plus = new Button("Zoom +");
        zoom_plus.setOnAction(e->{

        });
        zoom_plus.setPrefSize(75,25);

        Button zoom_min = new Button("Zoom -");
        zoom_min.setOnAction(e->{

        });
        zoom_min.setPrefSize(75,25);

        AnchorPane.setTopAnchor(textArea, 100.0);
        AnchorPane.setRightAnchor(textArea, WIDTH/13);

        AnchorPane.setTopAnchor(select, 160.0);
        AnchorPane.setRightAnchor(select, 120.0);

        AnchorPane.setTopAnchor(zoom_plus, 480.0);
        AnchorPane.setRightAnchor(zoom_plus, 108.0);

        AnchorPane.setTopAnchor(zoom_min, 480.0);
        AnchorPane.setRightAnchor(zoom_min, 30.0);



        AnchorPane anchorPane = new AnchorPane(textArea, select,zoom_plus,zoom_min);

        anchorPane.setPrefSize(170,150);

        pane.setRight(anchorPane);
        pane.setPrefSize(700,700);

        sceneResult = new Scene(pane);



        primaryStage.setTitle("3D");
        primaryStage.setResizable(false);
        handleMouseEvents(primaryStage);
        primaryStage.setScene(sceneResult);

        System.out.println(sceneResult.getFill().toString());

        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(HEIGHT/2+100);
        primaryStage.setMinWidth(WIDTH/2);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}