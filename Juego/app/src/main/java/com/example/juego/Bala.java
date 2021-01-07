package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

public class Bala {
    private PointF velocidad;
    private Point posicion = new Point(0,0);
    private Bitmap img;
    private Point direccion;
    private Rect hitbox;
    private int alto,ancho;


    public Point getPosicion() {
        return posicion;
    }

    public void setVelocidad(PointF velocidad) {
        this.velocidad = velocidad;
    }

    public PointF getVelocidad() {
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

    /**
     * Constructor del objeto bala que inicializa sus componentes según los parametros dados.
     * @param velocidad Velocidad de la bala en sus ejes.
     * @param img Imagen que define el aspecto de la bala.
     * @param direccion Dirección en la que se mueve la bala.
     * @param alto Alto del area por el que se mueve la bala.
     * @param ancho Ancho del area por el que se mueve la bala.
     */
    public Bala(PointF velocidad, Bitmap img, Point direccion,int alto,int ancho) {
        this.img = img;
        this.velocidad = new PointF(0,0);
        this.direccion = direccion;
        this.cambiarVelocidad((int)velocidad.x,(int)velocidad.y,direccion);
        this.mover(alto,ancho);
    }

    public Bala(PointF velocidad, Bitmap img, Point direccion,int alto,int ancho,Point posicion) {
        this(velocidad, img, direccion, alto, ancho);
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

        mover(alto,ancho);
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    /**
     * Mueve el personaje.
     * @param alto Alto de la zona en la que se mueve el personaje.
     * @param ancho ancho de la zona en la que se mueve el personaje.
     */
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
        setHitbox();
    }

    //TODO: Hacer la lógica detras del movimiento de la hitbox de las balas.
    private void setHitbox(){
        this.hitbox = new Rect(this.posicion.x-this.img.getWidth(),this.posicion.y-this.img.getHeight(),this.posicion.x+this.img.getWidth()-(int)(this.img.getWidth()*0.98),this.posicion.y+this.img.getHeight()-(int)(this.img.getHeight()*0.98));
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
