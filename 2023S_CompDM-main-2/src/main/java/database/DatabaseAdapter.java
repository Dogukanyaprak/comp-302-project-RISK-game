package src.main.java.database;

public interface DatabaseAdapter {
    public boolean saveGame(GameState state);

    public GameState loadGame();
}
