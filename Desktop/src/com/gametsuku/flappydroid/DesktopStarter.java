package com.gametsuku.flappydroid;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

import java.io.File;

public class DesktopStarter {
    private static boolean rebuildAtlas = true;
    private static boolean drawDebugOutline = false;

    public static void main(String[] args) {
        String rawImageDir = "../assets-raw/images";
        File file = new File(rawImageDir);
        System.out.println(file.lastModified());

        if (rebuildAtlas) {
            TexturePacker2.Settings settings = new TexturePacker2.Settings();
            settings.maxWidth = settings.maxHeight = 1024;
            settings.debug = drawDebugOutline;
            TexturePacker2.process(settings, "../assets-raw/images", "images", "images.pack");
        }

        DesktopActionResolver actionResolver = new DesktopActionResolver();

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Flappy Droid";
        cfg.useGL20 = true;
        cfg.width = 320;
        cfg.height = 600;
        new LwjglApplication(new MyGame(actionResolver), cfg);
    }
}
