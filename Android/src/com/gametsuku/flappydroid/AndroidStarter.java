package com.gametsuku.flappydroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.leaderboard.LeaderboardVariant;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.example.games.basegameutils.GameHelper;

import java.util.concurrent.TimeUnit;

public class AndroidStarter extends AndroidApplication implements ActionResolver, GameHelper.GameHelperListener
{
	private static final String LEADERBOARD_ID = "CgkImu25gLcVEAIQAQ";
	private GameHelper gameHelper;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useAccelerometer = false;
		cfg.useCompass = false;
		cfg.useWakelock = true;
		cfg.useGL20 = true;
		initialize(new MyGame(this), cfg);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.setup(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStart(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSignInFailed() {
	}

	@Override
	public void onSignInSucceeded() {
	}

	public void signIn() {
		gameHelper.beginUserInitiatedSignIn();
	}

	@Override
	public void showLeaderboard() {
		signIn();

		if (gameHelper.isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(
					gameHelper.getApiClient(), LEADERBOARD_ID), 1234);
		}
	}

	@Override
	public long getScore() {
		if (gameHelper.isSignedIn()) {
			PendingResult pendingResult = Games.Leaderboards.loadCurrentPlayerLeaderboardScore(gameHelper.getApiClient(), LEADERBOARD_ID,
					LeaderboardVariant.TIME_SPAN_ALL_TIME, LeaderboardVariant.COLLECTION_PUBLIC);

			Leaderboards.LoadPlayerScoreResult result = (Leaderboards.LoadPlayerScoreResult) pendingResult.await(3, TimeUnit.SECONDS);
			if (result.getStatus().getStatusCode() == GamesStatusCodes.STATUS_OK) {
				if (result.getScore() != null) {
					return result.getScore().getRawScore();
				}
			}
		}

		return -1;
	}

	@Override
	public void submitScore(long score) {
		signIn();

		System.out.println(gameHelper.isSignedIn());
		if (gameHelper.isSignedIn()) {
			System.out.println(gameHelper.isSignedIn());
			Games.Leaderboards.submitScore(gameHelper.getApiClient(), LEADERBOARD_ID, score);
		}
	}


}