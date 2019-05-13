package Constatnce;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Screen;

public final class Constance {

    private   static  double WIDTH  = Screen.getPrimary().getBounds().getWidth()/2 + 150;
    private static   double HEIGHT = Screen.getPrimary().getBounds().getHeight()/2 + 150;
    public static int SIZE = 4;
    private static  int X = 70;
    private static  int Y = -40;
    private static  int Z = -200;
    private static  int STEP = 5;
    private static int COUNT;
    private static PhongMaterial phongMaterial = new PhongMaterial(Color.web("#FFCC00"));
    private static PhongMaterial phongMaterialMain = new PhongMaterial(Color.GREEN);
    private static PhongMaterial phongMaterialMinor = new PhongMaterial(Color.web("#F0F8FF"));
    public static int ALIVEMIN = 5;
    public static int ALIVEMAX = 8;
    public static int CREATEMIN = 6;
    public static int CREATEMAX = 9;
    public static long SPEED = 500;



    public static void setWIDTH(double shift){
        WIDTH+=shift;
        RIGHTTEXT+=shift/2;
        RIGHTCHECK+=shift/2;
        RIGHTCOLOR+=shift/2;
        RIGHTLABEL1+=shift/2;
        RIGHTLABEL2+=shift/2;
        RIGHTLABEL3+=shift/2;
        RIGHTPAUSE+=shift/2;
        RIGHTRESET+=shift/2;
        RIGHTRUN+=shift/2;
        RIGHTSELECT+=shift/2;
        RIGHTSLIDER+=shift/2;
        RIGHTSPEED+=shift/2;
        RIGHTZOOMM+=shift/2;
        RIGHTZOOMP+=shift/2;
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
        return  HEIGHT - HEIGHT/1.08;
    }
    public static  double TOPSELECT(){
        return  HEIGHT - HEIGHT/1.8;
    }
    public static  double TOPCOLOR(){
        return  HEIGHT - HEIGHT/2.3;
    }
    public static  double TOPCHECK(){
        return  HEIGHT - HEIGHT/7.8;
    }
    public static  double TOPZOOMP(){
        return  HEIGHT - HEIGHT/2.3;
    }
    public static  double TOPZOOMM(){
        return  HEIGHT - HEIGHT/2.3;
    }
    public static double TOPSLIDER(){return HEIGHT - HEIGHT/1.25;}
    public static  double TOPRUN(){return  HEIGHT - HEIGHT/1.8;}
    public static double TOPRESET(){return HEIGHT - HEIGHT/4.6;}
    public static double TOPLABEL3(){return HEIGHT - HEIGHT/1.4;}
    public static double TOPSPEED(){return HEIGHT - HEIGHT/1.5;}
    public static double TOPPAUSE(){return HEIGHT - HEIGHT/4.6;}
    public static double TOPHIDE(){return HEIGHT - HEIGHT/1;}
    public static double TOPLABEL1(){
        return HEIGHT - HEIGHT/1.02;
    }
    public static double TOPLABEL2(){
        return HEIGHT - HEIGHT/1.18;
    }
    public static double TOPCREATEMIN(){return  HEIGHT - HEIGHT/2.8;}
    public static double TOPCREATEMAX(){return  HEIGHT - HEIGHT/2.8;}
    public static double TOPALIVEMIN(){return  HEIGHT - HEIGHT/2.8;}
    public static double TOPALIVEMAX(){return  HEIGHT - HEIGHT/2.8;}
    public static double TOPLABEL4(){return  HEIGHT-HEIGHT/3.2;}
    public static double TOPLABEL5(){return  HEIGHT-HEIGHT/3.2;}
    public static double TOPLABEL6(){return  HEIGHT-HEIGHT/3.2;}
    public static double TOPLABEL7(){return  HEIGHT-HEIGHT/3.2;}
    public static double TOPRULES(){return  HEIGHT-HEIGHT/12;}



    public static void setStep(int a){
        STEP = a;
    }
    public static int getSTEP(){
        return STEP;
    }
    public static double getKEVEN(){
        return getSTEP()/1.9;
    }

    public  static double RIGHTTEXT = WIDTH/16.5;
    public  static double RIGHTSELECT =  WIDTH/5.4;
    public  static double RIGHTCOLOR = WIDTH/5.4;
    public  static double RIGHTCHECK = WIDTH/7.9;
    public  static double RIGHTZOOMP =  WIDTH/9;
    public  static double RIGHTZOOMM = WIDTH/15;
    public  static double RIGHTSLIDER = WIDTH/21;
    public  static double RIGHTRUN = WIDTH/13;
    public  static double RIGHTRESET = WIDTH/5.4;
    public  static double RIGHTLABEL1 = WIDTH/10;
    public  static double RIGHTLABEL2 = WIDTH/10;
    public  static double RIGHTLABEL3 = WIDTH/10;
    public  static double RIGHTSPEED = WIDTH/21;
    public  static double RIGHTPAUSE = WIDTH/15;
    public static double RIGHTCREATMAX = WIDTH/4;
    public static double RIGHTCREATEMIN = WIDTH/5.74;
    public static double RIGHTALIVEMAX = WIDTH/12;
    public static double RIGHTALIVEMIN = WIDTH/95;
    public  static double RIGHTLABEL4 = WIDTH/3.4;
    public  static double RIGHTLABEL5 = WIDTH/4.7;
    public  static double RIGHTLABEL6 = WIDTH/8;
    public  static double RIGHTLABEL7 = WIDTH/20;
    public static double RIGHTRULES = WIDTH/7.9;




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

    public static void SETCOUNT(int a){
        COUNT = a;
    }
    public static int getCOUNT(){
        return COUNT;
    }

    public static void setPhongMaterial(Color q){
        phongMaterial.setDiffuseColor(q);
    }
    public static PhongMaterial getPhongMaterial(){
        return phongMaterial;
    }
    public static void setPhongMaterialMinor(Color q){
        phongMaterialMinor.setDiffuseColor(q);
    }
    public static PhongMaterial getPhongMaterialMinor(){
        return phongMaterialMinor;
    }
    public static void setPhongMaterialMain(Color q){
        phongMaterialMain.setDiffuseColor(q);
    }
    public static PhongMaterial getPhongMaterialMain(){
        return phongMaterialMain;
    }


}
