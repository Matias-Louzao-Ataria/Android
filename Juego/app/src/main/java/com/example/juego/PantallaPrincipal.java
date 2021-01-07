package com.example.juego;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class PantallaPrincipal extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap mapa;
    private MiHilo hilo;
    private SurfaceHolder holder;
    private Context contexto;
    private int altoPantalla,anchoPantalla;

    public PantallaPrincipal(Context context) {
        super(context);
        this.holder = this.getHolder();
        this.holder.addCallback(this);
        this.contexto = this.getContext();
        this.hilo = new MiHilo(this.holder,this);
        this.setFocusable(true);
    }

    public void fisicas(Bala[] balas){
        for(int i = 0;i < balas.length;i++){
            Bala balaActual = balas[i];
            balaActual.mover(altoPantalla,anchoPantalla);
            for(int j = 0;j < balas.length;j++){
                if(i != j){
                    if(balaActual.getHitbox().intersect(balas[j].getHitbox())){
                        balaActual.setVelocidad(new PointF(balaActual.getVelocidad().x * -1,balaActual.getVelocidad().y * -1));
                        balas[j].setVelocidad(new PointF(balas[j].getVelocidad().x * -1,balas[j].getVelocidad().y * -1));
                    }
                }
            }
        }
    }

    public void dibujar(Canvas canvas,Bala[] balas,Personaje personaje){
        try{
            canvas.drawColor(Color.CYAN);
            for(Bala balaActual : balas){
                canvas.drawBitmap(balaActual.getImg(),balaActual.getPosicion().x-balaActual.getImg().getWidth(),balaActual.getPosicion().y-balaActual.getImg().getHeight(),null);
                Paint p = new Paint();
                p.setColor(Color.RED);
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(20);
                canvas.drawRect(balaActual.getHitbox(),p);
            }
            canvas.drawBitmap(personaje.getImg(),personaje.getPosicion().x,personaje.getPosicion().y,null);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
