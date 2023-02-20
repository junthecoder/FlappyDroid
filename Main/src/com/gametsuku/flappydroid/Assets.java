package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
    private static Assets instance;

    private AssetManager manager;
    private TextureAtlas atlas;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }

        return instance;
    }

    public void load() {
        manager = new AssetManager();

        manager.load("images/images.pack", TextureAtlas.class);

        for (FileHandle entry: Gdx.files.internal("particles").list(".p")) {
            if (!entry.isDirectory()) {
                manager.load(entry.path(), ParticleEffect.class);
            }
        }

        for (FileHandle entry: Gdx.files.internal("sounds").list(".wav")) {
            if (!entry.isDirectory()) {
                manager.load(entry.path(), Sound.class);
            }
        }

        manager.finishLoading();

        atlas = manager.get("images/images.pack");
    }

    public void dispose() {
        manager.dispose();
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public <T> T get(String fileName) {
        return manager.get(fileName);
    }

    public <T> T get(String fileName, Class<T> cls) {
        return manager.get(fileName, cls);
    }

    public static BitmapFont getFont() {
        return new BitmapFont(Gdx.files.internal("segoe_black.fnt"));
    }

}
