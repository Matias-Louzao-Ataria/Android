package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RocaBox2D extends BaseActor {

    public RocaBox2D(World world, Texture texture, Vector2 posicion) {
        this.world = world;
        this.texture = texture;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        this.body = this.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = CategoryBits.ROCA;//Es
        fixtureDef.filter.maskBits = CategoryBits.BALA | CategoryBits.JUGADOR;//Choca con...
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);
        fixtureDef.shape = shape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData("roca");
        shape.dispose();
        this.setSize(WIDTH *2, HEIGHT *2);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setPosition(this.body.getPosition().x- WIDTH,this.body.getPosition().y- HEIGHT);
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