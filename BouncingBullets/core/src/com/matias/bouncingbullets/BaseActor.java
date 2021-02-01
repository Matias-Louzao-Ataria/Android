package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class BaseActor extends Actor {
    public static float WIDTH = 0.5f;
    public static float HEIGHT = 0.5f;

    protected World world;
    protected Body body;
    protected Fixture fixture;
    protected Texture texture;

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

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
