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
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.*;

public class MainGameScreen extends BaseScreen{
    //El texto del cronometro es de 2 de alto.
    public static boolean deletePowerUp;
    public static final float PAREDWIDTH = 1f;
    private static final float aspectRatio = Gdx.graphics.getWidth()/Gdx.graphics.getHeight();
    private static final float WORLD_HEIGHT = 20;
    private static final float WORLD_WIDTH = WORLD_HEIGHT*aspectRatio;
    public static  final  float VELBALAX = 40;
    public static  final  float VELBALAY = 40;
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
    private int numBalas = 10;
    private PowerUpObject powerUp;
    private Batch batch;
    private Bate bate;
    private Touchpad touchpad;
    private Button botonIzq,botonDcha,botonArr,botonAbj,botonObj;
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
        this.jugador = new JugadorBox2D(this.world,mapa,new Vector2(5,5));
        this.contactListener = new MainGameContactListener(this.jugador,this.world);
        this.lastSpawnedBala = TimeUtils.nanoTime();
        this.balas  = new Array<BalaBox2D>();
        this.paredesBody  = new Array<Body>();
        this.paredesFixture = new Array<Fixture>();
        this.bala = new BalaBox2D(this.world,textura,new Vector2( 3,3));
        this.powerUp = new PowerUpObject(this.world,new Texture(Gdx.files.internal("bate.png")),new Vector2(15,15), PowerUpObject.TipoObj.Bate);
        //generarBotones();

        this.botonObj = new Button(new SpriteDrawable(new Sprite(mapa)));
        this.botonObj.setPosition(WORLD_WIDTH-BOTON_WIDTH,WORLD_HEIGHT/2-BOTON_HEIGHT);
        this.botonObj.setWidth(BOTON_WIDTH);
        this.botonObj.setHeight(BOTON_HEIGHT);
        this.botonObj.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                if(jugador.getPowerUp() != null){
                    //TODO Programar los objetos
                    switch (jugador.getPowerUp()){
                        case Bate:

                            break;

                        case BateDorado:
                            break;

                        case Boton:
                            break;
                    }
                    jugador.setPowerUp(null);
                }
                return true;
            }
        });
        this.stage.addActor(this.botonObj);

        RocaBox2D roca = new RocaBox2D(world,mapa,new Vector2(10,10));
        this.stage.addActor(roca);
        SpriteDrawable fondo = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("fondostick.png"))));
        SpriteDrawable palanca = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("palanca.png"))));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle(fondo,palanca);
        this.touchpad = new Touchpad(0.1f, touchpadStyle);
        this.touchpad.setBounds(1, 1, 7, 7);
        this.world.setContactListener(this.contactListener);
        generarParedes(this.world);
        this.bala.getBody().applyLinearImpulse(new Vector2(velocidadesX[MathUtils.random(1)], velocidadesY[MathUtils.random(1)]),new Vector2(0,0),true);
        this.stage.addActor(touchpad);
        this.balas.add(bala);
        this.stage.addActor(bala);
        this.stage.addActor(this.powerUp);
        this.stage.addActor(this.jugador);
        Gdx.input.setInputProcessor(stage);
        this.first = TimeUtils.nanoTime();
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

        this.jugador.getBody().setLinearVelocity(new Vector2(this.touchpad.getKnobPercentX()*velocidadesX[1]*0.3f,this.touchpad.getKnobPercentY()*velocidadesY[1]*0.3f));

        if(this.bate != null && this.bate.vecesGolpeado >= 3){
            this.bate.dispose();
        }

        if(this.jugador.getPowerUp() != null && this.bate == null){
            switch (jugador.getPowerUp()){
                case Bate:
                    this.bate = new Bate(world,new Texture(Gdx.files.internal("bate.png")),this.jugador,new Vector2(this.jugador.getBody().getPosition().x+1,this.jugador.getBody().getPosition().y));
                    DistanceJointDef jointDef = new DistanceJointDef();
                    jointDef.bodyA = this.jugador.getBody();
                    jointDef.bodyB = this.bate.getBody();
                    jointDef.collideConnected = false;
                    jointDef.localAnchorA.set(0.6f,0);
                    this.bate.joint = this.world.createJoint(jointDef);
                    this.stage.addActor(this.bate);
                    break;

                case BateDorado:
                    break;

                case Boton:
                    break;
            }
        }

        if(deletePowerUp){
            for (Actor a:this.stage.getActors()) {
                if(a == this.powerUp){
                    a.remove();
                    this.powerUp.dispose();
                }
            }
            deletePowerUp = false;
        }

        this.batch.begin();
        this.batch.setColor(Color.BLUE);
        this.batch.draw(this.mapa,0,0,WORLD_WIDTH,WORLD_HEIGHT);
        this.textRenderer.setColor(Color.WHITE);
        this.textRenderer.getData().setScale(0.2f,0.2f);
        this.textRenderer.draw(this.batch,String.format("%d: %d",this.min,this.seg),WORLD_WIDTH/2 -3,WORLD_HEIGHT);
        this.textRenderer.draw(this.batch,String.format("%d",Gdx.graphics.getFramesPerSecond()),WORLD_WIDTH-4,WORLD_HEIGHT);
        this.batch.end();
        this.world.step(delta,6,2);
        this.stage.act(delta);
        this.stage.draw();
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

        this.stage.addActor(this.botonIzq);

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

        this.stage.addActor(this.botonDcha);

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

        this.stage.addActor(this.botonArr);

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
        this.stage.addActor(this.botonAbj);
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

        if(this.bate != null){
            this.bate.dispose();
        }
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



}
