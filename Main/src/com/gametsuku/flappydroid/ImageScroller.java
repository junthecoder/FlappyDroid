package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;

public class ImageScroller {
    private GameWorld world;
    private boolean isScrolling;

    public ImageScroller(GameWorld world) {
        this.world = world;
    }

    public void startScroll() {
        isScrolling = true;
    }

    public void stopScroll() {
        isScrolling = false;
    }

    public void scroll() {
        if (isScrolling) {
            world.getBackgroundLayer().getSkyline().scrollX(-Gdx.graphics.getDeltaTime() * 20);
            world.getGround().scrollX(-Gdx.graphics.getDeltaTime() * Constants.SPEED);
        }
    }
}
