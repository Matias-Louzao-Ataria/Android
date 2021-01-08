package com.example.juego;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class GestureDetectorListener extends GestureDetector.SimpleOnGestureListener {

    private PantallaPrincipal parent = null;

    public GestureDetectorListener(PantallaPrincipal pantallaPrincipal) {
        this.parent = pantallaPrincipal;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Toast.makeText(this.parent.getContext(), velocityX+" , "+velocityY, Toast.LENGTH_SHORT).show();
        Personaje personaje = this.parent.getHilo().getPersonaje();
        if(velocityX > 500){
            personaje.setPosicion(personaje.getPosicion().x+personaje.getVelocidad().x,personaje.getPosicion().y);
        }else if(velocityX < -500){
            personaje.setPosicion(personaje.getPosicion().x-personaje.getVelocidad().x,personaje.getPosicion().y);
        }
        if(velocityY > 500){
            personaje.setPosicion(personaje.getPosicion().x,personaje.getPosicion().y+personaje.getVelocidad().y);
        }else if(velocityY < -500){
            personaje.setPosicion(personaje.getPosicion().x,personaje.getPosicion().y-personaje.getVelocidad().y);
        }
        personaje.setHitbox();
        return true;
    }
}
