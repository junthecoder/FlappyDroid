package com.gametsuku.flappydroid;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundLayer extends Group {
    private RepeatedImage skyline;

    public BackgroundLayer(float width, float height) {
        Image sky = new Image(Assets.getInstance().getAtlas().findRegion("background"));

        skyline = new RepeatedImage(Assets.getInstance().getAtlas().findRegion("skyline"));
        skyline.setSize(width, height * 2 / 3);
        skyline.setPosition(0, Constants.GROUND_HEIGHT);

        Utilities.setSizeByScale(sky, height / sky.getHeight());

        addActor(sky);
        addActor(skyline);
    }

    public RepeatedImage getSkyline() {
        return skyline;
    }
}
