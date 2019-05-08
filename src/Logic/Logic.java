package Logic;

import Constatnce.Constance;
import Corps.Container;
import Corps.Square;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;


public class Logic {

    private int count;
    private ArrayList<Container> boxes;
    private ArrayList<Square[][]> array;

    public Logic(int count, ArrayList<Container> boxes){
        this.count = count;
        this.boxes = boxes;
        array = new ArrayList<>(count);
        Initialize();
        qwerty();
    }

    public ArrayList<Square[][]> getArray(){
        return array;
    }

    private void Initialize(){
        for(int k=0; k<count; ++k) {
            Square[][] matrix = new Square[count][count];
            array.add(matrix);
        }
    }

    private int getx_odd(Container obj){
        return Math.abs((int)(obj.getX()/Constance.getSTEP())  - (Constance.getCOUNT()/2));
    }
    private int gety_odd(Container obj){
        return Math.abs((int)(obj.getY()/Constance.getSTEP())  - (int)(Constance.getCOUNT()/2));
    }

    private int getz_odd(Container obj){
        return Math.abs((int)(obj.getZ()/Constance.getSTEP()) + ((Constance.getCOUNT()/2)));
    }
    private int getz_even(Container obj){
        int z;
        double t = obj.getZ();
        if (t < 0) {
            t += Constance.getKEVEN();
            z = Math.abs(Math.abs((int) (t) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2) + 1);
        }
        else {
            t -= Constance.getKEVEN();
            z = Math.abs(((int) (t) / Constance.getSTEP()) + (int) (Constance.getCOUNT() / 2));
        }
        return z;
    }

    private int getx_even(Container obj){
        int x;
        double q = obj.getX();
        if (q < 0) {
            q += Constance.getKEVEN();
            x = Math.abs(((int) (q) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2));
        } else {
            q -= Constance.getKEVEN();
            x = Math.abs(((int) (q) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2) + 1);
        }
        return x;
    }

    private int gety_even(Container obj){
        int y;
        double w = obj.getY();
        if (w < 0) {
            w += Constance.getKEVEN();
            y = Math.abs(((int) (w) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2));

        } else {
            w -= Constance.getKEVEN();
            y = Math.abs(((int) (w) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2) + 1);
        }
        return y;
    }

    private void qwerty(){
        int x=0, y=0,z=0;
        for(int i=0; i<boxes.size(); ++i) {
            Container obj = boxes.get(i);
            if(boxes.size()%2!=0){
                z = getz_odd(obj);// Math.abs((int)(obj.getZ()/Constance.getSTEP()) + ((Constance.getCOUNT()/2))) ;
                x = getx_odd(obj);//Math.abs((int)(obj.getX()/Constance.getSTEP())  - (int)(Constance.getCOUNT()/2));//Math.abs(((int) (obj.getX()) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2) + 1);
                y = gety_odd(obj);//)Math.abs((int)(obj.getY()/Constance.getSTEP())  - (int)(Constance.getCOUNT()/2));//Math.abs(((int) (obj.getY()) / Constance.getSTEP()) - (int) (Constance.getCOUNT() / 2) + 1);
        }
        else{
            z = getz_even(obj);

            x = getx_even(obj);

            y = gety_even(obj);

        }
            array.get(z)[x][y] = new Square(obj);
            if(obj.getMaterial()!=null)
                array.get(z)[x][y].setAlive(true);
        }
    }

    public void AddToMatrix(Square box){

    }

    public void Run(ArrayList<Container> choses){
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.BLUE);
        ArrayList<Integer> xAxic = new ArrayList<Integer>();
        ArrayList<Integer> yAxic = new ArrayList<Integer>();
        ArrayList<Integer> zAxic = new ArrayList<Integer>();

        for(int i=0; i<choses.size(); ++i){
            int x,y,z;
            if(Constance.getCOUNT()%2!=0){
                z = getz_odd(choses.get(i));
                x =getx_odd(choses.get(i));
                y = gety_odd(choses.get(i));
            }
            else{
                z = getz_even(choses.get(i));
                x = getx_even(choses.get(i));
                y = gety_even(choses.get(i));
            }

            for(int q=-1; q<2; ++q){
                for(int w=-1; w<2; ++w){
                    for(int e=-1; e<2; ++e){
                        if(q==0 && w==0 && e==0){
                            if(isDie(z,x,y))
                                array.get(z)[x][y].getBox().setMaterial(material);
                            else
                                array.get(z)[x][y].getBox().setMaterial(null);
                        }
                        else
                            try {
                                Container qwr = array.get(z+q)[x+w][y+e].getBox();
                                if (extra(z + q, x + w, y + e)) {
                                    xAxic.add(x + w);
                                    yAxic.add(y + e);
                                    zAxic.add(z + q);
                                 }
                            }
                            catch (Exception ex){
                            }

                    }
                }
            }
        }
        for(int i=0; i<xAxic.size(); ++i){
            array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox().setMaterial(material);
        }
        xAxic.clear();
        yAxic.clear();
        zAxic.clear();
    }

    private boolean extra(int z, int x, int y) {
        int count=0;
        for (int q = -1; q < 2; ++q) {
            for (int w = -1; w < 2; ++w) {
                for (int e = -1; e < 2; ++e) {
                    try {
                        if(array.get(z+q)[x+w][y+e].getBox().getMaterial()!=null){
                            ++count;
                        }
                    } catch (Exception ex) {

                    }
                }

            }
        }
        return count >= 6 && count <= 9;
    }

    private boolean isDie(int z, int x, int y){
        int count=0;
        for (int q = -1; q < 2; ++q) {
            for (int w = -1; w < 2; ++w) {
                for (int e = -1; e < 2; ++e) {
                    try {
                        if(array.get(z+q)[x+w][y+e].getBox().getMaterial()!=null){
                            ++count;
                        }
                    } catch (Exception ex) {

                    }
                }

            }
        }
        return count >= 5 && count <= 10;
    }
}
