package com.matias.bouncingbullets;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

 public class Main extends Game {

    public static PlatformSpecific platformSpecific;

     public Main(PlatformSpecific platformSpecific) {
        Main.platformSpecific = platformSpecific;
     }

     @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
    }
}
