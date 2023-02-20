package com.gametsuku.flappydroid;

public class StateMachine {
    private MyGame game;
    private GameState state = new GameState();

    public StateMachine(MyGame game) {
        this.game = game;
    }

    public void changeState(GameState newState) {
        if (state != null) {
            state.onEnd();
        }
        state = newState;
        state.onStart(game);
    }

    public GameState getState() {
        return state;
    }
}
