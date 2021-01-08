package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Personaje {
    private int hp;
    private int invencivilidad = 3;
    private Bitmap img;
    private Point posicion = new Point(0,0);
    private Point velocidad;
    private Point direccion = new Point();
    private int alto,ancho;

    public Personaje(int hp, Bitmap img, Point velocidad,int alto,int ancho) {
        this.hp = hp;
        this.img = img;
        this.velocidad = velocidad;
        //this.posicion.x = this.img.getWidth();
        //this.posicion.y = this.img.getHeight();
        this.alto = alto;
        this.ancho = ancho;
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

        mover(alto,ancho);
    }

    public void mover(int alto, int ancho){
        if(Math.abs((this.posicion.x+(this.velocidad.x*this.direccion.x))) < ancho){
            this.posicion.x += this.velocidad.x*this.direccion.x;
        }else{
            this.direccion.x *= -1;
        }

        if((this.posicion.x+(this.velocidad.x*this.direccion.x))-this.img.getWidth() < 0){
            this.direccion.x *= -1;
        }

        if(Math.abs((this.posicion.y+(this.velocidad.y*this.direccion.y))) < alto){
            this.posicion.y += (this.velocidad.y*this.direccion.y);
        }else{
            this.direccion.y *= -1;
        }

        if((this.posicion.y+(this.velocidad.y*this.direccion.y))-this.img.getHeight() < 0){
            this.direccion.y *= -1;
        }
    }

    public void setPosicion(int x, int y) {
        this.posicion.x = x;
        this.posicion.y = y;
    }
}
