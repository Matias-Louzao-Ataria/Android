package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class UIBaseActor extends Actor {
    protected  Texture texture;
    public static float WIDTH = 2f;
    public static float HEIGHT = 1.8f;

    public UIBaseActor(Texture texture,float x,float y){
        this.texture = texture;
        this.setPosition(x,y);
        this.setSize(WIDTH,HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(this.texture,this.getX(),this.getY(),getWidth(),getHeight());
    }

    public void dispose(){
        this.remove();
        this.texture.dispose();
        this.dispose();
    }
}
