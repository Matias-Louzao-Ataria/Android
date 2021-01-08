package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.view.SurfaceHolder;

import java.util.Random;

public class MiHilo extends Thread{

    private boolean running = false;
    private SurfaceHolder holder;
    private PantallaPrincipal parent;
    private Bala[] balas;
    private Personaje personaje;
    private static Random r = new Random();

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public MiHilo(SurfaceHolder holder,PantallaPrincipal parent){
        this.holder = holder;
        this.parent = parent;
    }

    public void initBalas(int num){
        this.balas = new Bala[num];
        Bitmap cir = BitmapFactory.decodeResource(this.parent.getResources(),R.drawable.circulo);
        cir = Bitmap.createScaledBitmap(cir,100,100,true);
        /*int prob = 0;
        Point dir;*/
        for(int i = 0;i < this.balas.length;i++){
            /*prob = r.nextInt(100-0);
            System.err.println(prob);
            if(prob <= 50){
                dir = new Point(-1,-1);
            }else{
                dir = new Point(1,1);
            }*/
            this.balas[i] = new Bala(new PointF(10,10),cir,new Point(1,1),this.parent.getAltoPantalla(),this.parent.getAnchoPantalla(),new Point(r.nextInt(this.parent.getAnchoPantalla()-cir.getWidth())+cir.getWidth(),r.nextInt(this.parent.getAltoPantalla()-cir.getHeight())+cir.getHeight()));
        }
    }

    @Override
    public void run() {
        while(running){
            Canvas canvas = null;
            try{
                if(!holder.getSurface().isValid()){

                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    canvas = this.holder.lockHardwareCanvas();
                }else{
                    canvas = this.holder.lockCanvas();
                }
                synchronized (this.holder){
                    if(this.personaje == null){
                        Bitmap cuadrado = BitmapFactory.decodeResource(this.parent.getResources(),R.drawable.cuadrado);
                        this.personaje = new Personaje(3,cuadrado,new Point(5,5),this.parent.getAltoPantalla(),this.parent.getAnchoPantalla());
                    }
                    if(this.balas == null){
                        initBalas(10);
                    }
                    this.parent.fisicas(balas);
                    this.parent.dibujar(canvas,balas,personaje);
                }
            }catch (IllegalStateException e){
                System.err.println(e.getLocalizedMessage());
            }finally {
                if(canvas != null){
                    this.holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void setSurfaceSize(Bitmap bitmap,int width,int height){
        synchronized (this.holder){
            if(bitmap != null){
                bitmap = Bitmap.createScaledBitmap(bitmap,width,height,true);
            }
        }
    }

    public Personaje getPersonaje() {
        return personaje;
    }
}
