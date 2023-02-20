package com.gametsuku.flappydroid;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameRenderer {
    private MyGame game;
    private GameWorld world;
    private DebugRenderer debugRenderer = new DebugRenderer();

    public GameRenderer(MyGame game) {
        this.game = game;
        this.world = game.getWorld();

        world.getButtonsLayer().debug();
    }

    public void render() {
        world.act();
        game.getScroller().scroll();

        game.getState().render();
        world.draw();

        drawEffects();
        drawDebug();
    }

    private void drawDebug() {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            Table.drawDebug(world);

            debugRenderer.putRect("droid", world.getDroid().getRect());
            debugRenderer.render(world);
        }
    }

    private void drawEffects() {
        SpriteBatch spriteBatch = world.getSpriteBatch();
        spriteBatch.begin();
        game.getEffectsManager().render(spriteBatch);
        spriteBatch.end();
    }
}
