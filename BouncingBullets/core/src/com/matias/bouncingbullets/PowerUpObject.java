package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Objects;

public class PowerUpObject extends BaseActor{

    public static enum TipoObj{
        Bate,
        BateDorado,
        Boton,
        Chaleco
    };

    private int timer = 10;
    private long segSpawn = 0;
    private boolean desaparecer = false;

    public PowerUpObject(World world, Texture texture, Vector2 posicion, TipoObj objType,long nanoseconds) {
        this.world = world;
        this.texture = texture;

        this.segSpawn = nanoseconds;

        WIDTH = 1f;
        HEIGHT = 1f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.bullet = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.body = this.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = CategoryBits.OBJETO;//Es
        fixtureDef.filter.maskBits = CategoryBits.JUGADOR;//Choca con...
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);
        fixtureDef.shape = shape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(objType);
        shape.dispose();
        this.setSize(WIDTH *2, HEIGHT *2);
    }

    @Override
    public void act(float delta) {
        if(TimeUtils.nanoTime() - segSpawn >= timer* 1000000000L){
            desaparecer = true;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(TimeUtils.nanoTime() - segSpawn > 4){
            dibujuar(batch);
        }else{
            if(TimeUtils.nanoTime()/1000000000 % 2 == 0){
                dibujuar(batch);
            }
        }
    }

    private void dibujuar(Batch batch) {
        this.setPosition(this.body.getPosition().x- WIDTH,this.body.getPosition().y- HEIGHT);
        batch.draw(this.texture,getX(),getY(),getWidth(),getHeight());
    }

    public int getTimer() {
        return timer;
    }

    public long getSegSpawn() {
        return segSpawn;
    }

    public boolean isDesaparecer() {
        return desaparecer;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        PowerUpObject powerUp = (PowerUpObject) o;
        return this.body == powerUp.body && this.fixture == powerUp.fixture;
    }
}
