package com.matias.bouncingbullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import static com.matias.bouncingbullets.MainGameScreen.VELBALAX;
import static com.matias.bouncingbullets.MainGameScreen.VELBALAY;

public class MainGameContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        String objA,objB;
        objA = (String) contact.getFixtureA().getUserData();
        objB = (String) contact.getFixtureB().getUserData();

        if(objA.equals("paredDcha") && objB.equals("bala")){
            contact.getFixtureB().getBody().setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureB().getBody().setLinearVelocity(new Vector2(contact.getFixtureB().getBody().getLinearVelocity().x*1/3,contact.getFixtureB().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }else if(objB.equals("paredDcha") && objA.equals("bala")){
            contact.getFixtureA().getBody().setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureA().getBody().setLinearVelocity(new Vector2(contact.getFixtureA().getBody().getLinearVelocity().x*1/3,contact.getFixtureA().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
            //contact.getFixtureA().getBody().applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }

        if(objA.equals("paredIzq") && objB.equals("bala")){
            contact.getFixtureB().getBody().setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }else if(objB.equals("paredIzq") && objA.equals("bala")){
            contact.getFixtureA().getBody().setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }

        if(objA.equals("paredArr") && objB.equals("bala")){
            contact.getFixtureB().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredArr") && objA.equals("bala")){
            contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }

        if(objA.equals("paredAbj") && objB.equals("bala")){
            contact.getFixtureB().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredAbj") && objA.equals("bala")){
            contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }
        if(objB.equals("bala") && objA.equals("jugador")){

        }else if(objB.equals("jugador") && objA.equals("bala")){

        }
        if(objB.contains("pared") && objA.equals("jugador")){
            System.out.println("A");
        }else if(objB.equals("jugador") && objA.contains("pared")){
            System.out.println("A");
        }
                /*if(objA.equals("bala") && objB.equals("bala")){
                    contact.getFixtureB().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x*-1,contact.getFixtureB().getBody().getLinearVelocity().y*-1);
                    contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x*-1,contact.getFixtureA().getBody().getLinearVelocity().y*-1);
                    /*contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
                    contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);*/
        //}
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
