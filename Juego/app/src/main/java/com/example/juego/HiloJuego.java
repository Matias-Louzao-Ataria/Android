
package com.example.juego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HiloJuego extends Thread{

    private boolean running = false;
    private SurfaceHolder holder = null;
    private PantallaPrincipal parent = null;
    private Bala[] balas = null;
    private Personaje personaje = null;
    private static Random r = new Random();
    private int started = 0;
    private int spawnposiciones[] = null;
    public static int min = 0,sec = 0;
    long timerstart = 0;
    long cronometrotime = 0;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public HiloJuego(SurfaceHolder holder, PantallaPrincipal parent){
        this.holder = holder;
        this.parent = parent;
        this.spawnposiciones = new int[]{MainActivity.anchopantalla, 0};
        this.cronometrotime = System.nanoTime();
    }

    public void initBalas(int num){//TODO comprobar si hay alguna bala ya en esa posición y buscar otra si la hay¿?.
        this.balas = new Bala[num];
        Bitmap cir = BitmapFactory.decodeResource(this.parent.getResources(),R.drawable.circulo);
        cir = Bitmap.createScaledBitmap(cir,50,50,true);
        int posY = 0,velX = 0,velY = 0;
        this.spawnposiciones[0] -= cir.getWidth();
        int posX = this.spawnposiciones[0];
        this.spawnposiciones[1] = cir.getWidth();
        Point dir;
        for(int i = 0;i < this.balas.length;i++){
            posY = r.nextInt(MainActivity.altopantalla-cir.getHeight());
            if(posX == this.spawnposiciones[0]){
                posX = this.spawnposiciones[1];
            }else{
                posX = this.spawnposiciones[0];
            }

            while(velX == 0 && velY == 0){
                if(i == 0){
                    velX = 1;//r.nextInt(1+1)-1;
                    velY = 1;//0r.nextInt(1+1)-1;
                }else{
                    velX = -1;//r.nextInt(1+1)-1;
                    velY = -1;//0r.nextInt(1+1)-1;
                }
            }
            Log.i("velTest",velX+" , "+velY);//TODO probar con menos velocidad.
            this.balas[i] = new Bala(i,new Point(10,10),cir,new Point(velX,velY),MainActivity.altopantalla,MainActivity.anchopantalla,new Point(200,200));
        }
    }

    @Override
    public void run() {
        while(running){
            this.timerstart = System.nanoTime();
            this.parent.now=System.currentTimeMillis();
            if (this.parent.now-this.parent.last>=this.parent.timeXFrame){ // si ya paso el tiempo necesario, dibujo, Control de framesxSegundo en funcion del tiempo
                this.parent.last=this.parent.now;
                Canvas canvas = null;
                try{
                    this.started = (int)(System.currentTimeMillis()/1000);
                    if(!holder.getSurface().isValid())continue;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        canvas = this.holder.lockHardwareCanvas();
                    }else{
                        canvas = this.holder.lockCanvas();
                    }
                    synchronized (this.holder){
                        if(this.personaje == null){
                            initPersonaje();
                        }
                        if(this.balas == null){
                            initBalas(2);
                        }
                        if(System.nanoTime() - this.cronometrotime >= 1000000000){
                            this.cronometrotime = System.nanoTime();
                            cronometro();
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
            else {
                try {
                    Thread.sleep((long)this.parent.timeXFrame - (this.parent.now - this.parent.last));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void initPersonaje() {
        Bitmap cuadrado = BitmapFactory.decodeResource(this.parent.getResources(),R.drawable.cuadrado);
        cuadrado = Bitmap.createScaledBitmap(cuadrado,100,100,true);
        this.personaje = new Personaje(3,cuadrado,new Point(102,102), MainActivity.altopantalla,MainActivity.anchopantalla);
    }

    private void cronometro() {
        sec++;
        if(sec >= 60){
            min++;
            sec = 0;
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
