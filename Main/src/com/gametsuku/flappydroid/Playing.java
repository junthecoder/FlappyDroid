package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Timer;

public class Playing extends GameState {
    private MyGame game;
    private GameWorld world;
    private Timer.Task addPipeTask;
    private Timer.Task scoreTask;
    private CollisionProcessor collisionProcessor;
    private DroidFlames flames;

    @Override
    public void onStart(MyGame game) {
        this.game = game;
        this.world = game.getWorld();

        collisionProcessor = new CollisionProcessor(game);
        flames = new DroidFlames(game);

        world.getReadyImage().setVisible(false);

        ScoreManager.getInstance().setScore(0);
        game.getWorld().updateScoreLabel();
        world.getScoreLabel().setVisible(true);

        setupTasks();
    }

    @Override
    public void onEnd() {
        addPipeTask.cancel();
        scoreTask.cancel();
    }

    @Override
    public void render() {
        world.getDroid().setSpeedY(world.getDroid().getSpeedY() + Constants.GRAVITY_ACCEL * Gdx.graphics.getDeltaTime());
        world.getPipesLayer().movePipes();
        collisionProcessor.processCollisions();
        flames.updateFlamePositions();
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        world.getDroid().setAccelerartionY(0);
        world.getDroid().setSpeedY(Constants.DROID_UP_SPEED);

        Assets.getInstance().get("sounds/flame.wav", Sound.class).play();

        flames.setupFlames();

        return true;
    }

    private void setupTasks() {
        addPipeTask = new Timer.Task() {
            @Override
            public void run() {
                world.getPipesLayer().addPipePair(game);
            }
        };

        scoreTask = new Timer.Task() {
            @Override
            public void run() {
                game.addScore();
            }
        };

        schduleTasks();
    }

    private void schduleTasks() {
        float delay = 2;

        Timer.schedule(addPipeTask, delay, Constants.PIPE_ADDING_INTERVAL);

        float scoreInterval = Constants.PIPE_SPACE_H / Constants.SPEED;
        Timer.schedule(scoreTask,
                delay + Constants.PIPE_WIDTH / 2 / Constants.SPEED + scoreInterval,
                scoreInterval);
    }
}
