package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Generators{

    public Fixture balaFixtureGenerator(CircleShape shape,Body balaBody) {
        Fixture balaFixture = balaBody.createFixture(shape,1);
        balaFixture.setUserData("bala");
        shape.dispose();
        return balaFixture;
    }

    public Body balaBodyGenerator(World world,Vector2 posicion) {
        BodyDef balaDef = balaBodyDefGenerator(posicion);
        return world.createBody(balaDef);
    }

    public CircleShape balaShapeGenerator(float radio) {
        CircleShape shape = new CircleShape();
        shape.setRadius(radio);
        return shape;
    }

    public BodyDef balaBodyDefGenerator(Vector2 posicion) {
        BodyDef balaDef = new BodyDef();
        balaDef.position.set(posicion);
        balaDef.type = BodyDef.BodyType.DynamicBody;
        balaDef.bullet = true;
        return balaDef;
    }
}
