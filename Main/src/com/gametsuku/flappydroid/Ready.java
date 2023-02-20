package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.forever;

public class Ready extends GameState {
    private MyGame game;
    private Table table;
    private Action updownAction;

    @Override
    public void onStart(final MyGame game) {
        this.game = game;
        GameWorld world = game.getWorld();

        world.getAttributionLabel().setVisible(false);

        game.getScroller().startScroll();

        world.getButtonsLayer().setVisible(false);
        world.resetDroid();

        world.getReadyImage().setVisible(true);
        world.getPipesLayer().clearPipes();

        setupDroidAction();
        setupTable();
        setupInputListener();
    }

    private void setupInputListener() {
        game.getWorld().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.getWorld().removeListener(this);
                game.getWorld().getDroid().removeAction(updownAction);
                table.remove();
                game.changeState(new Playing());

                return true;
            }
        });
    }

    private void setupDroidAction() {
        updownAction = forever(Actions.sequence(
                Actions.moveBy(0, 30, 0.7f, Interpolation.sineOut),
                Actions.moveBy(0, -30, 0.7f, Interpolation.sineIn)));

        game.getWorld().getDroid().addAction(updownAction);
    }

    private void setupTable() {
        table = new Table();
        table.setFillParent(true);
        game.getWorld().addActor(table);

        TextureAtlas.AtlasRegion tapRegion = Assets.getInstance().getAtlas().findRegion("tap");
        Image tapImage1 = new Image(tapRegion);
        Image tapImage2 = new Image(tapRegion);

        table.add(tapImage1).space(20);
        table.add(tapImage2);
    }
}
