package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


    private Scene scene;
    PerspectiveCamera camera;
    private Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);
    private Rotate rotateZ = new Rotate(0, Rotate.Z_AXIS);
    private final double TURN_FACTOR = 0.5;
    int a = 70;
    int b = -40;
    int c = -100;
    private Group root;
    int count = 5;

    private List<Box> boxes = new ArrayList<>();

    public Parent createContent() throws Exception {
        // Box

        camera = new PerspectiveCamera(true);
        camera.setFarClip(2000); // чтобы не пропадали объекты
        camera.setNearClip(1);
        camera.getTransforms().addAll(
                new Rotate(a, Rotate.Y_AXIS),
                new Rotate(b, Rotate.X_AXIS),
                new Rotate(0, Rotate.Z_AXIS),
                new Translate(0, 0, c)); // приближает и отдаляет камеру



        // Build the Scene Graph
        root = new Group();
        root.getChildren().add(camera);

        double x,y,z;
        int step=5;
        y=z=0;
        x = 5;
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
        SubScene subScene = new SubScene(root, 600, 600, true,
                SceneAntialiasing.BALANCED);
        subScene.setFill(Color.TRANSPARENT);
        subScene.setCamera(camera);

        return new Group(subScene);
    }


    private double mousePosX, mousePosY = 0;
    private void handleMouseEvents(Stage primaryStage) {
        scene.setOnMousePressed((MouseEvent me) -> {
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

            /*if(a>0) {
                for (Box ob : boxes) {
                    ob.translateZProperty().set(ob.getTranslateZ() + 10);
                    ob.translateXProperty().set(ob.getTranslateX() + 10);
                    ob.translateYProperty().set(ob.getTranslateY() + 10);
                }
            }
            else{
                for (Box ob : boxes) {
                    ob.translateZProperty().set(ob.getTranslateZ() - 10);
                    ob.translateXProperty().set(ob.getTranslateX() - 10);
                    ob.translateYProperty().set(ob.getTranslateY() - 10);
                }
            }*/
            System.out.println(c);

            /*if(camera.getTransforms().get(0).getMxx() > 0.5)
                camera.translateXProperty().set(camera.getTranslateX() + a);
            if(camera.getTransforms().get(0).getMyy() > 0.5)
                camera.translateYProperty().set(camera.getTranslateY() + a);
            if(camera.getTransforms().get(0).getMzz() > 0.5)
                camera.translateZProperty().set(camera.getTranslateZ() + a);*/
            //camera.translateZProperty().set(camera.getTranslateZ() + a); //крутая фишка
           // camera.translateXProperty().set(camera.getTranslateX() + a);
            //camera.translateYProperty().set(camera.getTranslateY() + a);

        });

        scene.setOnMouseDragged((MouseEvent me) -> {

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
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // set title for the stage
        primaryStage.setTitle("creating box");




        primaryStage.setResizable(false);
        scene = new Scene(createContent());
        handleMouseEvents(primaryStage);
        primaryStage.setScene(scene);

        //Scene scene = new Scene(group, 500, 300);

        //scene.setCamera(perspectivecamera);

        // primaryStage.setScene(scene);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}