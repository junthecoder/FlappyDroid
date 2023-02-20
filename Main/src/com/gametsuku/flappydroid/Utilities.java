package com.gametsuku.flappydroid;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Utilities {
    private Utilities() {
    }

    public static void setSizeByScale(Actor actor, float scale) {
        actor.setSize(actor.getWidth() * scale, actor.getHeight() * scale);
    }
}
