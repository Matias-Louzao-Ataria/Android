package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class Personaje {
    private int hp;
    private int invencivilidad = 3;
    private Bitmap img;
    private Point posicion = new Point(0,0);
    private Point velocidad;
    private Point direccion = new Point();
    private int alto,ancho;
    private Rect hitbox = null;

    public Personaje(int hp, Bitmap img, Point velocidad,int alto,int ancho) {
        this.hp = hp;
        this.img = img;
        this.velocidad = velocidad;
        //this.posicion.x = this.img.getWidth();
        //this.posicion.y = this.img.getHeight();
        this.alto = alto;
        this.ancho = ancho;
        setHitbox();
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setPosicion(Point posicion) {
        this.posicion = posicion;
    }

    public Point getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Point velocidad) {
        this.velocidad = velocidad;
    }

    public Point getDireccion() {
        return direccion;
    }

    public void setDireccion(Point direccion) {
        this.direccion = direccion;
    }

    public void cambiarVelocidad(int x, int y,Point dirreccion){
        if(x > 0){
            this.velocidad.x = x;
        }
        if(y > 0){
            this.velocidad.y = y;
        }
    }

    public void setPosicion(int x, int y) {
        this.posicion.x = x;
        this.posicion.y = y;
    }

    public void setHitbox(){
        this.hitbox = new Rect(this.posicion.x,this.posicion.y,this.posicion.x+this.img.getWidth(),this.posicion.y+this.img.getHeight());
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
