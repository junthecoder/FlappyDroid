package com.gametsuku.flappydroid;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MyGame extends Game  {
    private GameWorld world;
    private StateMachine stateMachine;
    private EffectsManager effectsManager;
    private ActionResolver actionResolver;
    private ImageScroller scroller;
    private GameRenderer renderer;

    public MyGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    @Override
    public void create() {
        Assets.getInstance().load();

        world = new GameWorld(this);
        renderer = new GameRenderer(this);
        stateMachine = new StateMachine(this);

        loadEffects();
        setupInputListener();

        ScoreManager.init(actionResolver);
        ScoreManager.getInstance().syncHighScoreFromGooglePlay();

        scroller = new ImageScroller(world);
        scroller.startScroll();
    }

    public void onRankingButtonPressed() {
        ScoreManager.getInstance().syncHighScore();
        actionResolver.showLeaderboard();
    }

    private void setupInputListener() {
        world.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return getState().touchDown(event, x, y, pointer, button);
            }
        });
    }

    private void loadEffects() {
        effectsManager = new EffectsManager();

        ParticleEffect explosionEffect = Assets.getInstance().get("particles/explosion.p");
        effectsManager.addEffectPool("explosion", explosionEffect);

        ParticleEffect flameEffect = Assets.getInstance().get("particles/flame.p");
        effectsManager.addEffectPool("flame", flameEffect);
    }

    public void addScore() {
        Assets.getInstance().get("sounds/coin.wav", Sound.class).play(0.55f);

        ScoreManager.getInstance().addScore();
        world.updateScoreLabel();
    }

    public void changeState(GameState newState) {
        stateMachine.changeState(newState);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void resume() {
        // Reload assets. You can use assetManager.update() loop instead.
        Assets.getInstance().load();
    }

    @Override
    public void dispose() {
        world.dispose();
        Assets.getInstance().dispose();
    }

    public GameWorld getWorld() {
        return world;
    }

    public EffectsManager getEffectsManager() {
        return effectsManager;
    }

    public ImageScroller getScroller() {
        return scroller;
    }

    public GameState getState() {
        return stateMachine.getState();
    }
}
