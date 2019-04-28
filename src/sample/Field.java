package sample;

public class Field
        extends AbstractField {
    public Field(Field field){
        this.X_start = field.X_start;
        this.X_end = field.X_end;
        this.Y_start = field.Y_start;
        this.Y_end = field.Y_end;
        next = field.next;
        prev = field.prev;
        up = field.up;
        down = field.down;
        this.IsLive = field.IsLive;
        this.WillLive=field.WillLive;
    }
    public Field(double x, double y){
        X_start=x;
        Y_start=y;
        X_end = X_start +shift;
        Y_end = Y_start+shift;
    }
    @Override
    public Field getField() {
        return this;
    }
    @Override
    public Field getDownField() {
        return down;
    }
    @Override
    public Field getUpField() {
        return up;
    }
    @Override
    public Field getNextField() {
        return next;
    }
    @Override
    public Field getPrevField() {
        return prev;
    }
    @Override
    public void setNextField(Field field) {
        next = field;
    }
    @Override
    public void setDownField(Field field) {
        down=field;
    }
    @Override
    public void setPrevField(Field field) {
        prev=field;
    }
    @Override
    public void setUpField(Field field) {
        up=field;
    }
}