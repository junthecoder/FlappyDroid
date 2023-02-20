package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class ScoreBoard extends Group {
    private Label scoreLabel;
    private Label highScoreLabel;
    private Image medal;

    public ScoreBoard(BitmapFont font) {
        Label.LabelStyle style = new Label.LabelStyle(font, Color.BLACK);

        Stack stack = new Stack();
        stack.setFillParent(true);
        addActor(stack);

        NinePatch patch = Assets.getInstance().getAtlas().createPatch("button");
        Image board = new Image(new NinePatchDrawable(patch));
        stack.add(board);

        Table table = new Table();
        table.debug();
        stack.add(table);

        scoreLabel = new Label("", style);
        highScoreLabel = new Label("", style);

        table.defaults().right().padRight(10);
        table.add(scoreLabel).expand();
        table.row();
        table.add(highScoreLabel).expand();
    }

    public void setScore(long score) {
        scoreLabel.setText("SCORE " + score);

        String medalName = null;
        if (score >= 100) {
            medalName = "gold";
        } else if (score >= 50) {
            medalName = "silver";
        } else if (score >= 20) {
            medalName = "bronze";
        }

        if (medal != null) {
            medal.remove();
        }

        if (medalName == null) {
            medal = null;
        } else {
            medal = new Image(Assets.getInstance().getAtlas().findRegion("medal_" + medalName));
            medal.setPosition(22, getHeight() / 2 - medal.getHeight() / 2);
            addActor(medal);
        }
    }

    public void setHighScore(long score) {
        highScoreLabel.setText("BEST  " + score);
    }
}
