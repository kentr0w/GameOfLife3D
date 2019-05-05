package Corps;

import Constatnce.Constance;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;

public class Corps {
    private static int count;
    private static ArrayList<Box> boxes;
    private static PhongMaterial phongMaterial;

    {
        phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.GREEN);
    }

    public Corps(int count){

        Corps.count = count;
        boxes = new ArrayList<>();
    }

    public Corps(){

    }
    private void Generation(){
        for(int i=0; i<count*count*count; ++i){
            Box box = new Box(Constance.SIZE,Constance.SIZE, Constance.SIZE);
            box.setOnMousePressed(event -> {
                if(box.getMaterial()==null)
                    box.setMaterial(phongMaterial);
                else
                    box.setMaterial(null);
            });
            boxes.add(box);
        }
    }

    public void setMaterial(Box box){
        box.setMaterial(phongMaterial);
    }
    public ArrayList<Box> getBoxes(){
        return boxes;
    }
    public void setCount(int count){
        Corps.count = count;
        boxes.clear();
        Generation();
    }
    public void SetColor(Color color){
        phongMaterial.setDiffuseColor(color);
        Generation();
        for(Box obj: boxes){
            if(obj.getMaterial()!=null)
                obj.setMaterial(phongMaterial);
        }
    }
}

















//TODO сделать boxes.clear только при перересовки ипосле этого чтобы все брали новый список