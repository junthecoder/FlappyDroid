package com.gametsuku.flappydroid;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ArrayMap;

public class DebugRenderer {
    private ArrayMap<String, Rectangle> rects = new ArrayMap<String, Rectangle>();

    public void putRect(String name, Rectangle rect) {
        rects.put(name, new Rectangle(rect));
    }

    public void render(Stage stage) {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setProjectionMatrix(stage.getCamera().combined);

            for (Rectangle rect : rects.values()) {
                shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
            }

            shapeRenderer.end();
        }
    }
}
