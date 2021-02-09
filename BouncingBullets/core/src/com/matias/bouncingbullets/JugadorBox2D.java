package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class JugadorBox2D extends BaseActor {
    public static final int maxHP = 7;
    private PowerUpObject.TipoObj powerUp;
    private int hp = 7;
    private boolean invencible = false;

    public JugadorBox2D(World world, Texture texture, Vector2 posicion) {
        this.world = world;
        this.texture = texture;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(posicion);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.filter.categoryBits = CategoryBits.JUGADOR;//Es
        fixtureDef.filter.maskBits = CategoryBits.BALA | CategoryBits.PARED | CategoryBits.ROCA | CategoryBits.OBJETO;//Choca con...
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);
        fixtureDef.shape = shape;
        this.fixture = this.body.createFixture(fixtureDef);
        this.fixture.setUserData(this);
        shape.dispose();
        this.setSize(WIDTH*2,HEIGHT*2);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        this.setPosition(this.body.getPosition().x-WIDTH/2,this.body.getPosition().y-HEIGHT/2);
        batch.draw(this.texture,getX(),getY(),getWidth(),getHeight());
    }

    public Body getBody() {
        return body;
    }

    public PowerUpObject.TipoObj getPowerUp() {
        return powerUp;
    }

    public void setPowerUp(PowerUpObject.TipoObj powerUp) {
        this.powerUp = powerUp;
    }

    public int getHp(){
        return this.hp;
    }

    public void addHp(int hp){
        this.hp += hp;
    }

    public boolean isInvencible() {
        return invencible;
    }

    public void setInvencible(boolean invencible) {
        this.invencible = invencible;
    }

    public void dispose(){
        this.body.destroyFixture(this.fixture);
        this.world.destroyBody(this.body);
        //this.texture.dispose();
    }
}