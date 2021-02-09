package com.matias.bouncingbullets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.matias.bouncingbullets.oldActors.Bate;

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

        if(objA.equals("paredDcha") && objB.getClass() == BalaBox2D.class){
            bodyB.setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            bodyB.applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureB().getBody().setLinearVelocity(new Vector2(contact.getFixtureB().getBody().getLinearVelocity().x*1/3,contact.getFixtureB().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }else if(objB.equals("paredDcha") && objA.getClass() == BalaBox2D.class){
            bodyA.setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            bodyA.applyLinearImpulse(new Vector2(-VELBALAX,0),new Vector2(0,0),true);
            //contact.getFixtureA().getBody().setLinearVelocity(new Vector2(contact.getFixtureA().getBody().getLinearVelocity().x*1/3,contact.getFixtureA().getBody().getLinearVelocity().y));//.applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
            //contact.getFixtureA().getBody().applyForceToCenter(new Vector2(-VELBALAX,VELBALAY),true);
        }

        if(objA.equals("paredIzq") && objB.getClass() == BalaBox2D.class){
            bodyB.setLinearVelocity(0,contact.getFixtureB().getBody().getLinearVelocity().y);
            bodyB.applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }else if(objB.equals("paredIzq") && objA.getClass() == BalaBox2D.class){
            bodyA.setLinearVelocity(0,contact.getFixtureA().getBody().getLinearVelocity().y);
            bodyA.applyLinearImpulse(new Vector2(VELBALAX,0),new Vector2(0,0),true);
        }

        if(objA.equals("paredArr") && objB.getClass() == BalaBox2D.class){
            bodyB.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            bodyB.applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredArr") && objA.getClass() == BalaBox2D.class){
            bodyA.setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            bodyA.applyLinearImpulse(new Vector2(0,-VELBALAY),new Vector2(0,0),true);
        }

        if(objA.equals("paredAbj") && objB.getClass() == BalaBox2D.class){
            bodyB.setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x,0);
            bodyB.applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }else if(objB.equals("paredAbj") && objA.getClass() == BalaBox2D.class){
            bodyA.setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x,0);
            bodyA.applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
        }
        if(objB.getClass() == BalaBox2D.class && objA.getClass() == JugadorBox2D.class){
            if(!this.jugador.isInvencible()){
                ((JugadorBox2D) objA).addHp(-1);
                MainGameScreen.borrarBalas.add((BalaBox2D) objB);
            }
            bodyA.setLinearVelocity(0,0);
            bodyB.setLinearVelocity(bodyB.getLinearVelocity().x*-1,bodyB.getLinearVelocity().y*-1);
        }else if(objB.getClass() == JugadorBox2D.class && objA.getClass() == BalaBox2D.class){
            if(!this.jugador.isInvencible()){
                ((JugadorBox2D) objB).addHp(-1);
                MainGameScreen.borrarBalas.add((BalaBox2D) objA);
            }
            bodyB.setLinearVelocity(0,0);
            bodyA.setLinearVelocity(bodyA.getLinearVelocity().x*-1,bodyA.getLinearVelocity().y*-1);
        }

        if(objA.getClass() == PowerUpObject.class && objB.getClass() == JugadorBox2D.class){
            aplicarPowerUp((PowerUpObject) objA);
        }else if(objB.getClass() == PowerUpObject.class && objA.getClass() == JugadorBox2D.class){
            aplicarPowerUp((PowerUpObject) objB);
        }

//        if(objB.getClass() == BalaBox2D.class && objA.getClass() == Bate.class && ((Bate) objA).vecesGolpeado < 3){
//            BalaBox2D bala = ((BalaBox2D) objB);
//            bala.getBody().setLinearVelocity(bala.getBody().getLinearVelocity().x*-1,bala.getBody().getLinearVelocity().y*-1);
//            ((Bate) objA).vecesGolpeado++;
//        }else if(objA.getClass() == BalaBox2D.class && objB.getClass() == Bate.class && ((Bate) objB).vecesGolpeado < 3){
//            BalaBox2D bala = ((BalaBox2D) objA);
//            bala.getBody().setLinearVelocity(bala.getBody().getLinearVelocity().x*-1,bala.getBody().getLinearVelocity().y*-1);
//            ((Bate) objB).vecesGolpeado++;
//        }

                /*if(objA.equals("bala") && objB.equals("bala")){
                    contact.getFixtureB().getBody().setLinearVelocity(contact.getFixtureB().getBody().getLinearVelocity().x*-1,contact.getFixtureB().getBody().getLinearVelocity().y*-1);
                    contact.getFixtureA().getBody().setLinearVelocity(contact.getFixtureA().getBody().getLinearVelocity().x*-1,contact.getFixtureA().getBody().getLinearVelocity().y*-1);
                    /*contact.getFixtureB().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);
                    contact.getFixtureA().getBody().applyLinearImpulse(new Vector2(0,VELBALAY),new Vector2(0,0),true);*/
        //}
    }

    private void aplicarPowerUp(PowerUpObject powerUp) {
        powerUp.setDesaparecer(true);
        this.jugador.setPowerUp(powerUp.getTipo());
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
