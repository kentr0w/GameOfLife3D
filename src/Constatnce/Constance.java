package Constatnce;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Screen;
import javafx.stage.Window;

public final class Constance {

    private   static  double WIDTH  = Screen.getPrimary().getBounds().getWidth()/2;
    private static   double HEIGHT = Screen.getPrimary().getBounds().getHeight()/2;
    public static int SIZE = 4;
    private static  int X = 70;
    private static  int Y = -40;
    private static  int Z = -100;
    private static  int STEP = 5;
    private static int COUNT;
    private static PhongMaterial phongMaterial = new PhongMaterial(Color.BLACK);
    private static PhongMaterial phongMaterialMain = new PhongMaterial(Color.GREEN);
    private static PhongMaterial phongMaterialMinor = new PhongMaterial(Color.PINK);
    private static int ALIVEMIN = 5;
    private static int ALIVEMAX = 8;
    private static int CREATEMIN = 6;
    private static int CREATEMAX = 9;




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
        return  HEIGHT - HEIGHT/1.08;
    }
    public static  double TOPSELECT(){
        return  HEIGHT - HEIGHT/1.7;
    }
    public static  double TOPCOLOR(){
        return  HEIGHT - HEIGHT/2.5;
    }
    public static  double TOPCHECK(){
        return  HEIGHT - HEIGHT/7.7;
    }
    public static  double TOPZOOMP(){
        return  HEIGHT - HEIGHT/2.5;
    }
    public static  double TOPZOOMM(){
        return  HEIGHT - HEIGHT/2.5;
    }
    public static double TOPSLIDER(){return HEIGHT - HEIGHT/1.35;}
    public static  double TOPRUN(){return  HEIGHT - HEIGHT/1.7;}
    public static double TOPRESET(){return HEIGHT - HEIGHT/4.5;}

    public static void setStep(int a){
        STEP = a;
    }
    public static int getSTEP(){
        return STEP;
    }
    public static double getKEVEN(){
        return getSTEP()/1.9;
    }

    public static double RIGHTTEXT = WIDTH/16.5;
    public static double RIGHTSELECT = WIDTH/5.4;
    public static double RIGHTCOLOR = WIDTH/5.4;
    public static double RIGHTCHECK = WIDTH/7.9;
    public static double RIGHTZOOMP = WIDTH/9;
    public static double RIGHTZOOMM = WIDTH/15;
    public static double RIGHTSLIDER = WIDTH/21;
    public static double RIGHTRUN = WIDTH/13;
    public static double RIGHTRESET = WIDTH/5.4;



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

    public static int getALIVEMIN() {
        return ALIVEMIN;
    }

    public static void setALIVEMIN(int ALIVEMIN) {
        Constance.ALIVEMIN = ALIVEMIN;
    }

    public static int getALIVEMAX() {
        return ALIVEMAX;
    }

    public static void setALIVEMAX(int ALIVEMAX) {
        Constance.ALIVEMAX = ALIVEMAX;
    }

    public static int getCREATEMIN() {
        return CREATEMIN;
    }

    public static void setCREATEMIN(int CREATEMIN) {
        Constance.CREATEMIN = CREATEMIN;
    }

    public static int getCREATEMAX() {
        return CREATEMAX;
    }

    public static void setCREATEMAX(int CREATEMAX) {
        Constance.CREATEMAX = CREATEMAX;
    }
}
