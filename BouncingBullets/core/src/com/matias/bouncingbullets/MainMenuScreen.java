package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends BaseScreen{

    private Skin skin;
    private ImageButton btnJugar,btnOpciones,btnCreditos;
    private Texture mapa = new Texture(Gdx.files.internal("cuadrado.png"));
    private Table table;

    public MainMenuScreen(Main parent) {
        super(parent);
    }

    @Override
    public void show() {
        setStage(new Stage(new FitViewport(Gdx.graphics.getHeight()*16/9f,Gdx.graphics.getHeight())));
        this.table = new Table();
        this.table.setFillParent(true);
        getStage().addActor(this.table);

        this.skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
        this.btnJugar = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("testbutton.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))));
        this.btnJugar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getParent().setScreen(new MainGameScreen(getParent()));
            }
        });
        this.table.add(this.btnJugar).height(Gdx.graphics.getHeight()/3f);
        this.table.row();

        this.btnOpciones = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("testbutton.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))));
        this.btnOpciones.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                getParent().setScreen(new SettingsScreen(getParent()));
            }
        });
        this.table.add(this.btnOpciones).height(Gdx.graphics.getHeight()/3f);
        this.table.row();

        this.btnCreditos = new ImageButton(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("testbutton.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))),new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("cuadrado.png")))));
        this.btnCreditos.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.platformSpecific.hacerFoto();

            }
        });
        this.table.add(this.btnCreditos).height(Gdx.graphics.getHeight()/3f);
//        this.table.row();
        Gdx.input.setInputProcessor(getStage());
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0,0.7f,0.5f,0.35f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*this.stage.getCamera().lookAt(Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/2f,0);
        this.stage.getCamera().update();*/
        getStage().act();
        getStage().getBatch().begin();
        getStage().getBatch().draw(this.mapa,0,0,Gdx.graphics.getHeight()*16/9f,Gdx.graphics.getHeight());
        getStage().getBatch().end();
        getStage().draw();
    }

    @Override
    public void resize(int width, int height) {
        getStage().getViewport().update(width,height);
    }

    @Override
    public void dispose() {
        getStage().dispose();
        this.skin.dispose();
    }
}
