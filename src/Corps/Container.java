package Corps;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Container extends Box {

    private double x,y,z;
    private PhongMaterial phongMaterial;

    public int id;


    public Container(double q, double w, double e){
        super(q,w,e);
        x=y=z=0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
