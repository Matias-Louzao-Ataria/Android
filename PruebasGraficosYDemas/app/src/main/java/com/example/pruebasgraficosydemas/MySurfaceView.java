package com.example.pruebasgraficosydemas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int MINMOVE = 2;
    private SurfaceHolder holder;
    private Context contexto;
    private Bitmap mapa;
    private Bitmap circulo;
    private int anchoPantalla = 1;
    private int altoPantalla = 1;
    private boolean running = false;
    private int posCirculoX,posCirculoY;
    private HiloA hilo;
    private static HashMap<Integer, PointF>posiciones = new HashMap<Integer, PointF>();

    public MySurfaceView(Context context) {
        super(context);
        this.holder = this.getHolder();
        this.holder.addCallback(this);
        this.contexto = this.getContext();
        this.hilo = new HiloA();
        this.setFocusable(true);
        this.mapa = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.fondo);
        this.circulo = BitmapFactory.decodeResource(contexto.getResources(),R.drawable.circulo);
    }

    public void dibujar(Canvas c) {// Rutina de dibujo en el lienzo. Se le llamará desde el hilo
        try{
            c.drawBitmap(mapa,0,0,null);
            /*this.circulo = Bitmap.createScaledBitmap(circulo,100,100,true);
            c.drawBitmap(this.circulo,this.posCirculoX,this.posCirculoY,null);*/
            for(PointF p:posiciones.values()){
                this.circulo = Bitmap.createScaledBitmap(this.circulo,anchoPantalla/8,altoPantalla/12,true);
                c.drawBitmap(this.circulo,p.x-this.circulo.getWidth()/2,p.y-this.circulo.getHeight()/2,null);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void changeCirculoPos(int x,int y){
        this.posCirculoX = x-this.circulo.getWidth();
        this.posCirculoY = y -this.circulo.getHeight();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        //Poner a true para que el hilo empiece
        this.hilo.setFuncionando(true);
        if(this.hilo.getState() == Thread.State.NEW){
            this.hilo.start();
        }else if(this.hilo.getState() == Thread.State.TERMINATED){
            this.hilo = new HiloA();
            this.hilo.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        this.hilo.setSurfaceSize(width,height);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        this.hilo.setFuncionando(false);
        try{
            this.hilo.join();
        }catch (InterruptedException e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (holder){
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);
            int action = event.getActionMasked();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    /*changeCirculoPos((this.posCirculoX+this.circulo.getWidth())+10,this.posCirculoY+this.circulo.getHeight());
                    break;*/
                case MotionEvent.ACTION_POINTER_DOWN:
                    PointF posicion = new PointF(event.getX(pointerIndex),event.getY(pointerIndex));
                    this.posiciones.put(pointerId,posicion);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    this.posiciones.remove(pointerId);
                case MotionEvent.ACTION_MOVE:
                    for(int i = 0;i < event.getPointerCount();i++){
                        PointF posActual = posiciones.get(event.getPointerId(i));
                        if( posActual != null){
                            if(Math.abs(posActual.x - event.getX(i)) > MINMOVE || Math.abs(posActual.y - event.getY(i)) > MINMOVE){
                                posiciones.get(event.getPointerId(i)).set(new PointF(event.getX(i),event.getY(i)));
                            }
                        }
                    }
            }
        }
        return true;
    }

    class HiloA extends Thread{

        public HiloA() {

        }

        public void run() {
            while(running){
                Canvas canvas = null;
                try{
                    if(!holder.getSurface().isValid()){

                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        canvas = holder.lockHardwareCanvas();
                    }else{
                        canvas = holder.lockCanvas();
                    }
                    synchronized (holder){
                        //Codigo del hilo
                        dibujar(canvas);
                    }
                }catch(Exception e){

                }finally {
                    if(canvas != null){
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }

        public void setFuncionando(boolean funcionando){
            running = funcionando;
        }

        public void setSurfaceSize(int width,int height){
            synchronized (holder){
               if(mapa != null){
                   mapa = Bitmap.createScaledBitmap(mapa,width,height,true);
               }
            }
        }
    }
}
