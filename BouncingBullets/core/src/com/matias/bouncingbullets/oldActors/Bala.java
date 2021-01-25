package com.matias.bouncingbullets.oldActors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bala extends Actor {
    private Texture img = null;
    private Body body = null;
    private Fixture fixture = null;
    private World world = null;
    private float PXINM = 0;

    public Bala(Texture img,Body body, Fixture fixture,World world,float PXINM) {
        this.img = img;
        this.body = body;
        this.fixture = fixture;
        this.world = world;
        this.PXINM = PXINM;
        setSize(this.fixture.getShape().getRadius()*PXINM,this.fixture.getShape().getRadius()*PXINM);
        //setPosition(this.body.getPosition().x*PXINM,this.body.getPosition().y*PXINM);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((this.body.getPosition().x - this.fixture.getShape().getRadius())*PXINM,(this.body.getPosition().y - this.fixture.getShape().getRadius()) *PXINM);
        batch.draw(this.img,getX(),getY(),this.getWidth(),this.getHeight());
    }

    @Override
    public void act(float delta) {
        if(Gdx.input.isTouched()){
            this.body.applyLinearImpulse(new Vector2(50,0),new Vector2(),true);
        }
        //this.body.setLinearVelocity(5,5);
    }

    public void detach(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.img.dispose();
    }

    /*public void cambiarVelocidad(int x, int y,Vector2 dirreccion){
        if(x > 0){
            this.velocidad.x = x;
        }
        if(y > 0){
            this.velocidad.y = y;
        }
    }*/



    /*public void mover(int alto, int ancho){//TODO comprobar si hay otra bala al moverse¿?.
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
    }*/

    /*private void setHitbox(){
        this.hitbox = new Rectangle(this.posicion.x,this.posicion.y,this.posicion.x+this.img.getWidth(),this.posicion.y+this.img.getHeight());//-(int)(this.img.getHeight()*0.98));
    }*/

    public Texture getImg() {
        return img;
    }

    public void setImg(Texture img) {
        this.img = img;
    }

}
