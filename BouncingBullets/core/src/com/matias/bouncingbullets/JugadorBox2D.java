package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class JugadorBox2D extends Actor {
    public static final float JUGADOR_WIDTH = 0.5f;
    public static final float JUGADOR_HEIGHT = 0.5f;
    private World world;
    private Body body;
    private Fixture fixture;
    private Texture texture;

    public JugadorBox2D(World world, Texture texture, Vector2 posicion) {
        this.world = world;
        this.texture = texture;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        this.body = this.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(JUGADOR_WIDTH, JUGADOR_HEIGHT);
        this.fixture = this.body.createFixture(shape,1);
        this.fixture.setUserData("jugador");
        shape.dispose();
        this.setSize(JUGADOR_WIDTH*2,JUGADOR_HEIGHT*2);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setPosition(this.body.getPosition().x-JUGADOR_WIDTH,this.body.getPosition().y-JUGADOR_HEIGHT);
        batch.draw(this.texture,getX(),getY(),getWidth(),getHeight());
    }

    public Body getBody() {
        return body;
    }

    public void dispose(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.texture.dispose();
    }
}