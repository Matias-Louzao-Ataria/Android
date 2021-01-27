package com.matias.bouncingbullets;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class ProcesadorInput extends InputAdapter {

    private MainGameScreen parent;

    public ProcesadorInput(MainGameScreen parent) {
        this.parent = parent;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.parent.getJugador().getBody().setLinearVelocity(new Vector2(0,10));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.parent.getJugador().getBody().setLinearVelocity(new Vector2(0,0));
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
