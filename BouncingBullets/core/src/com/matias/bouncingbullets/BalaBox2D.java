package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BalaBox2D extends BaseActor {
    public static final float RADIO_BALA = 1f;

    public BalaBox2D(World world, Texture texture, Vector2 posicion) {
        this.world = world;
        this.texture = texture;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.bullet = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = CategoryBits.BALA;//Es
        fixtureDef.filter.maskBits = CategoryBits.JUGADOR | CategoryBits.PARED | CategoryBits.ROCA | CategoryBits.BATE;//Choca con...
        CircleShape shape = new CircleShape();
        shape.setRadius(RADIO_BALA);
        fixtureDef.shape = shape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
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
