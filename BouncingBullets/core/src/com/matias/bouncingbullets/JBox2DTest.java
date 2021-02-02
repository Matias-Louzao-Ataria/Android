package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class JBox2DTest extends BaseScreen{

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private World world;
    Body body,body2,body3,body4;
    int dir = 1;

    public JBox2DTest(Main parent) {
        super(parent);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                System.out.println(String.format("x: %f,y: %f,deltaX: %f,deltaY: %f",x,y,deltaX,deltaY));
                //body.setLinearVelocity(deltaX+20,-deltaY+20);
                body.applyLinearImpulse(new Vector2(deltaX*20,-deltaY*20),new Vector2(0,0),true);
                return false;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                return false;
            }

            @Override
            public void pinchStop() {

            }
        }));

        this.renderer = new Box2DDebugRenderer();
        this.world = new World(new Vector2(0,0),false);
        this.camera = new OrthographicCamera(30*16/9,30);
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(3, 3);
        Fixture fixture = body.createFixture(shape,1);
        fixture.setUserData("jugador");

//        BodyDef bodyDef2 = new BodyDef();
//        bodyDef2.position.set(15,0);
//        bodyDef2.type = BodyDef.BodyType.DynamicBody;
//        this.body2 = this.world.createBody(bodyDef2);
//        shape.setAsBox(5, 1);
//        Fixture fixture2 = body2.createFixture(shape,1);
//        fixture2.setUserData("bate");
//
//        BodyDef bodyDef3 = new BodyDef();
//        bodyDef3.position.set(5,5);
//        bodyDef3.type = BodyDef.BodyType.StaticBody;
//        this.body3 = this.world.createBody(bodyDef3);
//        shape.setAsBox(5, 1);
//        Fixture fixture3 = body3.createFixture(shape,1);
//        fixture3.setUserData("l1");
//
//        BodyDef bodyDef4 = new BodyDef();
//        bodyDef4.position.set(5,-5);
//        bodyDef4.type = BodyDef.BodyType.StaticBody;
//        this.body4 = this.world.createBody(bodyDef4);
//        shape.setAsBox(5, 1);
//        Fixture fixture4 = body4.createFixture(shape,1);
//        fixture4.setUserData("l2");

        shape.dispose();

//        DistanceJointDef jointDef = new DistanceJointDef();
//        jointDef.bodyA = body;
//        jointDef.bodyB = body2;
//        jointDef.localAnchorB.set(-8,0);
//        jointDef.collideConnected = true;
//        //jointDef.collideConnected = false;
//
//        Joint joint = world.createJoint(jointDef);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.world.step(delta,6,2);
        this.renderer.render(this.world,this.camera.combined);
//        if(Gdx.input.justTouched()){
//            if(dir == 1){
//                dir = -1;
//            }else{
//                dir = 1;
//            }
//            //body.setAngularVelocity(5f);
//            body.applyLinearImpulse(new Vector2(dir*1000,dir*1000),new Vector2(10f,0),true);
//            //body.applyLinearImpulse(new Vector2(0,-10),new Vector2(0f,0),true);
//            //body2.applyForce(new Vector2(100,10),new Vector2(7f,0),true);
//            //body.applyForce(new Vector2(-10000,-1000),new Vector2(0f,0),true);
//        }
    }

    @Override
    public void resize(int width, int height) {
        this.camera.update();
    }
}
