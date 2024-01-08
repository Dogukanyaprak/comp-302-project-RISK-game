package src.main.java.database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TextFileAdp {

    int success = 0;
    public void saveToSerFile(GameState gameState) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("game.ser"));
            oos.writeObject(gameState);
            oos.close();
            success = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getSuccess() {
        return success;
    }

    public GameState loadFromSerFile() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("game.ser"));
            System.out.println("This this is working- textfileadapter");
            GameState loadingGame = (GameState) ois.readObject();
            ois.close();
            System.out.println("This is working- textfileadapter");
            System.out.println("Players names are: ");

            return loadingGame;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
