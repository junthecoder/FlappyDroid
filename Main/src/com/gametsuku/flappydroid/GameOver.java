package com.gametsuku.flappydroid;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.Timer;

public class GameOver extends GameState {
    @Override
    public void onStart(final MyGame game) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.getWorld().showScoreBoard();
            }
        }, 1);

        explodeDroid(game);

        game.getScroller().stopScroll();
    }

    private void explodeDroid(MyGame game) {
        GameWorld world = game.getWorld();

        world.getDroid().setVisible(false);

        Assets.getInstance().get("sounds/explosion.wav", Sound.class).play();

        ParticleEffectPool.PooledEffect explosion = game.getEffectsManager().instantiateEffect("explosion");

        explosion.setPosition(
                world.getDroid().getX() + world.getDroid().getScaledWidth() / 2,
                world.getDroid().getY() + world.getDroid().getScaledHeight() / 2);
    }
}
