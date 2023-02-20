package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class EffectsManager {
    private ArrayMap<String, ParticleEffectPool> effectPools = new ArrayMap<String, ParticleEffectPool>();
    private Array<ParticleEffectPool.PooledEffect> effects = new Array<ParticleEffectPool.PooledEffect>();

    public void render(SpriteBatch spriteBatch) {
        for (int i = effects.size - 1; i >= 0; i--) {
            ParticleEffectPool.PooledEffect effect = effects.get(i);
            effect.draw(spriteBatch, Gdx.graphics.getDeltaTime());
            if (effect.isComplete()) {
                effect.free();
                effects.removeIndex(i);
            }
        }
    }

    public void addEffectPool(String name, ParticleEffect effect) {
        effectPools.put(name, new ParticleEffectPool(effect, 1, 1));
    }

    public ParticleEffectPool.PooledEffect instantiateEffect(String name) {
        ParticleEffectPool.PooledEffect effect = effectPools.get(name).obtain();
        effects.add(effect);
        effect.start();

        return effect;
    }
}
