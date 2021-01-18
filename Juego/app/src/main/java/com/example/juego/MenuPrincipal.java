package com.example.juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

public class MenuPrincipal extends SurfaceView implements SurfaceHolder.Callback {

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Context context;
    private Bitmap bitmapFondo;
    private int anchoPantalla=1;
    private int altoPantalla=1;
    private HiloMenu hiloMenu = null;
    private Rect botonJugar = null;
    private Rect botonMapas = null;
    private Rect botonOpciones = null;
    private Rect botonCreditos = null;

    public MenuPrincipal(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.context = context;
        hiloMenu = new HiloMenu();
        setFocusable(true);
        bitmapFondo = null;
        this.botonJugar = new Rect(50,75,600,275);
        this.botonMapas = new Rect(50,325,600,525);
        this.botonOpciones = new Rect(50,575,600,775);
        this.botonCreditos = new Rect(50,825,600,1025);
    }
    public void actualizarFisica(){
        
    }
    public void dibujar(Canvas canvas) {
        try {
            canvas.drawColor(Color.GREEN);
            Paint pR = new Paint();
            pR.setColor(Color.CYAN);
            canvas.drawRect(this.botonJugar,pR);
            canvas.drawRect(this.botonMapas,pR);
            canvas.drawRect(this.botonOpciones,pR);
            canvas.drawRect(this.botonCreditos,pR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //CALLBACKS
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        hiloMenu.setFuncionando(true);
        if (hiloMenu.getState() == Thread.State.NEW){
            hiloMenu.start();
        }
        if (hiloMenu.getState() == Thread.State.TERMINATED) {
            hiloMenu =new HiloMenu();
            hiloMenu.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;
        altoPantalla = height;
        MainActivity.altopantalla = this.altoPantalla;
        MainActivity.anchopantalla = this.anchoPantalla;
        hiloMenu.setSurfaceSize(width,height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(this.botonJugar.contains((int)event.getX(),(int)event.getY())){
            Toast.makeText(context, "JOOOOOOOOOOOOOJO!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.context,JuegoActivitie.class);
            this.context.startActivity(intent);
        }else{
            Toast.makeText(context, "SHIZAAAAAAAAAA!", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hiloMenu.setFuncionando(false);
        try {
            hiloMenu.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class HiloMenu extends Thread{
        public HiloMenu(){
        }
        @Override
        public void run() {
            while (running) {
                Canvas c = null;
                try {
                    if (!surfaceHolder.getSurface().isValid()) continue;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        c = surfaceHolder.lockHardwareCanvas();
                    }else{
                        c = surfaceHolder.lockCanvas();
                    }
                    synchronized (surfaceHolder) {
                        actualizarFisica();
                        dibujar(c);
                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        void setFuncionando(boolean flag) {
            running = flag;
        }

        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {
                if (bitmapFondo != null) {
                    bitmapFondo = Bitmap.createScaledBitmap( bitmapFondo, width, height, true);
                }
            }
        }
    }
}
