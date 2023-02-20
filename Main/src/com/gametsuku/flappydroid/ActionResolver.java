package com.gametsuku.flappydroid;

public interface ActionResolver {
    public void showLeaderboard();
    public void submitScore(long score);
    public long getScore();
}
