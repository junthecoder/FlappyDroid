package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;

public class DesktopActionResolver implements ActionResolver {
    private static final String TAG = "DesktopActionResolver";

    @Override
    public void showLeaderboard() {
        Gdx.app.log(TAG, "Show leaderboard");
    }

    @Override
    public void submitScore(long score) {
        Gdx.app.log(TAG, "Submit score: " + score);
    }

    @Override
    public long getScore() {
        Gdx.app.log(TAG, "Get score: always returns -1");
        return -1;
    }
}
