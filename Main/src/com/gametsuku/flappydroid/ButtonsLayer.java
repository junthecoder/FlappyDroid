package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class ButtonsLayer extends Table {
    private MyGame game;
    private TextButton.TextButtonStyle buttonStyle;
    private ScoreBoard scoreBoard;

    public ButtonsLayer(MyGame game) {
        this.game = game;
        setupTable();
    }

    private void setupTable() {
        setupButtonStyle();
        setupScoreBoard();

        TextButton playButton = createPlayButton();
        TextButton rankingButton = createRankingButton();

        this.pad(30, 2.5f, 25, 2.5f);
        this.defaults().space(2.5f);

        this.add(scoreBoard).pad(10).expand().fill().colspan(2);
        this.row().pad(6).height(30);
        this.add(playButton).expand().fill();
        this.add(rankingButton).expand().fill();
    }

    private void setupScoreBoard() {
        scoreBoard = new ScoreBoard(Assets.getFont());
        scoreBoard.setVisible(false);
    }

    private void setupButtonStyle() {
        NinePatchDrawable buttonDrawable = new NinePatchDrawable(Assets.getInstance().getAtlas().createPatch("button"));
        NinePatchDrawable buttonDownDrawable = new NinePatchDrawable(Assets.getInstance().getAtlas().createPatch("button_down"));

        buttonStyle = new TextButton.TextButtonStyle(buttonDrawable, buttonDownDrawable, buttonDrawable, Assets.getFont());
        buttonStyle.fontColor = Color.BLACK;
    }

    private TextButton createRankingButton() {
        TextButton rankingButton = new TextButton("RANK", buttonStyle);
        rankingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.onRankingButtonPressed();
            }
        });

        return rankingButton;
    }

    private TextButton createPlayButton() {
        TextButton playButton = new TextButton("PLAY", buttonStyle);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.changeState(new Ready());
            }
        });

        return playButton;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }
}
