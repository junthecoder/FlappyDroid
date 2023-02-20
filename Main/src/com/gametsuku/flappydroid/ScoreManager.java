package com.gametsuku.flappydroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class ScoreManager {
    private static final String HIGHSCORE_KEY = "highScore";
    private static ScoreManager instance;
    private long score;
    private long highScore;
    private Preferences prefs;
    private ActionResolver actionResolver;

    private ScoreManager(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
        prefs = Gdx.app.getPreferences("preferences");
    }

    public static void init(ActionResolver actionResolver) {
        instance = new ScoreManager(actionResolver);
    }

    public static ScoreManager getInstance() {
        return instance;
    }

    public void addScore() {
        score++;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getScore() {
        return score;
    }

    public void syncHighScore() {
        long highScorePref = prefs.getLong(HIGHSCORE_KEY, 0);
        long highScorePlay = actionResolver.getScore();

        if (highScorePref > highScorePlay) {
            actionResolver.submitScore(highScorePref);
        } else if (highScorePlay > highScorePref) {
            prefs.putLong(HIGHSCORE_KEY, highScorePlay);
            prefs.flush();
        }
    }

    public void syncHighScoreFromGooglePlay() {
        // Update high score from Google Play Service.
        long highScorePlay = actionResolver.getScore();
        long highScorePref = prefs.getLong(HIGHSCORE_KEY);
        highScore = Math.max(highScorePlay, highScorePref);

        if (highScorePlay > highScorePref) {
            prefs.putLong(HIGHSCORE_KEY, highScorePlay);
            prefs.flush();
        } // Don't submit score to Google Play without asking.
    }

    public void newScore(long score) {
        highScore = prefs.getLong(HIGHSCORE_KEY, 0);
        if (score > highScore) {
            highScore = score;
            prefs.putLong(HIGHSCORE_KEY, highScore);
            prefs.flush();
        }
    }

    public long getHighScore() {
        return highScore;
    }
}
