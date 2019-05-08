package Corps;

import javafx.scene.shape.Box;

public class Square {
    private Container box;
    private boolean IsAlive;
    public Square(Container box){
        this.box = box;
        IsAlive = false;
    }
    public Container getBox(){
        return box;
    }
    public void setAlive(boolean q){
        IsAlive =q;
    }
    public boolean getIsAlive(){
        return IsAlive;
    }

}
