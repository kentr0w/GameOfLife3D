package Logic;

import Constatnce.Constance;
import Corps.Container;
import Corps.Square;
import Service.MainScene;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;


public class Logic {

    private ArrayList<Container> boxes;
    private ArrayList<Square[][]> array = new ArrayList<>(Constance.getCOUNT());

    private ArrayList<Integer> xAxic = new ArrayList<>();
    private ArrayList<Integer> yAxic = new ArrayList<>();
    private ArrayList<Integer> zAxic = new ArrayList<>();

    private ArrayList<Integer> notxAxic = new ArrayList<>();
    private ArrayList<Integer> notyAxic = new ArrayList<>();
    private ArrayList<Integer> notzAxic = new ArrayList<>();

    public Logic(ArrayList<Container> boxes){
        this.boxes = boxes;
        Initialize();
        qwerty();
    }

    private void Initialize(){
        for(int k=0; k<Constance.getCOUNT(); ++k) {
            Square[][] matrix = new Square[Constance.getCOUNT()][Constance.getCOUNT()];
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
                z = getz_odd(obj);
                x = getx_odd(obj);
                y = gety_odd(obj);
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

    private int rty=0;


    public void rr(ArrayList<Container> choses){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        Run(choses);
                        rty++;
                        System.out.println(rty);
                    }
                };

                while (rty<5) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    Platform.runLater(updater);
                }
            }
        });
        try {
            thread.join();
        }catch (InterruptedException ex){
        }
        thread.start();

        System.out.println("Hello");
        thread.interrupt();
    }
    public void Run(ArrayList<Container> choses){

            for (Container chose : choses) {
                int x, y, z;
                if (Constance.getCOUNT() % 2 != 0) {
                    z = getz_odd(chose);
                    x = getx_odd(chose);
                    y = gety_odd(chose);
                } else {
                    z = getz_even(chose);
                    x = getx_even(chose);
                    y = gety_even(chose);
                }

                for (int q = -1; q < 2; ++q) {
                    for (int w = -1; w < 2; ++w) {
                        for (int e = -1; e < 2; ++e) {
                            if (q == 0 && w == 0 && e == 0) {
                                if (isDie(z, x, y)) {
                                    xAxic.add(x);
                                    yAxic.add(y);
                                    zAxic.add(z);
                                }
                                else{
                                    notxAxic.add(x);
                                    notyAxic.add(y);
                                    notzAxic.add(z);
                                }
                            } else
                                try {
                                    Container qwr = array.get(z + q)[x + w][y + e].getBox();
                                    if (extra(z + q, x + w, y + e)) {
                                        xAxic.add(x + w);
                                        yAxic.add(y + e);
                                        zAxic.add(z + q);
                                    }
                                } catch (Exception ex) {

                                }
                        }
                    }
                }
            }
            choses.clear();

            draw(choses,  xAxic,  yAxic,  zAxic,  notxAxic,  notyAxic,  notzAxic);

            xAxic.clear();
            yAxic.clear();
            zAxic.clear();

            notxAxic.clear();
            notyAxic.clear();
            notzAxic.clear();
    }

    private void draw(ArrayList<Container> choses, ArrayList<Integer> xAxic, ArrayList<Integer> yAxic, ArrayList<Integer> zAxic, ArrayList<Integer> notxAxic, ArrayList<Integer> notyAxic, ArrayList<Integer> notzAxic){
        for (int i = 0; i < xAxic.size(); ++i) {
            array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox().setMaterial(Constance.getPhongMaterial());
            if (!choses.contains(array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox()))
                choses.add(array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox());
        }
        for(int i=0; i<notxAxic.size(); ++i)
            array.get(notzAxic.get(i))[notxAxic.get(i)][notyAxic.get(i)].getBox().setMaterial(null);
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

    public void Clean(){
        xAxic.clear();
        yAxic.clear();
        zAxic.clear();
        notxAxic.clear();
        notyAxic.clear();
        notzAxic.clear();
    }
}
