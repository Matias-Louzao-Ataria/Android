package com.example.juego;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

public class PantallaPrincipal extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap mapa;
    private MiHilo hilo;
    private SurfaceHolder holder;
    private Context contexto;
    private int altoPantalla,anchoPantalla;
    private GestureDetectorCompat detectorGestos = null;

    public PantallaPrincipal(Context context) {
        super(context);
        this.holder = this.getHolder();
        this.holder.addCallback(this);
        this.contexto = this.getContext();
        this.hilo = new MiHilo(this.holder,this);
        this.setFocusable(true);
        this.detectorGestos = new GestureDetectorCompat(this.getContext(),new GestureDetectorListener(this));
    }

    public void fisicas(Bala[] balas){
        Personaje personaje = this.hilo.getPersonaje();
        for(int i = 0;i < balas.length;i++){
            Bala balaActual = balas[i];
            if(balaActual != null){
                balaActual.mover(altoPantalla,anchoPantalla);
                if(balaActual.getHitbox().intersect(personaje.getHitbox())){
                    balaActual.setDireccion(new Point(balaActual.getDireccion().x * -1,balaActual.getDireccion().y * -1));
                }
                for(int j = 0;j < balas.length;j++){
                    if(i != j){
                        Bala balaChoque = balas[j];
                        if(balaChoque != null){
                            if(balaActual.getHitbox().intersect(balas[j].getHitbox())){
                                balaActual.setVelocidad(new PointF(balaActual.getVelocidad().x * -1,balaActual.getVelocidad().y * -1));
                                balaChoque.setVelocidad(new PointF(balaChoque.getVelocidad().x * -1,balaChoque.getVelocidad().y * -1));
                            }
                        }
                    }
                }
            }
        }
    }

    public void dibujar(Canvas canvas,Bala[] balas,Personaje personaje){
        try{
            Paint rectPaint = new Paint();
            rectPaint.setColor(Color.RED);
            rectPaint.setStyle(Paint.Style.STROKE);
            canvas.drawColor(Color.CYAN);
            for(Bala balaActual : balas){
                canvas.drawBitmap(balaActual.getImg(),balaActual.getPosicion().x-balaActual.getImg().getWidth(),balaActual.getPosicion().y-balaActual.getImg().getHeight(),null);
                rectPaint.setStrokeWidth(20);
                canvas.drawRect(balaActual.getHitbox(),rectPaint);
            }
            canvas.drawBitmap(personaje.getImg(),personaje.getPosicion().x,personaje.getPosicion().y,null);
            canvas.drawRect(personaje.getHitbox(),rectPaint);
        }catch(Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public MiHilo getHilo() {
        return hilo;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.hilo.getPersonaje().setPosicion(this.hilo.getPersonaje().getPosicion().x+5,this.hilo.getPersonaje().getPosicion().y+5);
        this.detectorGestos.onTouchEvent(event);
        return true;
    }

    //CALLBACKS
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.hilo.setRunning(true);
        if(this.hilo.getState() == Thread.State.NEW){
            this.hilo.start();
        }
        if(this.hilo.getState() == Thread.State.TERMINATED){
            this.hilo = new MiHilo(this.holder,this);
            this.hilo.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        this.altoPantalla = height;
        this.anchoPantalla = width;
        this.hilo.setSurfaceSize(mapa,this.altoPantalla,this.anchoPantalla);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        this.hilo.setRunning(false);
        try{
            this.hilo.join();
        }catch(InterruptedException e){
            System.err.println(e.getLocalizedMessage());
        }
    }
}
