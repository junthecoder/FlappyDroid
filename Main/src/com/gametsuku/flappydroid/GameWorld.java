package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class GameWorld extends Stage {
    private ButtonsLayer buttonsLayer;
    private PipesLayer pipesLayer;
    private BackgroundLayer backgroundLayer;
    private Label attributionLabel;
    private Image readyImage;
    private Droid droid;
    private Label scoreLabel;
    private RepeatedImage ground;

    public GameWorld(MyGame game) {
        buildStage(game);
    }

    private void buildStage(MyGame myGame) {
        Gdx.input.setInputProcessor(this);
        setViewport(160, 240, true);
        setupButtonSound();
        setupLayers(myGame);
        setupAttributionLabel();
        setupReadyImage();
    }

    private void setupLayers(MyGame myGame) {
        Stack stack = new Stack();
        addActor(stack);
        stack.setFillParent(true);

        backgroundLayer = new BackgroundLayer(getWidth(), getHeight());
        pipesLayer = new PipesLayer();
        Group guiLayer = buildGUILayer();
        Group groundLayer = buildGroundLayer();
        Group droidLayer = buildDroidLayer();
        buttonsLayer = new ButtonsLayer(myGame);

        stack.add(getBackgroundLayer());
        stack.add(getPipesLayer());
        stack.add(groundLayer);
        stack.add(droidLayer);
        stack.add(guiLayer);
        stack.add(getButtonsLayer());
    }

    private void setupButtonSound() {
        addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (actor instanceof Button) {
                    Assets.getInstance().get("sounds/button.wav", Sound.class).play();
                }
            }
        });
    }

    private Group buildDroidLayer() {
        Group droidLayer = new Group();

        droid = new Droid();
        droidLayer.addActor(droid);

        droid.setScale(Constants.DROID_HEIGHT / getDroid().getHeight());
        resetDroid();

        return droidLayer;
    }

    private Group buildGroundLayer() {
        Group groundLayer = new Group();

        ground = new RepeatedImage(Assets.getInstance().getAtlas().findRegion("ground"));
        ground.setSize(getWidth(), Constants.GROUND_HEIGHT);
        groundLayer.addActor(ground);

        return groundLayer;
    }

    private Group buildGUILayer() {
        Group layer = new Group();

        scoreLabel = new Label("", new Label.LabelStyle(Assets.getFont(), Color.WHITE));
        layer.addActor(scoreLabel);

        return layer;
    }

    private void setupAttributionLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(Assets.getFont(), Color.WHITE);
        attributionLabel = new Label("The Android robot by Google\n is licensed under a CC BY 3.0.", labelStyle);
        attributionLabel.setFontScale(0.4f);
        addActor(attributionLabel);
    }

    private void setupReadyImage() {
        readyImage = new Image(Assets.getInstance().getAtlas().findRegion("ready"));
        addActor(readyImage);
        readyImage.setScale(2f);
        readyImage.setPosition(14, getHeight() - 75);
        readyImage.setVisible(false);
    }

    public void showScoreBoard() {
        getScoreLabel().setVisible(false);
        getButtonsLayer().setVisible(true);

        ScoreManager.getInstance().newScore(ScoreManager.getInstance().getScore());

        getButtonsLayer().getScoreBoard().setVisible(true);
        getButtonsLayer().getScoreBoard().setScore(ScoreManager.getInstance().getScore());
        getButtonsLayer().getScoreBoard().setHighScore(ScoreManager.getInstance().getHighScore());
    }

    public void resetDroid() {
        getDroid().setVisible(true);
        getDroid().setSpeedY(0);
        getDroid().setAccelerartionY(0);
        getDroid().setPosition(
                getWidth() * 1 / 3 - getDroid().getScaledWidth() / 2,
                Constants.GROUND_HEIGHT + (getHeight() - Constants.GROUND_HEIGHT) / 2 - getDroid().getScaledHeight() / 2);
    }

    public PipesLayer getPipesLayer() {
        return pipesLayer;
    }

    public ButtonsLayer getButtonsLayer() {
        return buttonsLayer;
    }

    public BackgroundLayer getBackgroundLayer() {
        return backgroundLayer;
    }

    public Label getAttributionLabel() {
        return attributionLabel;
    }

    public Image getReadyImage() {
        return readyImage;
    }

    public Droid getDroid() {
        return droid;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public RepeatedImage getGround() {
        return ground;
    }

    public void updateScoreLabel() {
        getScoreLabel().setText("" + ScoreManager.getInstance().getScore());
        getScoreLabel().setPosition(
                getWidth() / 2 - getScoreLabel().getPrefWidth() / 2,
                getHeight() * 4 / 5);
    }
}

