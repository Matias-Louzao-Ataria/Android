package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;

public class MainGameScreen extends BaseScreen{
    //El texto del cronometro es de 2 de alto.
    public static final float PAREDWIDTH = 1f;
    private static final float aspectRatio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
    private static final float WORLD_HEIGHT = 20;
    private static final float WORLD_WIDTH = WORLD_HEIGHT*aspectRatio;
    public static  final  float VELBALAX = 35;
    public static  final  float VELBALAY = 35;
    public static final int BOTON_WIDTH = 1;
    public static final int BOTON_HEIGHT = 1;
    private Float[] velocidadesX = {0f,VELBALAX};
    private Float[] velocidadesY = {0f,VELBALAY};
    private World world;
    private Stage stage;
    private OrthographicCamera camera;
    private BalaBox2D bala;
    private JugadorBox2D jugador;
    private MainGameContactListener contactListener;
    private Array<BalaBox2D> balas;
    private Array<Body> paredesBody;
    private Array<Fixture> paredesFixture;
    private long lastSpawnedBala,first;
    private BitmapFont textRenderer;
    private int numBalas = 15;
    private Batch batch;
    private Button botonIzq,botonDcha,botonArr,botonAbj;
    private int min = 0,seg = 0;
    private Texture textura = new Texture(Gdx.files.internal("circulo.png"));
    private Texture mapa = new Texture(Gdx.files.internal("cuadrado.png"));


    public MainGameScreen(Main parent) {
        super(parent);
    }

    @Override
    public void show() {
        this.world = new World(new Vector2(0,0),true);
        this.stage = new Stage(new ScalingViewport(Scaling.fit,WORLD_WIDTH,WORLD_HEIGHT));
        this.camera = (OrthographicCamera) this.stage.getCamera();
        this.textRenderer = new BitmapFont();
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(this.stage.getCamera().combined);
        this.contactListener = new MainGameContactListener();
        this.lastSpawnedBala = TimeUtils.nanoTime();
        this.balas  = new Array<BalaBox2D>();
        this.paredesBody  = new Array<Body>();
        this.paredesFixture = new Array<Fixture>();
        this.bala = new BalaBox2D(this.world,textura,new Vector2( 3,3));
        this.jugador = new JugadorBox2D(this.world,textura,new Vector2(5,5));
        generarBotones();
        this.world.setContactListener(this.contactListener);
        generarParedes(this.world);
        this.bala.getBody().applyLinearImpulse(new Vector2(velocidadesX[MathUtils.random(1)], velocidadesY[MathUtils.random(1)]),new Vector2(0,0),true);
        this.balas.add(bala);
        this.stage.addActor(bala);
        this.stage.addActor(this.jugador);
        Gdx.input.setInputProcessor(stage);
        this.first = TimeUtils.nanoTime();
    }

    private void generarBotones() {
        this.botonIzq = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonIzq.setPosition(1,3);
        this.botonIzq.setWidth(BOTON_WIDTH);
        this.botonIzq.setHeight(BOTON_HEIGHT);
        this.botonIzq.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("Y");
                return true;
            }
        });

        this.stage.addActor(this.botonIzq);

        this.botonDcha = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonDcha.setPosition(this.botonIzq.getX()+3,this.botonIzq.getY());
        this.botonDcha.setWidth(BOTON_WIDTH);
        this.botonDcha.setHeight(BOTON_HEIGHT);
        this.botonDcha.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("A");
                return true;
            }
        });

        this.stage.addActor(this.botonDcha);

        this.botonArr = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonArr.setPosition(this.botonIzq.getX()+1.5f,this.botonIzq.getY()+1.5f);
        this.botonArr.setWidth(BOTON_WIDTH);
        this.botonArr.setHeight(BOTON_HEIGHT);
        this.botonArr.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("X");
                return true;
            }
        });

        this.stage.addActor(this.botonArr);

        this.botonAbj = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonAbj.setPosition(this.botonIzq.getX()+1.5f,this.botonIzq.getY()-1.5f);
        this.botonAbj.setWidth(BOTON_WIDTH);
        this.botonAbj.setHeight(BOTON_HEIGHT);
        this.botonAbj.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("B");
                return true;
            }
        });
        this.stage.addActor(this.botonAbj);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0,0.7f,0.3f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.camera.lookAt(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        if(this.balas.size < this.numBalas && TimeUtils.nanoTime()-this.lastSpawnedBala >= 1000000000){
            this.lastSpawnedBala = TimeUtils.nanoTime();
            this.bala = new BalaBox2D(this.world,this.textura,new Vector2(WORLD_WIDTH/2,WORLD_HEIGHT/2));
            this.bala.getBody().applyLinearImpulse(new Vector2(velocidadesX[MathUtils.random(1)], velocidadesY[MathUtils.random(1)]),new Vector2(0,0),true);
            this.balas.add(this.bala);
            this.stage.addActor(this.bala);
        }

        if(TimeUtils.nanoTime() - this.first >= 1000000000){
            this.first = TimeUtils.nanoTime();
            this.seg++;
            if(this.seg >= 60){
                this.seg = 0;
                this.min++;
            }
        }

        //this.touchpad.setColor(Color.BLACK);
        if(this.jugador.getBody().getPosition().x >= WORLD_WIDTH - 0.5f || this.jugador.getBody().getPosition().y >= WORLD_HEIGHT - 2.5f || this.jugador.getBody().getPosition().x <= 0 + 0.5f || this.jugador.getBody().getPosition().y <= 0 + 0.5f){
            this.jugador.getBody().setLinearVelocity(0,0);
        }//TODO:Cambiar la condición con constantes para que este mas claro.

        this.batch.begin();
        this.batch.setColor(Color.BLUE);
        this.batch.draw(this.mapa,0,0,WORLD_WIDTH,WORLD_HEIGHT);
        this.textRenderer.setColor(Color.WHITE);
        this.textRenderer.getData().setScale(0.2f,0.2f);
        this.textRenderer.draw(this.batch,String.format("%d: %d",this.min,this.seg),WORLD_WIDTH/2 -3,WORLD_HEIGHT);
        this.textRenderer.draw(this.batch,String.format("%d",Gdx.graphics.getFramesPerSecond()),WORLD_WIDTH-4,WORLD_HEIGHT);
        this.batch.end();
        this.world.step(delta,6,2);
        this.stage.act();
        this.stage.draw();
    }

    public void generarParedes(World world){
        //Derecha de la pantalla
        PolygonShape paredShapeDcha = new PolygonShape();
        paredShapeDcha.setAsBox(PAREDWIDTH,WORLD_HEIGHT*2);
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

        this.batch.dispose();
        this.textRenderer.dispose();
        this.mapa.dispose();
        this.world.dispose();
        this.stage.dispose();
    }

    @Override
    public void resize(int width, int height) {//TODO:Destruir paredes y generar unas nuevas
        //System.out.println(aspectRatio != Gdx.graphics.getWidth()/Gdx.graphics.getHeight() && this.paredesBody != null && this.paredesFixture != null && this.paredesFixture.size > 0 && this.paredesBody.size > 0);
        this.stage.getViewport().update(width,height);
        /*if(aspectRatio != Gdx.graphics.getWidth()/Gdx.graphics.getHeight() && this.paredesBody != null && this.paredesFixture != null && this.paredesFixture.size > 0 && this.paredesBody.size > 0){
            destruirParedes();
            generarParedes(this.world);
        }*/
    }

    public World getWorld() {
        return world;
    }

    public JugadorBox2D getJugador() {
        return jugador;
    }

//mutiplicar = tener pixeles y dividir = tener metros
    /*private boolean debug;
    public static final float PPM = 20;
    private World world;
    private Bala b;
    private float aspectRatio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
    private Stage stage;
    private float worldWidth,worldHeight;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;
    private Generators generator;

    public MainGameScreen(Main parent,boolean debug) {
        super(parent);
        this.debug = debug;
    }

    @Override
    public void show() {
        this.world = new World(new Vector2(0,0),true);
        this.generator = new Generators();
        this.stage = new Stage(new FitViewport(500*aspectRatio,500));
        this.worldWidth = this.stage.getViewport().getWorldWidth();
        this.worldHeight = this.stage.getViewport().getWorldHeight();
        if(this.debug){
            this.camera = new OrthographicCamera(20*aspectRatio,20);
            this.renderer = new Box2DDebugRenderer();
        }
        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                b.setX(50);
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
        Body bb = this.generator.balaBodyGenerator(this.world,new Vector2(20/PPM,this.worldHeight/2/PPM));//metros
        Fixture f = this.generator.balaFixtureGenerator(this.generator.balaShapeGenerator(20f/ PPM),bb);
        this.b = new Bala(new Texture(Gdx.files.internal("circulo.png")),bb,f,this.world,PPM);
        this.stage.addActor(this.b);
        generarParedes(this.world);
    }

    @Override
    public void render(float delta) {
        //Clear Screen
        Gdx.gl.glClearColor(0,0.7f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.world.step(1/60f,6,2);
        this.stage.act();
        this.stage.draw();

        //this.stage.getCamera().position.set(this.b.getX(),this.b.getY(),0);
        //render
        if(debug){
            //this.camera.position.set(this.b.getX(),this.b.getY(),0);
            this.renderer.render(this.world,this.camera.combined.scl(PPM));
        }
    }

    @Override
    public void dispose() {
        //this.world.destroyBody();
        this.b.detach();
        this.world.dispose();
        this.stage.dispose();
        this.renderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        this.stage.getViewport().update(width,height);
        this.worldWidth = this.stage.getViewport().getWorldWidth();
        this.worldHeight = this.stage.getViewport().getWorldHeight();
        //this.camera.setToOrtho(false,width/2,height/2);
    }*/



































    /*public static final int balaVelocidadX = 10;
    private float PXINM = 0;
    private Stage stage;
    private World zawarudo;
    private Fixture balaFixture;
    private Body balaBody;
    private Generators generator;
    private Bala b;
    private int width = Gdx.graphics.getWidth(),height = Gdx.graphics.getHeight();
    float aspectRatio = width/height;
    private long lastGenratedBala;
    private Texture balaTexture = new Texture(Gdx.files.internal("circulo.png"));

    public MainGameScreen(Main parent) {
        super(parent);
    }

    @Override
    public void show() {
        this.stage = new Stage(new FitViewport(height*aspectRatio,height));
        this.zawarudo = new World(new Vector2(0,0),true);
        this.generator = new Generators();
        this.PXINM = height/Main.ScreenMeters;//Cuantos metros va a valer un pixel a partir de una medida que he fijado en Main.
    }

    public void generarBala() {
        CircleShape shape = this.generator.balaShapeGenerator(1f);
        //int rwidth = MathUtils.random(0,width-(int)(shape.getRadius()*PXINM)),rheight = MathUtils.random(0,height-(int)(shape.getRadius()*PXINM));
        //System.out.println(rwidth+","+rheight);
        this.balaBody = this.generator.balaBodyGenerator(this.zawarudo,new Vector2((0),0));
        this.balaFixture = this.generator.balaFixtureGenerator(shape,this.balaBody);

        //this.lastGenratedBala = TimeUtils.nanoTime();
        this.b = new Bala(this.balaTexture,balaBody,balaFixture,this.zawarudo,this.PXINM);
        this.stage.addActor(this.b);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0.7f,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //this.stage.getCamera().position.set(0,0,0);
        this.stage.getCamera().update();
        //System.out.println(Gdx.graphics.getFramesPerSecond());
        this.stage.act();
        //long timeNow = TimeUtils.nanoTime();
        /*if(timeNow-this.lastGenratedBala >= 1000000000){
            this.lastGenratedBala = timeNow;
            this.balacont++;
            generarBala(1);
        }*/
        /*this.zawarudo.step(delta,6,2);
        this.stage.draw();
    }

    @Override
    public void hide() {
        this.b.detach();
        this.balaBody.destroyFixture(this.balaFixture);
        this.zawarudo.destroyBody(this.balaBody);
        this.stage.dispose();
        this.zawarudo.dispose();
    }*/
}
