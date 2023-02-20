package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;

public class DroidFlames {
    private MyGame game;
    private GameWorld world;
    private ParticleEffectPool.PooledEffect flame1, flame2;

    public DroidFlames(MyGame game) {
        this.game = game;
        this.world = game.getWorld();
    }

    void updateFlamePositions() {
        if (flame1 != null) {
            flame1.setPosition(world.getDroid().getX() + 4.3f, world.getDroid().getY() + 0.26f);
            flame2.setPosition(world.getDroid().getX() + 8.2f, world.getDroid().getY() + 0.26f);
        }
    }

    void setupFlames() {
        if (flame1 != null) {
            flame1.allowCompletion();
            flame2.allowCompletion();
        }

        flame1 = game.getEffectsManager().instantiateEffect("flame");
        flame2 = game.getEffectsManager().instantiateEffect("flame");
    }
}
