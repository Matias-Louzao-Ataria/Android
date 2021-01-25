package com.matias.bouncingbullets.oldActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Personaje extends Actor {
    private int hp;
    private int invencivilidad = 3;
    private Texture img;
    private Vector2 posicion = null;
    private Vector2 direccion = new Vector2();
    Body body;
    Fixture fixture;

    public Personaje(int hp, Texture img,Body body,Fixture fixture) {
        this.hp = hp;
        this.img = img;
        this.body = body;
        this.fixture = fixture;
        //this.posicion = new Vector2(Gdx.graphics.getWidth()/2-this.img.getWidth(),Gdx.graphics.getHeight()/2-this.img.getHeight());
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    public Vector2 getDireccion() {
        return direccion;
    }

    public void setDireccion(Vector2 direccion) {
        this.direccion = direccion;
    }














































    /*public void cambiarVelocidad(int x, int y,Vector2 dirreccion){
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
    }*/

    /*public void setHitbox(){
        this.hitbox = new Rectangle(this.posicion.x,this.posicion.y,this.posicion.x+this.img.getWidth(),this.posicion.y+this.img.getHeight());
    }

    public Rectangle getHitbox() {
        return hitbox;
    }*/
}
