package Service;

import Constatnce.Constance;
import Corps.Container;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import java.util.ArrayList;

public class MinorScene {
    private static SubScene subScene;
    private Group root;
    private Camera camera;
    private ArrayList<Container> boxes;
    private double mousePosX, mousePosY;

    {
        root = new Group();
        camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(1000);
        camera.getTransforms().addAll(
                new Rotate(Constance.getX(), Rotate.Y_AXIS),
                new Rotate(Constance.getY(), Rotate.X_AXIS),
                new Rotate(0, Rotate.Z_AXIS),
                new Translate(0,0, Constance.getZ()));
        root.getChildren().add(camera);
    }

    public MinorScene(ArrayList<Container> boxes){
        this.boxes = boxes;
    }

    private void CreatContent(boolean iseven){
        root.getChildren().clear();
        root.getChildren().add(camera);
        double x=0,y=0,z=0;
        int k_x=0, k_y=0, k_z=0;
        if(iseven)
            x=y=z=Constance.getKEVEN();

        for(int i=0; i<boxes.size(); ++i){
            Container obj = boxes.get(i);
            if(i!=0 && i%Constance.getCOUNT()==0){
                if (y > 0)
                    y = -y;
                else {
                    y = -y;
                    y += Constance.getSTEP();
                    ++k_y;
                }
                if(iseven) {
                    if (k_y + 1 > Constance.getCOUNT() / 2) {
                        y = Constance.getKEVEN();
                        k_y = 0;
                    }
                }
                    else
                    if(k_y>Constance.getCOUNT()/2) {
                        y = 0;
                        k_y=0;
                    }
                if(i%(Constance.getCOUNT()*Constance.getCOUNT())==0){
                    if(z>0)
                        z=-z;
                    else {
                        z=-z;
                        z += Constance.getSTEP();
                        ++k_z;
                    }
                    if(iseven) {
                        if (k_z + 1 > Constance.getCOUNT() / 2) {
                            z = Constance.getKEVEN();
                            k_z = 0;
                        }
                    }
                    else
                        if(k_z>Constance.getCOUNT()/2) {
                            z = 0;
                            k_z=0;
                        }
                }
            }
            obj.setTranslateX(x);
            obj.setTranslateY(y);
            obj.setTranslateZ(z);
            obj.setX(x);
            obj.setY(y);
            obj.setZ(z);
            root.getChildren().add(obj);
            if(x>0)
                x=-x;
            else {
                x=-x;
                x += Constance.getSTEP();
                ++k_x;
            }
            if(iseven) {
                if (k_x + 1 > Constance.getCOUNT() / 2) {
                    x = Constance.getKEVEN();
                    k_x = 0;
                }
            }
            else
                if(k_x>Constance.getCOUNT()/2) {
                    x = 0;
                    k_x=0;
                }

        }
    }

    private void AddAxis(){
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
    }

    private void Scrol(double shift){
        camera.getTransforms().clear();
        if(shift>0)
            Constance.setZ(Constance.getZ()+5);
        else
            Constance.setZ(Constance.getZ()-5);
        camera.getTransforms().addAll(
                new Rotate(Constance.getX(), Rotate.Y_AXIS),
                new Rotate(Constance.getY(), Rotate.X_AXIS),
                new Rotate(0, Rotate.Z_AXIS),
                new Translate(0, 0, Constance.getZ()));
    }

    private void handleMouseEvents(){
        subScene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        subScene.addEventHandler(ScrollEvent.SCROLL, event -> {
            Scrol(event.getDeltaY());
        });

        subScene.setOnMouseDragged((MouseEvent me) -> {

            double dx = (mousePosX - me.getSceneX()) ;
            double dy = (mousePosY - me.getSceneY());
            if(dx<0 && dx!=0)
                Constance.setX(Constance.getX()-2);
            else if(dx!=0)
                Constance.setX(Constance.getX()+2);
            if(dy>0 && dy!=0)
                Constance.setY(Constance.getY()+2);
            else if(dy!=0)
                Constance.setY(Constance.getY()-2);
            Constance.setX(Constance.getX()%360);
            Constance.setY(Constance.getY()%360);
            if (me.isPrimaryButtonDown()) {
                camera.getTransforms().clear();
                camera.getTransforms().addAll(
                        new Rotate(Constance.getX(), Rotate.Y_AXIS),
                        new Rotate(Constance.getY(), Rotate.X_AXIS),
                        new Translate(0, 0, Constance.getZ()));

            }
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });
    }

    public SubScene GetSubScene(){
        if(boxes.size()!=0) {
            CreatContent(boxes.size()%2==0);
        }
        else {
            root.getChildren().clear();
            root.getChildren().add(camera);
        }
        AddAxis();
        if(subScene==null)
            subScene = new SubScene(root, (3*Constance.getWIDTH())/4, Constance.getHEIGHT(), true,
                SceneAntialiasing.BALANCED);
        else
            subScene.setRoot(root);
        subScene.setFill(Constance.getPhongMaterialMinor().getDiffuseColor());
        subScene.setCamera(camera);
        handleMouseEvents();
        return subScene;
    }

    public void SetHeigth(int shift){
        this.subScene.setHeight(subScene.getHeight() + shift);
    }

    public void SetWidth(int shift){
        this.subScene.setWidth(subScene.getWidth() + shift);
    }
    public void ZoomPlus(){
        Scrol(1);
    }

    public void ZoomMinus(){
        Scrol(-1);
    }
    public void setBoxes(ArrayList<Container> boxes){
        this.boxes = boxes;
    }

    public void SetColor(){
        subScene.setFill(Constance.getPhongMaterialMinor().getDiffuseColor());
    }


}
