package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SettingsScreen extends BaseScreen{
    private Skin skin;
    private Texture mapa = new Texture(Gdx.files.internal("cuadrado.png"));
    private CheckBox musica,vibracion,fps;

    public SettingsScreen(Main parent) {
        super(parent);
    }

    @Override
    public void show() {
        setStage(new Stage(new FitViewport(Gdx.graphics.getHeight()*16/9f,Gdx.graphics.getHeight())));
        this.skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
        this.musica = new CheckBox("Desactivar musica",this.skin);
        this.musica.setPosition(20,Gdx.graphics.getHeight()/2f);
        this.musica.scaleBy(5f);
        getStage().addActor(this.musica);
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
