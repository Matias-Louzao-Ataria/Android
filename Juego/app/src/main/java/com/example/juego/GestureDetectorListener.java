package com.example.juego;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

    private PantallaPrincipal parent = null;
    private int limit = 900;

    public GestureDetectorListener(PantallaPrincipal pantallaPrincipal) {
        this.parent = pantallaPrincipal;
    }

    @Override//TODO: Mejor sistema de movimiento para el personaje.
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(this.parent.getContext(), velocityX+" , "+velocityY, Toast.LENGTH_SHORT).show();
        Personaje personaje = this.parent.getHilo().getPersonaje();
        if(velocityX > limit){
            if(personaje.getPosicion().x + personaje.getVelocidad().x < this.parent.getAnchoPantalla()-personaje.getImg().getWidth()){
                personaje.setPosicion(personaje.getPosicion().x+personaje.getVelocidad().x,personaje.getPosicion().y);
            }else{
                personaje.setPosicion(this.parent.getAnchoPantalla()-personaje.getImg().getWidth(),personaje.getPosicion().y);
            }
        }else if(velocityX < -limit){
            if(personaje.getPosicion().x - personaje.getVelocidad().x > personaje.getImg().getWidth()){
                personaje.setPosicion(personaje.getPosicion().x - personaje.getVelocidad().x,personaje.getPosicion().y);
            }else{
                personaje.setPosicion(0,personaje.getPosicion().y);
            }
        }
        if(velocityY > limit){
            if(personaje.getPosicion().y + personaje.getVelocidad().y < this.parent.getAltoPantalla()-personaje.getImg().getHeight()){
                personaje.setPosicion(personaje.getPosicion().x,personaje.getPosicion().y+personaje.getVelocidad().y);
            }else{
                personaje.setPosicion(personaje.getPosicion().x,this.parent.getAltoPantalla()-personaje.getImg().getHeight());
            }
        }else if(velocityY < -limit){
            if(personaje.getPosicion().y - personaje.getVelocidad().y > 0){
                personaje.setPosicion(personaje.getPosicion().x,personaje.getPosicion().y-personaje.getVelocidad().y);
            }else{
                personaje.setPosicion(personaje.getPosicion().x,0);
            }
        }
        personaje.setHitbox();
        return true;
    }
}
