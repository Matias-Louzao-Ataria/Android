package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;
import java.io.File;

public class MainGameScreen extends BaseScreen{
    //El texto del cronometro es de 2 de alto.
    private OrthographicCamera camera;
    public static final float PAREDWIDTH = 1f;
    private static float WORLD_HEIGHT = 20;
    private static float WORLD_WIDTH = WORLD_HEIGHT*16/9;
    public static  final  float VELBALAX = 30;
    public static  final  float VELBALAY = 30;
    public static final int BOTON_WIDTH = 1;
    public static final int BOTON_HEIGHT = 1;
    private final Float[] velocidadesX = {0f,VELBALAX};
    private final Float[] velocidadesY = {0f,VELBALAY};
    private World world;
//    private Stage stage;
    private JugadorBox2D jugador;
    private MainGameContactListener contactListener;
    private Array<BalaBox2D> balas;
    public static Array<BalaBox2D> borrarBalas = new Array<BalaBox2D>();
    private Array<Body> paredesBody;
    private Array<Fixture> paredesFixture;
    private long  cronometro = 0;
    private int lastSpawnedBala = 0, lastSpawnedPowerUp = 0,cronometroInvencible = 0;
    private BitmapFont textRenderer;
    private int numBalas = 5,min = 0,seg = 0,hpBaroffset = 0;
    private Array<PowerUpObject> powerUps;
//    private Batch batch;
    private Button botonIzq,botonDcha,botonArr,botonAbj,botonObj;
    private float lastX = 0,lastY = 0,offset = 10;
    private Texture textura = new Texture(Gdx.files.internal("circulo.png"));
    private Texture mapa = new Texture(Gdx.files.internal("fondostick.png"));
    private boolean first = true;//Es true si es la primera vez que se ejecuta render.
    private Array<UIBaseActor> hpBar;
    private Array<RocaBox2D> rocas;
    private UIBaseActor playerPowerUp = null;

    public MainGameScreen(Main parent) {
        super(parent);
    }

    public MainGameScreen(Main parent,File file) {
        super(parent);
    }

    @Override
    public void show() {
        Gdx.input.setCatchKey(Input.Keys.BACK,true);
        this.world = new World(new Vector2(0,0),true);

        setStage(new Stage(new ScalingViewport(Scaling.fit,WORLD_WIDTH,WORLD_HEIGHT)));
        this.camera = (OrthographicCamera) getStage().getCamera();
        this.textRenderer = new BitmapFont(Gdx.files.internal("fuente.fnt"));
//        this.batch = new SpriteBatch();
        this.powerUps = new Array<PowerUpObject>();
//        this.batch.setProjectionMatrix(this.stage.getCamera().combined);

        this.jugador = new JugadorBox2D(this.world,new Texture(Gdx.files.internal("tv.png")),new Vector2(5,5));
        this.contactListener = new MainGameContactListener(this.jugador,this.world);
        this.lastSpawnedBala = this.getInstanteEnJuego();
        this.balas  = new Array<BalaBox2D>();
        this.paredesBody  = new Array<Body>();
        this.paredesFixture = new Array<Fixture>();
        this.hpBar = new Array<UIBaseActor>();

        this.rocas = new Array<RocaBox2D>();

        this.world.setContactListener(this.contactListener);
        generarParedes(this.world);

//            this.stage.addActor(roca);
        getStage().addActor(this.jugador);
        InputMultiplexer input = new InputMultiplexer(getStage());
        Gdx.input.setInputProcessor(new GestureDetector(new GestureDetector.GestureListener() {
            @Override
            public boolean touchDown(float x, float y, int pointer, int button) {
                System.out.println(pointer);
                if(pointer == 1){
                    if(jugador.getPowerUp() != null){
                        //TODO Programar los objetos
                        switch (jugador.getPowerUp()){
                            case Corazon:
                                if(jugador.getHp() < JugadorBox2D.maxHP){
                                    jugador.addHp(1);
                                }
                                break;

                            case Boton:
                                for (BalaBox2D bala : balas) {
                                    Body body = bala.getBody();
                                    body.setLinearVelocity(body.getLinearVelocity().x*-1,body.getLinearVelocity().y*-1);
                                }
                                break;

                            case Chaleco:
                                cronometroInvencible = getInstanteEnJuego();
                                jugador.setInvencible(true);
                                break;
                            default:
                        }
                        jugador.setPowerUp(null);
                    }
                }
                return false;
            }

            @Override
            public boolean tap(float x, float y, int count, int button) {
                return false;
            }

            @Override
            public boolean longPress(float x, float y) {
                return false;
            }

            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                return false;
            }

            @Override
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                System.out.println(String.format("x: %f,y: %f,deltaX: %f,deltaY: %f",x,y,deltaX,deltaY));
                //body.setLinearVelocity(deltaX+20,-deltaY+20);
                if(x != lastX && y != lastY && (Math.abs(deltaX) > offset || Math.abs(deltaY) > offset)){
                    if(Math.abs(deltaX) > offset && Math.abs(deltaY) > offset){
                        jugador.getBody().setLinearVelocity(new Vector2(Math.signum(deltaX)*7,Math.signum(-deltaY)*7));
                    }else if(Math.abs(deltaY) > offset){
                        jugador.getBody().setLinearVelocity(new Vector2(0,Math.signum(-deltaY)*7));
                    }else if(Math.abs(deltaX) > offset){
                        jugador.getBody().setLinearVelocity(new Vector2(Math.signum(deltaX)*7,0));
                    }
                    lastX = x;
                    lastY = y;
                }
                return true;
            }

            @Override
            public boolean panStop(float x, float y, int pointer, int button) {
                return false;
            }

            @Override
            public boolean zoom(float initialDistance, float distance) {
                return false;
            }

            @Override
            public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
                return false;
            }

            @Override
            public void pinchStop() {

            }
        }));
        this.cronometro = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0,0.7f,0.3f,1f);
        if(this.jugador.getHp() > 0){
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            this.camera.lookAt(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
            if(this.balas.size < this.numBalas && this.getInstanteEnJuego() - this.lastSpawnedBala >= 1){
                generarBala();
            }

            for (PowerUpObject powerUp:this.powerUps) {
                if(powerUp.isDesaparecer()){
                    for (Actor a:getStage().getActors()) {
                        if(a == powerUp){
                            a.remove();
                            powerUp.dispose();
                        }
                    }
                }
            }
            if(this.getInstanteEnJuego() - this.lastSpawnedPowerUp >= 5){
                if(MathUtils.random(1,100) > 60){
                    generarPowerUp();
                }
            }

            if(this.jugador.isInvencible() && this.getInstanteEnJuego() - this.cronometroInvencible >= 7){
                this.jugador.setInvencible(false);
            }

            if(this.playerPowerUp == null && this.jugador.getPowerUp() != null){
                this.playerPowerUp = new UIBaseActor(new Texture(Gdx.files.internal("boton2.png")),WORLD_WIDTH-UIBaseActor.WIDTH-8,WORLD_HEIGHT-UIBaseActor.HEIGHT);
                getStage().addActor(this.playerPowerUp);
            }else if(this.jugador.getPowerUp() == null && this.playerPowerUp != null){
                this.playerPowerUp.remove();
                this.playerPowerUp = null;
            }

            if(borrarBalas.size > 0){
                eliminarBala();
            }

            System.out.println(this.jugador.getHp());
            System.out.println(this.hpBar.size);

            for (BalaBox2D bala : this.balas) {
                if(this.getInstanteEnJuego() - bala.getLastChecked() >= 5 && bala.getBody().getLinearVelocity().x == 0 && bala.getBody().getLinearVelocity().y == 0){
                    bala.getBody().setLinearVelocity(velocidadesX[1]/3,velocidadesY[1]/3);
                    bala.setLastChecked(getInstanteEnJuego());
                }
            }

            if(TimeUtils.nanoTime() - this.cronometro >= 1000000000){
                this.cronometro = TimeUtils.nanoTime();
                this.seg++;
                if(this.seg >= 60){
                    this.seg = 0;
                    this.min++;
                }
            }

//            this.batch.begin();
            getStage().getBatch().begin();
//            this.stage.getBatch().setColor(Color.BLUE);
            getStage().getBatch().draw(this.mapa,0,0,WORLD_WIDTH,WORLD_HEIGHT);
            this.textRenderer.setColor(Color.WHITE);
            this.textRenderer.getData().setScale(0.2f,0.2f);
            this.textRenderer.draw(getStage().getBatch(),String.format("%d : %d",this.min,this.seg),WORLD_WIDTH/2 -3,WORLD_HEIGHT);
            this.textRenderer.draw(getStage().getBatch(),String.format("%d",Gdx.graphics.getFramesPerSecond()),WORLD_WIDTH-4,WORLD_HEIGHT);

            if(this.first){
                generarHpBar();
            }else if(this.hpBar.size > this.jugador.getHp()){
                updateHp();
            }else if(this.hpBar.size < this.jugador.getHp()){
                addHp();
            }

            getStage().getBatch().end();
            this.world.step(delta,6,2);
            getStage().act(delta);
            getStage().draw();
            this.first = false;
        }else{
            //TODO:Poner una textura que indique que has perdido.
//            this.jugador.dispose();
//            for (BalaBox2D balaActual : balas) {
//                balaActual.dispose();
//            }
//            destruirParedes();
//            this.world.dispose();
//            this.batch.begin();
//            this.stage.getBatch().begin();
//            this.textRenderer.draw(this.stage.getBatch(),String.format("%d",Gdx.graphics.getFramesPerSecond()),WORLD_WIDTH-4,WORLD_HEIGHT);
//            this.stage.getBatch().end();
//            this.batch.end();
        }
    }

    private void generarHpBar() {
        for (int i = 0; i < this.jugador.getHp()-this.hpBar.size; i++) {
            addHp();
        }
    }

    private void addHp() {
        UIBaseActor hp = new UIBaseActor(new Texture(Gdx.files.internal("corazon.png")),this.hpBaroffset,WORLD_HEIGHT - UIBaseActor.HEIGHT);
        this.hpBar.add(hp);
        getStage().addActor(hp);
        this.hpBaroffset+=2;
    }

    private void updateHp(){
        for (int i = this.hpBar.size;i > this.jugador.getHp(); i--) {
            UIBaseActor hpActual = this.hpBar.get(i-1);
            hpActual.remove();
            this.hpBar.removeValue(hpActual,true);
            this.hpBaroffset-=2;
        }
    }

    private void generarPowerUp() {
        int max = PowerUpObject.TipoObj.values().length;
        lastSpawnedPowerUp = getInstanteEnJuego();
        PowerUpObject.TipoObj tipo = PowerUpObject.TipoObj.values()[MathUtils.random(0,max-1)];
        Texture texture = null;
//        Sprite s = new Sprite(texture);
        switch (tipo){
            case Boton:
                texture  = new Texture(Gdx.files.internal("boton2.png"));
                break;
            case Chaleco:
                 texture = new Texture(Gdx.files.internal("chaleco.png"));
                break;
            case Corazon:
                texture = new Texture(Gdx.files.internal("corazon.png"));
                break;
        }
        PowerUpObject powerUp = new PowerUpObject(this.world,texture,new Vector2(MathUtils.random(PowerUpObject.WIDTH,WORLD_WIDTH-PowerUpObject.WIDTH),MathUtils.random(PowerUpObject.HEIGHT,WORLD_HEIGHT -2 -PowerUpObject.HEIGHT)), tipo,TimeUtils.nanoTime());
        this.powerUps.add(powerUp);
        getStage().addActor(powerUp);
    }

    public int getInstanteEnJuego() {
        return (this.min*60)+this.seg;
    }

    private void generarBala() {
        this.lastSpawnedBala = getInstanteEnJuego();
        BalaBox2D bala = new BalaBox2D(this.world, new Texture(Gdx.files.internal("bala.png")), new Vector2(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), this.getInstanteEnJuego());
        bala.getBody().applyLinearImpulse(new Vector2(velocidadesX[MathUtils.random(1)], velocidadesY[MathUtils.random(1)]),new Vector2(0,0),true);
        this.balas.add(bala);
        getStage().addActor(bala);
    }

    private void eliminarBala() {
        for (BalaBox2D bala : borrarBalas) {
            getStage().getActors().removeIndex(getStage().getActors().indexOf(bala,true));
            this.balas.removeIndex(this.balas.indexOf(bala,true));
            borrarBalas.removeIndex(borrarBalas.indexOf(bala,true));
            bala.dispose();
        }
    }

    private void generarBotones() {
        this.botonIzq = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonIzq.setPosition(1,3);
        this.botonIzq.setWidth(BOTON_WIDTH);
        this.botonIzq.setHeight(BOTON_HEIGHT);
        this.botonIzq.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                jugador.getBody().setLinearVelocity(new Vector2(-10,0));
                return true;
            }
        });

        getStage().addActor(this.botonIzq);

        this.botonDcha = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonDcha.setPosition(this.botonIzq.getX()+3,this.botonIzq.getY());
        this.botonDcha.setWidth(BOTON_WIDTH);
        this.botonDcha.setHeight(BOTON_HEIGHT);
        this.botonDcha.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                jugador.getBody().setLinearVelocity(new Vector2(10,0));
                return true;
            }
        });

        getStage().addActor(this.botonDcha);

        this.botonArr = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonArr.setPosition(this.botonIzq.getX()+1.5f,this.botonIzq.getY()+1.5f);
        this.botonArr.setWidth(BOTON_WIDTH);
        this.botonArr.setHeight(BOTON_HEIGHT);
        this.botonArr.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                jugador.getBody().setLinearVelocity(new Vector2(0,10));
                return true;
            }
        });

        getStage().addActor(this.botonArr);

        this.botonAbj = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonAbj.setPosition(this.botonIzq.getX()+1.5f,this.botonIzq.getY()-1.5f);
        this.botonAbj.setWidth(BOTON_WIDTH);
        this.botonAbj.setHeight(BOTON_HEIGHT);
        this.botonAbj.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                jugador.getBody().setLinearVelocity(new Vector2(0,-10));
                return true;
            }
        });
        getStage().addActor(this.botonAbj);
    }

    public void generarParedes(World world){
        //Derecha de la pantalla
        PolygonShape paredShapeDcha = new PolygonShape();
        paredShapeDcha.setAsBox(PAREDWIDTH,WORLD_HEIGHT);
        BodyDef paredBodyDefDcha = new BodyDef();
        paredBodyDefDcha.position.set(new Vector2(WORLD_WIDTH+PAREDWIDTH,WORLD_HEIGHT));
        paredBodyDefDcha.type = BodyDef.BodyType.StaticBody;
        Body paredBodyDcha = world.createBody(paredBodyDefDcha);
        Fixture paredFixtureDcha = paredBodyDcha.createFixture(paredShapeDcha,1);
        paredFixtureDcha.setUserData("paredDcha");
        this.paredesBody.add(paredBodyDcha);
        this.paredesFixture.add(paredFixtureDcha);

        //Izquierda de la pantalla
        BodyDef paredBodyDefIzq = new BodyDef();
        paredBodyDefIzq.position.set(new Vector2(0-PAREDWIDTH,WORLD_HEIGHT));
        paredBodyDefIzq.type = BodyDef.BodyType.StaticBody;
        Body paredBodyIzq = world.createBody(paredBodyDefIzq);
        Fixture paredFixtureIzq = paredBodyIzq.createFixture(paredShapeDcha,1);
        paredFixtureIzq.setUserData("paredIzq");
        this.paredesBody.add(paredBodyIzq);
        this.paredesFixture.add(paredFixtureIzq);

        //Arriba de la pantalla
        PolygonShape paredShapeArr = new PolygonShape();
        paredShapeArr.setAsBox(WORLD_WIDTH, PAREDWIDTH);
        BodyDef paredBodyDefArr = new BodyDef();
        paredBodyDefArr.position.set(new Vector2(0,WORLD_HEIGHT+PAREDWIDTH-2));
        paredBodyDefArr.type = BodyDef.BodyType.StaticBody;
        Body paredBodyArr = world.createBody(paredBodyDefArr);
        Fixture paredFixtureArr = paredBodyArr.createFixture(paredShapeArr,1);
        paredFixtureArr.setUserData("paredArr");
        this.paredesBody.add(paredBodyArr);
        this.paredesFixture.add(paredFixtureArr);

        //Abajo de la pantalla
        BodyDef paredBodyDefAbj = new BodyDef();
        paredBodyDefAbj.position.set(new Vector2(0,0-PAREDWIDTH));
        paredBodyDefAbj.type = BodyDef.BodyType.StaticBody;
        Body paredBodyAbj = world.createBody(paredBodyDefAbj);
        Fixture paredFixtureAbj = paredBodyAbj.createFixture(paredShapeArr,1);
        paredFixtureAbj.setUserData("paredAbj");
        this.paredesBody.add(paredBodyAbj);
        this.paredesFixture.add(paredFixtureAbj);
        paredShapeArr.dispose();
        paredShapeDcha.dispose();
    }

    public void destruirParedes() {
        for (int i = 0; i < this.paredesBody.size; i++) {
            Body bodyActual = this.paredesBody.get(i);
            Fixture fixtureActual = this.paredesFixture.get(i);
            bodyActual.destroyFixture(fixtureActual);
            this.world.destroyBody(bodyActual);
        }
    }

    @Override
    public void dispose() {
        this.jugador.dispose();
        for (BalaBox2D balaActual : balas) {
            balaActual.dispose();
        }
        destruirParedes();


//        this.batch.dispose();
        this.textRenderer.dispose();
        this.mapa.dispose();
        this.textura.dispose();
        if(this.world != null){
            this.world.dispose();
        }
        getStage().dispose();
    }

    @Override
    public void resize(int width, int height) {
        getStage().getViewport().update(width,height);
    }

    public World getWorld() {
        return world;
    }

    public JugadorBox2D getJugador() {
        return jugador;
    }


//            this.botonObj = new Button(new SpriteDrawable(new Sprite(mapa)));
//            this.botonObj.setPosition(WORLD_WIDTH-BOTON_WIDTH,WORLD_HEIGHT/2-BOTON_HEIGHT);
//            this.botonObj.setWidth(BOTON_WIDTH);
//            this.botonObj.setHeight(BOTON_HEIGHT);
//            this.botonObj.addListener(new EventListener() {
//                @Override
//                public boolean handle(Event event) {
//                    if(jugador.getPowerUp() != null){
//
//                        switch (jugador.getPowerUp()){
//                            case Bate:
//
//                                break;
//
//                            case BateDorado:
//                                break;
//
//                            case Boton:
//                                for (BalaBox2D bala : balas) {
//                                    Body body = bala.getBody();
//                                    body.setLinearVelocity(body.getLinearVelocity().x*-1,body.getLinearVelocity().y*-1);
//                                }
//                                break;
//                        }
//                        jugador.setPowerUp(null);
//                    }
//                    return true;
//                }
//            });
//            this.stage.addActor(this.botonObj);


//        if(this.bate != null){
//            this.bate.dispose();

//        }


    //System.out.println(aspectRatio != Gdx.graphics.getWidth()/Gdx.graphics.getHeight() && this.paredesBody != null && this.paredesFixture != null && this.paredesFixture.size > 0 && this.paredesBody.size > 0);

/*if(aspectRatio != Gdx.graphics.getWidth()/Gdx.graphics.getHeight() && this.paredesBody != null && this.paredesFixture != null && this.paredesFixture.size > 0 && this.paredesBody.size > 0){
            destruirParedes();
            generarParedes(this.world);
        }*/


    //        this.bala = new BalaBox2D(this.world,textura,new Vector2( 3,3));
//        generarPowerUp();
    //generarBotones();

    //        SpriteDrawable fondo = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("fondostick.png"))));
//        SpriteDrawable palanca = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("palanca.png"))));
//        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle(fondo,palanca);
//        this.touchpad = new Touchpad(0.1f, touchpadStyle);
//        this.touchpad.setBounds(1, 1, 7, 7);

//        this.jugador.getBody().setLinearVelocity(new Vector2(this.touchpad.getKnobPercentX()*velocidadesX[1]*0.3f,this.touchpad.getKnobPercentY()*velocidadesY[1]*0.3f));

//        if(this.bate != null && this.bate.vecesGolpeado >= 3){
//            this.world.destroyJoint(this.bate.joint);
//            this.bate.remove();
//            this.bate.dispose();
//            this.stage.getActors().removeIndex(this.stage.getActors().indexOf(this.bate, true));
//        }

//        if(this.jugador.getPowerUp() != null){
//            switch (jugador.getPowerUp()){
//                case Bate:
//                    this.bate = new Bate(world,new Texture(Gdx.files.internal("bate.png")),this.jugador,new Vector2(this.jugador.getBody().getPosition().x+1,this.jugador.getBody().getPosition().y));
//                    DistanceJointDef jointDef = new DistanceJointDef();
//                    jointDef.bodyA = this.jugador.getBody();
//                    jointDef.bodyB = this.bate.getBody();
//                    jointDef.collideConnected = false;
//                    jointDef.localAnchorA.set(0.6f,0);
//                    this.bate.joint = this.world.createJoint(jointDef);
//                    this.stage.addActor(this.bate);
//                    break;
//
//                case BateDorado:
//                    break;
//
//                case Boton:
//                    break;
//            }
//        }




}
