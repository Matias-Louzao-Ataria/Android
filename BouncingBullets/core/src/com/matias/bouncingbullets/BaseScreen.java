package com.matias.bouncingbullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class BaseScreen implements Screen {

    private Main parent;
    private Stage stage;

    public BaseScreen(Main parent) {
        this.parent = parent;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

    }

    public Main getParent() {
        return parent;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
