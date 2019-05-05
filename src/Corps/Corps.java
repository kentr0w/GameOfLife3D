package Corps;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;

public class Corps {
    private static int size, count;
    private static ArrayList<Box> boxes;
    private  PhongMaterial phongMaterial;

    {
        phongMaterial = new PhongMaterial();
        phongMaterial.setDiffuseColor(Color.GREEN);
    }

    public Corps(int size, int count){
        Corps.size = size;
        Corps.count = count;
        boxes = new ArrayList<>();
    }

    public Corps(){

    }
    private void Generation(){
        for(int i=0; i<count*count*count; ++i){
            Box box = new Box(size,size, size);
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
}

















//TODO сделать boxes.clear только при перересовки ипосле этого чтобы все брали новый список