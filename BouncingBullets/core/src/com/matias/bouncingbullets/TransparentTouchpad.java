package com.matias.bouncingbullets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

public class TransparentTouchpad extends Touchpad {

    public TransparentTouchpad(float deadzoneRadius, Skin skin) {
        super(deadzoneRadius, skin);
    }

    public TransparentTouchpad(float deadzoneRadius, Skin skin, String styleName) {
        super(deadzoneRadius, skin, styleName);
    }

    public TransparentTouchpad(float deadzoneRadius, TouchpadStyle style) {
        super(deadzoneRadius, style);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, 0);
    }
}
