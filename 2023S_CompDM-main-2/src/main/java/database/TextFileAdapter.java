package src.main.java.database;


import java.io.*;

public class TextFileAdapter implements DatabaseAdapter, Serializable {
    private TextFileAdp textadap;

    public TextFileAdapter() {
        textadap = new TextFileAdp();
    }
    @Override
    public boolean saveGame(GameState gameState) {
        textadap.saveToSerFile(gameState);
        if (textadap.getSuccess() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public GameState loadGame() {
        GameState stat=  textadap.loadFromSerFile();
        return stat;
    }
}
