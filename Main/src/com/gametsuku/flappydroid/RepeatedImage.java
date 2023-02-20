package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class RepeatedImage extends Actor {

    private TextureRegion region;
    private float scrollX;

    public RepeatedImage(TextureRegion region) {
        this.region = region;
    }

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        float aspectRatio = getHeight() / region.getRegionHeight();
        float tileWidth = region.getRegionWidth() * aspectRatio;
        int n = (int)(getWidth() / tileWidth) + 2;
        float x = 0;

        for (int i = 0; i < n; i++) {
            batch.draw(region,
                    getX() + (scrollX % tileWidth) + x, getY(),
                    tileWidth, getHeight());
            x += tileWidth;
        }
    }

    public void scrollX(float dx) {
        scrollX += dx;
    }
}
