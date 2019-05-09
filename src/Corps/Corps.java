package Corps;

import Constatnce.Constance;
import javafx.scene.shape.Box;

import java.util.ArrayList;

public class Corps {
    private static ArrayList<Container> boxes;
    private static ArrayList<Container> choses;


    public Corps(){
        boxes = new ArrayList<>(Constance.getCOUNT()*Constance.getCOUNT()*Constance.getCOUNT());
        choses = new ArrayList<Container>();
    }

    private void Generation(){
        for(int i=0; i<Constance.getCOUNT()*Constance.getCOUNT()*Constance.getCOUNT(); ++i){
            Container box = new Container(Constance.SIZE,Constance.SIZE, Constance.SIZE);
            box.setOnMousePressed(event -> {
                if(box.getMaterial()==null) {
                    box.setMaterial(Constance.getPhongMaterial());
                    choses.add(box);
                }
                else {
                    box.setMaterial(null);
                    choses.remove(box);
                }
            });
            box.id = i;
            boxes.add(box);
        }
    }

    public void setMaterial(Box box){
        box.setMaterial(Constance.getPhongMaterial());
    }
    public ArrayList<Container> getBoxes(){
        return boxes;
    }
    public void setCount(){
        boxes.clear();
        Generation();
    }
    public void SetColor(){
        Generation();
        for(Box obj: boxes){
            if(obj.getMaterial()!=null) {
                obj.setMaterial(Constance.getPhongMaterial());
            }
        }
    }

    public ArrayList<Container> getChoses(){
        return choses;
    }
    public void Clean(){
        choses.clear();
    }
}