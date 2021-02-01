package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Bate extends BaseActor{

    private JugadorBox2D jugador;
    public short vecesGolpeado = 0;
    public Joint joint;

    public Bate(World world, Texture texture, JugadorBox2D jugador, Vector2 posicion) {
        this.world = world;
        this.texture = texture;
        this.jugador = jugador;

        WIDTH = 0.9f;
        HEIGHT = 0.3f;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = CategoryBits.BATE;//Es
        fixtureDef.filter.maskBits = CategoryBits.BALA;//Choca con...
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);
        fixtureDef.shape = shape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        shape.dispose();
        this.setSize(WIDTH *2, HEIGHT *2);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setPosition(this.body.getPosition().x- WIDTH,this.body.getPosition().y- HEIGHT);
        float x = getX(),y = getY();

        if(x < this.jugador.getX()){

        }
        batch.draw(this.texture,x,y,getWidth(),getHeight());
    }

    public Body getBody() {
        return body;
    }

    public void dispose(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        this.world.destroyJoint(this.joint);
        this.texture.dispose();
    }
}
