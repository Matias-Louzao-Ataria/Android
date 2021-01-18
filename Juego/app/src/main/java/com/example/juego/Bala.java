package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

public class Bala {
    private Point velocidad = null;
    private Point posicion = new Point(0,0);
    private Bitmap img = null;
    private Point direccion = null;
    private Rect hitbox = null;
    private int alto,ancho;
    private boolean chocado;
    private int id;
    private int idChocado;

    /**
     * Constructor del objeto bala que inicializa sus componentes según los parametros dados.
     * @param velocidad Velocidad de la bala en sus ejes.
     * @param img Imagen que define el aspecto de la bala.
     * @param direccion Dirección en la que se mueve la bala.
     * @param alto Alto del area por el que se mueve la bala.
     * @param ancho Ancho del area por el que se mueve la bala.
     */
    public Bala(int id,Point velocidad, Bitmap img, Point direccion,int alto,int ancho) {
        this.img = img;
        this.velocidad = velocidad;
        this.direccion = direccion;
        this.id = id;
        this.mover(alto,ancho);
    }

    public Bala(int id,Point velocidad, Bitmap img, Point direccion,int alto,int ancho,Point posicion) {
        this(id,velocidad, img, direccion, alto, ancho);
        this.posicion = posicion;
    }

    /**
     * Cambia la velocidad y dirección de el personajeen los ejes.
     * @param x Cantidad que la bala se mueve en X.
     * @param y Cantidad que la bala se mueve en Y.
     * @param dirreccion Dirección la que se mueve el personaje.
     */
    public void cambiarVelocidad(int x, int y,Point dirreccion){
        if(x > 0){
            this.velocidad.x = x;
        }
        if(y > 0){
            this.velocidad.y = y;
        }
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    /**
     * Mueve el personaje.
     * @param alto Alto de la zona en la que se mueve el personaje.
     * @param ancho ancho de la zona en la que se mueve el personaje.
     */
    public void mover(int alto, int ancho){//TODO comprobar si hay otra bala al moverse¿?.
        if((this.posicion.x+(this.velocidad.x*this.direccion.x))+this.img.getWidth() < ancho){
            this.posicion.x += this.velocidad.x*this.direccion.x;
        }else{
            this.direccion.x *= -1;
        }

        if((this.posicion.x+(this.velocidad.x*this.direccion.x)) < 0){
            this.direccion.x *= -1;
        }

        if(this.posicion.y+(this.velocidad.y*this.direccion.y)+this.img.getHeight() < alto){
            this.posicion.y += (this.velocidad.y*this.direccion.y);
        }else{
            this.direccion.y *= -1;
        }

        if((this.posicion.y+(this.velocidad.y*this.direccion.y)) < 0){
            this.direccion.y *= -1;
        }
        setHitbox();
    }

    private void setHitbox(){
        this.hitbox = new Rect(this.posicion.x,this.posicion.y,this.posicion.x+this.img.getWidth(),this.posicion.y+this.img.getHeight());//-(int)(this.img.getHeight()*0.98));
    }

    public Rect getHitbox() {
        return hitbox;
    }

    public boolean hasChocado() {
        return chocado;
    }

    public void setChocado(boolean chocado) {
        this.chocado = chocado;
    }

    public Point getPosicion() {
        return posicion;
    }

    public void setVelocidad(Point velocidad) {
        this.velocidad = velocidad;
    }

    public Point getVelocidad() {
        return velocidad;
    }

    public Bitmap getImg() {
        return img;
    }

    /**
     * Devuelve el valor de la propiedad direccion.
     * @return Valor de la propiedad direccion.
     */
    public Point getDireccion() {
        return direccion;
    }

    /**
     * Cambia la dirección en la que se mueve la bala. Si la dirección de la bala es null significa que ya no se mueve y por lo tanto que ha sido eliminada del mapa.
     * @param direccion Dirección en la que se mueve la bala.
     */
    public void setDireccion(Point direccion) {
        this.direccion = direccion;
    }

    public int getIdChocado() {
        return idChocado;
    }

    public void setIdChocado(int idChocado) {
        this.idChocado = idChocado;
    }

    public int getId() {
        return id;
    }
}
