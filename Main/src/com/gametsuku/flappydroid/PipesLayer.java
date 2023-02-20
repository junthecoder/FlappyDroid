package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class PipesLayer extends Group {
    private TextureAtlas.AtlasRegion pipeTexture;
    private Array<Image> pipes = new Array<Image>();

    public PipesLayer() {
        pipeTexture = Assets.getInstance().getAtlas().findRegion("pipe");
    }

    void addPipePair(MyGame myGame) {
        Image pipe1 = createPipe(false);
        Image pipe2 = createPipe(true);

        float spacePosY = MathUtils.random(
                Constants.GROUND_HEIGHT + Constants.MIN_PIPE_HEIGHT + Constants.PIPE_SPACE_V / 2,
                myGame.getWorld().getHeight() - Constants.MIN_PIPE_HEIGHT - Constants.PIPE_SPACE_V / 2);
        pipe1.setPosition(myGame.getWorld().getWidth(), spacePosY - pipe1.getHeight() - Constants.PIPE_SPACE_V / 2);
        pipe2.setPosition(myGame.getWorld().getWidth(), spacePosY + Constants.PIPE_SPACE_V / 2);
    }

    private Image createPipe(boolean rotate) {
        Image pipe = new Image(pipeTexture);

        pipes.add(pipe);
        addActor(pipe);

        Utilities.setSizeByScale(pipe, Constants.PIPE_WIDTH / pipe.getWidth());

        if (rotate) {
            pipe.setOrigin(pipe.getWidth() / 2, pipe.getHeight() / 2);
            pipe.setRotation(180);
        }

        return pipe;
    }

    public void clearPipes() {
        while (pipes.size > 0) {
            pipes.pop().remove();
        }
    }

    public Array<Image> getPipes() {
        return pipes;
    }

    public void movePipes() {
        for (int i = getPipes().size - 1; i >= 0; i--) {
            Image pipe = getPipes().get(i);

            pipe.translate(-Gdx.graphics.getDeltaTime() * Constants.SPEED, 0);
            if (pipe.getX() + pipe.getWidth() <= 0) {
                getPipes().removeIndex(i);
            }
        }
    }
}
