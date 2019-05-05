package Constatnce;

import javafx.stage.Screen;
import javafx.stage.Window;

public final class Constance {

    private   static  double WIDTH  = Screen.getPrimary().getBounds().getWidth()/2;
    private static   double HEIGHT = Screen.getPrimary().getBounds().getHeight()/2;
    public static int SIZE = 4;
    private static  int X = 70;
    private static  int Y = -40;
    private static  int Z = -100;
    public static final int STEP = 5;
    public static final double KEVEN = STEP/1.9;

    public static void setWIDTH(double shift){
        WIDTH+=shift;
    }

    public static void setHEIGHT(double shift){
        HEIGHT+=shift;
    }

    public static double getWIDTH(){
        return WIDTH;
    }
    public static double getHEIGHT(){
        return HEIGHT;
    }

    public static  double TOPTEXT(){
        return  HEIGHT - HEIGHT/1.2;
    }
    public static  double TOPSELECT(){
        return  HEIGHT - HEIGHT/1.4;
    }
    public static  double TOPCOLOR(){
        return  HEIGHT - HEIGHT/2;
    }
    public static  double TOPCHECK(){
        return  HEIGHT - HEIGHT/2.9;
    }
    public static  double TOPZOOMP(){
        return  HEIGHT - HEIGHT/5.8;
    }
    public static  double TOPZOOMM(){
        return  HEIGHT - HEIGHT/5.8;
    }

    public static double RIGHTTEXT = WIDTH/16.5;
    public static double RIGHTSELECT = WIDTH/12;
    public static double RIGHTCOLOR = WIDTH/12;
    public static double RIGHTCHECK = WIDTH/14;
    public static double RIGHTZOOMP = WIDTH/6.7;
    public static double RIGHTZOOMM = WIDTH/20;


    public static int getZ() {
        return Z;
    }

    public static void setZ(int z) {
        Z= z;
    }

    public static int getX() {
        return X;
    }

    public static void setX(int x) {
        X= x;
    }

    public static int getY() {
        return Y;
    }

    public static void setY(int y) {
        Y= y;
    }
}
