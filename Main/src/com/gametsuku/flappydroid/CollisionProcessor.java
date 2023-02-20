package com.gametsuku.flappydroid;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CollisionProcessor {
    private MyGame game;
    private GameWorld world;
    private Rectangle pipeRect = new Rectangle();

    public CollisionProcessor(MyGame game) {
        this.game = game;
        this.world = game.getWorld();
    }

    public void processCollisions() {
        if (droidCollidesToGround()) {
            game.changeState(new GameOver());
            return;
        }

        processPipeCollisions();
    }

    private boolean droidCollidesToGround() {
        return world.getDroid().getY() < Constants.GROUND_HEIGHT;
    }

    private void processPipeCollisions() {
        for (Image pipe : world.getPipesLayer().getPipes()) {
            pipeRect.set(pipe.getX(), pipe.getY(), pipe.getWidth(), pipe.getHeight());

            if (world.getDroid().getRect().overlaps(pipeRect)) {
                game.changeState(new GameOver());
            }
        }
    }
}
