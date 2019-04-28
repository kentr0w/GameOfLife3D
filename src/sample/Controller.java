package sample;

import com.sun.org.apache.xml.internal.security.c14n.CanonicalizerSpi;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import sun.java2d.loops.FillRect;
import javafx.scene.paint.PhongMaterial;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.*;

import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.scene.Camera.*;

public class Controller {


    @FXML
    private BorderPane borderPane;

    @FXML
    private Canvas Canvas;

    @FXML
    private Button Button;

    @FXML
    private Label Label;

    private static Field[][] array = new Field[70][70];

    private double anchorX, anchorY;
    private double anchorAngleX=0;
    private double anchorAngleY=0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @FXML
    public void initialize(URL location, ResourceBundle resourceBundle){


/*
        Material mat = new PhongMaterial();
        ((PhongMaterial) mat).setSpecularColor(Color.BLUE);
        ((PhongMaterial) mat).setDiffuseColor(Color.RED);
        Sphere sphere = new Sphere(50);
        Box box = new Box(100, 100, 100);

        box.translateXProperty().set(778/2);
        box.translateYProperty().set(753/2);
        box.translateZProperty().set(-1200);

        Transform transform = new Rotate(65, new Point3D(1,0,0));
        box.getTransforms().add(transform);


        box.setMaterial(mat);
        sphere.setMaterial(mat);
        Camera camera = new PerspectiveCamera(true);


        SmartGroup group = new SmartGroup();
        group.getChildren().add(box);
        borderPane.getChildren().add(group);
        Stage stage = (Stage)Canvas.getScene().getWindow();*/

       /* GraphicsContext g = Canvas.getGraphicsContext2D();

        g.fillRect(0,0,1, Canvas.getHeight());
        g.fillRect(0,0,Canvas.getWidth(), 1);
        g.fillRect(Canvas.getWidth()-1,0,1, Canvas.getHeight());
        g.fillRect(0,Canvas.getHeight()-1,Canvas.getWidth(), 1);
        for(int i=0; i<Canvas.getHeight(); i+=10)
        {
            g.fillRect(0, i, Canvas.getWidth(), 1);
            g.fillRect(i, 0, 1, Canvas.getHeight());
        }

        for(int i=0; i<4900; i++){
            array[i%70][i/70]= new Field((i%70)*10, (i/70)*10);
        }
        for(int i=0; i<70; i++) {
            for (int j = 0; j < 70; j++) {
                if (i != 0)
                    array[i][j].setUpField(array[i - 1][j]);
                if (j != 69)
                    array[i][j].setNextField(array[i][j + 1]);
                if (i != 69)
                    array[i][j].setDownField(array[i + 1][j]);
                if (j != 0)
                    array[i][j].setPrevField(array[i][j - 1]);
            }
        }
        g.setFill(Color.GREEN);
        Canvas.setOnMouseClicked(event -> {
            int x = (int)event.getX()/10;
            int y = (int)event.getY()/10;
            array[y][x].IsLive=true;
            g.fillRect(array[x][y].X_start+3,array[x][y].Y_start+3,5,5);
        });*/

    }

    @FXML
    private  void MouseClick(){
        /*GraphicsContext g = Canvas.getGraphicsContext2D();
        Label.setText("QWE");
        g.setFill(Color.RED);
        Generation(array);
        for(int i=0; i<70; ++i)
            for(int j=0; j<70; ++j)
                if(array[i][j].WillLive)
                    g.fillRect(array[j][i].X_start+3,array[j][i].Y_start+3,5,5);*/

    }
    private void Generation(Field[][] arr){
        int count=0;
        for(int i=0; i<70; ++i){
            for(int j=0; j<70; ++j){
                    for(int q=-1; q<2;++q) {
                        for (int w = -1; w < 2; ++w) {
                            try {
                                if (arr[i + q][j + w].IsLive)
                                    count++;
                            }
                            catch (ArrayIndexOutOfBoundsException ex){
                            }
                        }
                    }
                    if(arr[i][j].IsLive)
                        if(count!=3 && count!=4)
                            arr[i][j].WillLive=false;
                        else
                            arr[i][j].WillLive=true;
                    else
                        if(count==3)
                            arr[i][j].WillLive=true;
                        else
                            arr[i][j].WillLive=false;
                    count=0;
                }
            }

        }

        /*private void inintMouseControl(Group group, Scene scene){
            Rotate xRotate, yRotate;
            group.getTransforms().addAll(
                    xRotate = new Rotate(0, Rotate.X_AXIS),
                    yRotate = new Rotate(0, Rotate.Y_AXIS)
            );

            xRotate.angleProperty().bind(angleX);
            yRotate.angleProperty().bind(angleY);

            scene.setOnMousePressed(event -> {
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
                anchorAngleX = angleX.get();
                anchorAngleY = angleY.get();
            });

            scene.setOnMouseDragged(event -> {
                angleX.set(anchorAngleX-(anchorY-event.getSceneY()));
                angleY.set(anchorAngleY + anchorX - event.getSceneX());
            });
        }*/

    }
    /*class SmartGroup extends Group{
    Rotate r;
    Transform t  = new Rotate();
    void rotateByX(int ang){
        r = new Rotate(ang, Rotate.X_AXIS);
        t = t.createConcatenation(r);
        this.getTransforms().clear();
        this.getTransforms().addAll(t);
    }
        void rotateByY(int ang){
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }*/
