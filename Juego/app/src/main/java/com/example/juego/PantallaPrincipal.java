package com.example.juego;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

public class PantallaPrincipal extends SurfaceView implements SurfaceHolder.Callback {

    long last;
    int maxFrames=60;                  // Número máximo de frames por segundo
    int timeXFrame=1000/maxFrames;    // Tasa de tiempo para dibujar un frame (trabajando con milisegundos)
    static int numFrames;                           // Usado unicamente para ver el nº de frames por pantalla
    static long nowVisFrames,framesTimer,visFrames;
    long now;
    private Bitmap mapa = null;
    private HiloJuego hilo = null;
    private SurfaceHolder holder = null;
    private Context contexto = null;
    private int altoPantalla,anchoPantalla;
    private GestureDetectorCompat detectorGestos = null;
    private Rect btnIzq = null,btnDch = null,btnArr = null,btnAb = null;
    DisplayMetrics metrics;


    public PantallaPrincipal(Context context) {
        super(context);
        this.holder = this.getHolder();
        this.holder.addCallback(this);
        this.contexto = this.getContext();
        this.hilo = new HiloJuego(this.holder,this);
        this.setFocusable(true);
        this.detectorGestos = new GestureDetectorCompat(this.getContext(),new GestureDetectorListener(this));
        now=System.currentTimeMillis();
        last=System.currentTimeMillis();
        metrics = new DisplayMetrics();
        this.btnIzq = new Rect(100,20,200,75);
        this.btnArr= new Rect(300,20,400,75);
        this.btnAb = new Rect(MainActivity.anchopantalla-400,20,MainActivity.anchopantalla-300,75);
        this.btnDch  = new Rect(MainActivity.anchopantalla-200,20,MainActivity.anchopantalla-100,75);
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
    }

    public void fisicas(Bala[] balas){//TODO Mirar que esto siga funcionando bien e intentar que las balas no puedan ir una dentro de otra.
        Personaje personaje = this.hilo.getPersonaje();
        for(int i = 0;i < balas.length;i++){
            Bala balaActual = balas[i];
            if(balaActual != null){
                balaActual.mover(altoPantalla,anchoPantalla);
                if(balaActual.getHitbox().intersect(personaje.getHitbox())){
                    if(!balaActual.hasChocado()){
                        balaActual.setDireccion(new Point(balaActual.getDireccion().x * -1,balaActual.getDireccion().y * -1));
                        //balaActual.setChocado(true);
                    }
                    //TODO Decrementar los puntos de vida del personaje.
                }
                if(!balaActual.getHitbox().intersect(personaje.getHitbox()) && balaActual.hasChocado()){
                    //balaActual.setChocado(false);
                }
                for(int j = 0;j < balas.length;j++){
                    if(i != j){
                        Bala balaChoque = balas[j];
                        if(balaChoque != null){
                            if((!balaChoque.hasChocado() && !balaActual.hasChocado()) && balaActual.getHitbox().intersect(balaChoque.getHitbox())){
                                //Choque diagonal:
                                int limit = 20;
                                if(balaActual.getHitbox().left >= balaActual.getHitbox().right){
                                    Log.d("logh","111");
                                    if(balaActual.getHitbox().width() == limit){
                                        Log.d("logh","100");
                                    }
                                    if(balaActual.getHitbox().width() < limit){
                                        Log.d("logh","222");
                                    }
                                }
                                if(balaActual.getHitbox().height() <= limit){
                                    Log.d("logh","333");
                                    if(balaActual.getHitbox().height() == limit){
                                        Log.d("logh","200");
                                    }
                                    if(balaActual.getHitbox().height() < limit){
                                        Log.d("logh","444");
                                    }
                                }
                                balaActual.setChocado(true);
                                balaChoque.setChocado(true);
                                balaActual.setIdChocado(balaChoque.getId());
                                balaChoque.setIdChocado(balaActual.getId());
                                balaActual.setVelocidad(new Point(balaActual.getVelocidad().x * -1,balaActual.getVelocidad().y * -1));
                                balaChoque.setVelocidad(new Point(balaChoque.getVelocidad().x * -1,balaChoque.getVelocidad().y * -1));
                                Log.d("logidchoca1",""+balaActual.getIdChocado());
                                Log.d("logidchocc1",""+balaChoque.getIdChocado());
                                Log.d("logchoca1",""+balaActual.hasChocado());
                                Log.d("logcchocc1",""+balaChoque.hasChocado());

                                /*if(balaActual.getDireccion().x != 0 && balaActual.getDireccion().y != 0){
                                    if(balaChoque.getDireccion().x != 0 && balaChoque.getDireccion().y != 0){

                                    }
                                }

                                //Choque frontal
                                if(balaActual.getDireccion().x != 0 && balaActual.getDireccion().y == 0){
                                    if(balaChoque.getDireccion().x != 0 && balaChoque.getDireccion().y == 0){
                                        balaActual.setVelocidad(new Point(balaActual.getVelocidad().x * -1,balaActual.getVelocidad().y * -1));
                                    }else if(balaChoque.getDireccion().x == 0 && balaChoque.getDireccion().y != 0){

                                    }
                                }else if(balaActual.getDireccion().x == 0 && balaActual.getDireccion().y != 0){

                                }*/
                            }else if(!balaActual.getHitbox().intersect(balaChoque.getHitbox()) && (balaChoque.hasChocado() || balaActual.hasChocado()) && balaActual.getId() == balaChoque.getIdChocado()){
                                if(balaActual.hasChocado()){
                                    balaActual.setChocado(false);
                                }
                                if(balaChoque.hasChocado()){
                                    balaChoque.setChocado(false);
                                }
                                Log.d("logidchoca2",""+balaActual.getIdChocado());
                                Log.d("logidchocc2",""+balaChoque.getIdChocado());
                                Log.d("logchoca2",""+balaActual.hasChocado());
                                Log.d("logcchocc2",""+balaChoque.hasChocado());
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
                canvas.drawBitmap(balaActual.getImg(),balaActual.getPosicion().x,balaActual.getPosicion().y,null);
                rectPaint.setStrokeWidth(5);
                canvas.drawRect(balaActual.getHitbox(),rectPaint);
            }
            canvas.drawBitmap(personaje.getImg(),personaje.getPosicion().x,personaje.getPosicion().y,null);
            canvas.drawRect(personaje.getHitbox(),rectPaint);
            canvas.drawRect(this.btnIzq,rectPaint);
            canvas.drawRect(this.btnArr,rectPaint);
            canvas.drawRect(this.btnDch,rectPaint);
            canvas.drawRect(this.btnAb,rectPaint);
            Paint t = new Paint();
            t.setTextSize(100);
            t.setColor(Color.BLACK);
            canvas.drawText(String.format("%d:%d",HiloJuego.min,HiloJuego.sec),anchoPantalla/2-t.getTextSize(),0+t.getTextSize(),t);
            frames(canvas);
        }catch(Exception e){
            System.err.println(e.getLocalizedMessage());
        }
    }

    public void frames(Canvas c){
        nowVisFrames=System.currentTimeMillis();
        if (nowVisFrames-framesTimer>1000){// un segundo
            framesTimer=nowVisFrames;
            visFrames=numFrames;
            numFrames=0;
        }
        Paint t = new Paint();
        t.setTextSize(100);
        t.setColor(Color.WHITE);
        c.drawText("" + visFrames, gp(10), t.getTextSize()+gp(10), t);
        numFrames++;
    }

    int gp(float dp) {
        return (int)(dp*metrics.density);
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public HiloJuego getHilo() {
        return hilo;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //this.hilo.getPersonaje().setPosicion(this.hilo.getPersonaje().getPosicion().x+5,this.hilo.getPersonaje().getPosicion().y+5);
        //this.detectorGestos.onTouchEvent(event);
        Personaje p = this.hilo.getPersonaje();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(this.btnIzq.contains((int)event.getX(),(int)event.getY())){
                //p.setPosicion(p.getPosicion().x+p.getVelocidad().x,p.getPosicion().y);
                if(p.getPosicion().x - p.getVelocidad().x > p.getImg().getWidth()){
                    p.setPosicion(p.getPosicion().x - p.getVelocidad().x,p.getPosicion().y);
                }else{
                    p.setPosicion(0,p.getPosicion().y);
                }

            }

            if(this.btnDch.contains((int)event.getX(),(int)event.getY())){
                //p.setPosicion(p.getPosicion().x-p.getVelocidad().x,p.getPosicion().y);
                if(p.getPosicion().x + p.getVelocidad().x < MainActivity.anchopantalla-p.getImg().getWidth()){
                    p.setPosicion(p.getPosicion().x+p.getVelocidad().x,p.getPosicion().y);
                }else{
                    p.setPosicion(MainActivity.anchopantalla-p.getImg().getWidth(),p.getPosicion().y);
                }
            }

            if(this.btnArr.contains((int)event.getX(),(int)event.getY())){
                //p.setPosicion(p.getPosicion().x,p.getPosicion().y-p.getVelocidad().y);
                if(p.getPosicion().y - p.getVelocidad().y > 0){
                    p.setPosicion(p.getPosicion().x,p.getPosicion().y-p.getVelocidad().y);
                }else{
                    p.setPosicion(p.getPosicion().x,0);
                }

            }

            if(this.btnAb.contains((int)event.getX(),(int)event.getY())){
                //p.setPosicion(p.getPosicion().x,p.getPosicion().y+p.getVelocidad().y);
                if(p.getPosicion().y + p.getVelocidad().y < MainActivity.altopantalla-p.getImg().getHeight()){
                    p.setPosicion(p.getPosicion().x,p.getPosicion().y+p.getVelocidad().y);
                }else{
                    p.setPosicion(p.getPosicion().x,MainActivity.altopantalla-p.getImg().getHeight());
                }
            }
        }
        p.setHitbox();
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
            this.hilo = new HiloJuego(this.holder,this);
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
