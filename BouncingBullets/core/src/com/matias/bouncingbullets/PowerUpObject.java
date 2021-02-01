package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PowerUpObject extends BaseActor{

    public static enum TipoObj{
        Bate,
        BateDorado,
        Boton
    };

    public PowerUpObject(World world, Texture texture, Vector2 posicion, TipoObj objType) {
        this.world = world;
        this.texture = texture;

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
        //TODO Eliminar la textura,(descomentar la linea de abajo).
        this.texture.dispose();
    }

}
