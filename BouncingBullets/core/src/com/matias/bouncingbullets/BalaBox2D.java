package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BalaBox2D extends Actor {
    public static final float RADIO_BALA = 1f;
    private World world;
    private Body body;
    private Fixture fixture;
    private Texture texture;

    public BalaBox2D(World world, Texture texture, Vector2 posicion) {
        this.world = world;
        this.texture = texture;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.bullet = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);
        Shape shape = new CircleShape();
        shape.setRadius(RADIO_BALA);
        this.fixture = this.body.createFixture(shape,1);
        this.fixture.setUserData("bala");
        shape.dispose();
        this.setSize(RADIO_BALA*2,RADIO_BALA*2);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setPosition(this.body.getPosition().x-RADIO_BALA,this.body.getPosition().y-RADIO_BALA);
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
