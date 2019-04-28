package sample;

abstract public class AbstractField {
    protected static final int shift = 10;
    double X_start, Y_start, X_end, Y_end;
    Field next, prev, up, down;
    boolean IsLive;
    boolean WillLive;
    public abstract Field getField();
    public abstract Field getNextField();
    public abstract Field getPrevField();
    public abstract Field getUpField();
    public abstract Field getDownField();
    public abstract void setNextField(Field field);
    public abstract void setPrevField(Field field);
    public abstract void setDownField(Field field);
    public abstract void setUpField(Field field);
}