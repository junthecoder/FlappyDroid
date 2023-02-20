package com.gametsuku.flappydroid;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Droid extends Group {
    private Image arm;
    private float accelerartionY;
    private float speedY;
    private Rectangle rect = new Rectangle();

    public Droid() {
        // legs
        Image legR = createLimb();
        Image legL = createLimb();

        legR.setPosition(6, 0);
        legL.setPosition(9, 0);

        legR.setRotation(-40);
        legL.setRotation(-40);

        // body
        Image body = new Image(Assets.getInstance().getAtlas().findRegion("body"));
        addActor(body);
        body.setPosition(4, 4);

        // arm
        arm = createLimb();

        arm.setPosition(4, 6);
        arm.setRotation(90);

        setSize(14, 18);
    }

    private Image createLimb() {
        TextureAtlas.AtlasRegion limbTexture = Assets.getInstance().getAtlas().findRegion("limb");
        Image limb = new Image(limbTexture);
        addActor(limb);

        limb.setOrigin(1, 4);

        return limb;
    }

    public float getScaledWidth() {
        return getWidth() * getScaleX();
    }

    public float getScaledHeight() {
        return getHeight() * getScaleY();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        speedY += accelerartionY * delta;
        translate(0, speedY * delta);

        rect.set(getX(), getY(), getScaledWidth(), getScaledHeight());

        float angle = MathUtils.clamp(speedY * 1.46f, -150, -30);
        arm.setRotation(angle);
    }

    public void setAccelerartionY(float accelerartionY) {
        this.accelerartionY = accelerartionY;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public Rectangle getRect() {
        return rect;
    }
}
