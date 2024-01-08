package src.main.java.database;

import src.main.java.domain.Game;

import java.io.Serializable;

public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;
    private static DatabaseAdapter adapter;
    private Game game;

    public GameState(DatabaseAdapter adapter, Game game) {
        GameState.adapter = adapter;
        this.game = game;
    }

    public static Game loadGame() {
        GameState lastGameState = adapter.loadGame();
        System.out.println("This should be called");
        return lastGameState.getGame();
    }

    public void saveGame() {
        adapter.saveGame(this);
        System.out.println("Is this part working- gamestate");
    }

    public Game getGame() {
        return game;
    }

}
