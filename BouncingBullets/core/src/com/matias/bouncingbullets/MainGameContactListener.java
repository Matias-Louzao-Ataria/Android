package com.matias.bouncingbullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.matias.bouncingbullets.MainGameScreen.VELBALAX;
import static com.matias.bouncingbullets.MainGameScreen.VELBALAY;

public class MainGameContactListener implements ContactListener {

    private JugadorBox2D jugador;
    private World parent;

    public MainGameContactListener(JugadorBox2D jugador,World world){
        this.jugador = jugador;
        this.parent = world;
    }

    @Override
    public void beginContact(Contact contact) {
        Object objA,objB;
        objA = contact.getFixtureA().getUserData();
        objB = contact.getFixtureB().getUserData();

        Body bodyA = contact.getFixtureA().getBody(),bodyB = contact.getFixtureB().getBody();

        if(objA.equals("paredDcha") && objB instanceof BalaBox2D){
            bodyB.setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            bodyB.applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureB().getBody().setLinearVelocity(new Vector2(contact.getFixtureB().getBody().getLinearVelocity().x*1/3,contact.getFixtureB().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }else if(objB.equals("paredDcha") && objA instanceof BalaBox2D){
            bodyA.setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            bodyA.applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureA().getBody().setLinearVelocity(new Vector2(contact.getFixtureA().getBody().getLinearVelocity().x*1/3,contact.getFixtureA().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
            //contact.getFixtureA().getBody().applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }

        if(objA.equals("paredIzq") && objB instanceof BalaBox2D){
            bodyB.setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            bodyB.applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }else if(objB.equals("paredIzq") && objA instanceof BalaBox2D){
            bodyA.setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            bodyA.applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }

        if(objA.equals("paredArr") && objB instanceof BalaBox2D){
            bodyB.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            bodyB.applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredArr") && objA instanceof BalaBox2D){
            bodyA.setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            bodyA.applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }

        if(objA.equals("paredAbj") && objB instanceof BalaBox2D){
            bodyB.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            bodyB.applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredAbj") && objA instanceof BalaBox2D){
            bodyA.setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            bodyA.applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }
        if(objB instanceof BalaBox2D && objA instanceof JugadorBox2D){
            bodyA.setLinearVelocity(0,0);
            bodyB.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x*-1,contact.getFixtureB().getBody().getLinearVelocity().y*-1);
        }else if(objB instanceof JugadorBox2D && objA instanceof BalaBox2D){
            bodyB.setLinearVelocity(0,0);
            bodyA.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x*-1,contact.getFixtureB().getBody().getLinearVelocity().y*-1);
        }

        if(objA instanceof PowerUpObject.TipoObj && objB instanceof JugadorBox2D){
            aplicarPowerUp((PowerUpObject.TipoObj) objA);
        }else if(objB instanceof PowerUpObject.TipoObj && objA instanceof JugadorBox2D){
            aplicarPowerUp((PowerUpObject.TipoObj) objB);
        }

        if(objA instanceof PowerUpObject.TipoObj && objB instanceof BalaBox2D){
            bodyB.setLinearVelocity(bodyB.getLinearVelocity().x*-1,bodyB.getLinearVelocity().y*-1);
        }else if(objB instanceof PowerUpObject.TipoObj && objA instanceof BalaBox2D){
            bodyA.setLinearVelocity(bodyA.getLinearVelocity().x*-1,bodyA.getLinearVelocity().y*-1);
        }

        if(objB instanceof BalaBox2D && objA instanceof Bate){
            BalaBox2D bala = ((BalaBox2D) objB);
            ((Bate) objA).vecesGolpeado++;
            bala.getBody().setLinearVelocity(bala.getBody().getLinearVelocity().x*-1,bala.getBody().getLinearVelocity().y*-1);
        }else if(objA instanceof BalaBox2D && objB instanceof Bate){
            BalaBox2D bala = ((BalaBox2D) objA);
            ((Bate) objB).vecesGolpeado++;
            bala.getBody().setLinearVelocity(bala.getBody().getLinearVelocity().x*-1,bala.getBody().getLinearVelocity().y*-1);
        }

                /*if(objA.equals("bala") && objB.equals("bala")){
                    contact.getFixtureB().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x*-1,contact.getFixtureB().getBody().getLinearVelocity().y*-1);
                    contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x*-1,contact.getFixtureA().getBody().getLinearVelocity().y*-1);
                    /*contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
                    contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);*/
        //}
    }

    private void aplicarPowerUp(PowerUpObject.TipoObj powerUp) {
        this.jugador.setPowerUp(powerUp);
        MainGameScreen.deletePowerUp = true;
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
