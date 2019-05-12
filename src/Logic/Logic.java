package Logic;

import Constatnce.Constance;
import Corps.Container;
import Corps.Square;
import javafx.application.Platform;
import java.util.ArrayList;


public class Logic {

    private Thread thread;
    private ArrayList<Container> boxes;
    private ArrayList<Container> choses;
    private ArrayList<Container> choses1;
    private ArrayList<Square[][]> array = new ArrayList<>(Constance.getCOUNT());

    private ArrayList<Integer> xAxic = new ArrayList<>();
    private ArrayList<Integer> yAxic = new ArrayList<>();
    private ArrayList<Integer> zAxic = new ArrayList<>();

    private ArrayList<Integer> notxAxic = new ArrayList<>();
    private ArrayList<Integer> notyAxic = new ArrayList<>();
    private ArrayList<Integer> notzAxic = new ArrayList<>();
    private boolean finish = true;


    public Logic(){
    }
    public void setup(ArrayList<Container> boxes){
        this.boxes = boxes;
        Initialize();
        qwerty();

        thread = new Thread(() -> {
            Runnable updater = () -> Logic.this.Run(choses);
            while (!isEnd() & finish) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                }
                Platform.runLater(updater);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                }
                AfterRun();
            }
        });
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

    public void setChoses(ArrayList<Container> choses){
        this.choses = choses;
    }
    public void rr(){
        finish = true;
        if(choses.isEmpty())
            return;
        thread.setDaemon(true);
        thread.start();
        System.out.println("1 = " + thread.isInterrupted());
    }
    private void Run(ArrayList<Container> choses){

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
                                    else{
                                        notxAxic.add(x+w);
                                        notyAxic.add(y+e);
                                        notzAxic.add(z+q);
                                    }
                                } catch (Exception ex) {

                                }
                        }
                    }
                }
            }
            choses.clear();

        for (int i = 0; i < xAxic.size(); ++i) {
            array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox().setMaterial(Constance.getPhongMaterial());
            if (!choses.contains(array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox()))
                choses.add(array.get(zAxic.get(i))[xAxic.get(i)][yAxic.get(i)].getBox());
        }
        for(int i=0; i<notxAxic.size(); ++i)
            array.get(notzAxic.get(i))[notxAxic.get(i)][notyAxic.get(i)].getBox().setMaterial(null);

            xAxic.clear();
            yAxic.clear();
            zAxic.clear();

            notxAxic.clear();
            notyAxic.clear();
            notzAxic.clear();
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
        return count >= Constance.getCREATEMIN() && count <= Constance.getCREATEMAX();
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
        return count >= Constance.getALIVEMIN() && count <= Constance.getALIVEMAX();
    }

    private void AfterRun(){
        for(Container container: boxes){
            container.setOnMousePressed(event -> {
                if(container.getMaterial()==null) {
                    container.setMaterial(Constance.getPhongMaterial());
                    choses.add(container);

                }
                else {
                    container.setMaterial(null);
                    choses.remove(container);
                }
            });
        }
    }

    private boolean isEnd(){
        if(choses.isEmpty()) {
            System.out.println("1");
            return true;
        }
        if(choses1 == null) {
            choses1 = (ArrayList<Container>) choses.clone();
            return false;
        }
        if(choses1.size()!=choses.size())
            return false;
        else {
            for(int i=0; i<choses.size(); ++i) {
                if(choses.get(i)!=choses1.get(i)) {
                    choses1 = (ArrayList<Container>)choses.clone();
                    System.out.println("3");
                    return false;
                }
            }
            System.out.println("2");
            return true;
        }
    }

    public void Clean(){
        finish = false;
        xAxic.clear();
        yAxic.clear();
        zAxic.clear();
        notxAxic.clear();
        notyAxic.clear();
        notzAxic.clear();
    }
}
