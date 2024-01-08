package src.main.java.Controller;

import src.main.java.database.DatabaseAdapter;
import src.main.java.database.GameState;
import src.main.java.database.MongoAdapter;
import src.main.java.database.TextFileAdapter;
import src.main.java.domain.Game;
import src.main.java.ui.BuildingGameFrame;
import src.main.java.ui.EndGameFrame;
import src.main.java.ui.GameFrame;

public class GameController {

    private GameController() {
    }

    public static void startBuild() {
        BuildingGameFrame buildingGameFrame = new BuildingGameFrame();
        BuildingFrameController.setBuildingGameFrame(buildingGameFrame);
    }


    public static void startGame() {

        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.refresh();
        gameFrame.setVisible(true);

    }

    public static void endGame(String winner) {
        GameFrame gameFrame = GameFrame.getInstance();
        gameFrame.setVisible(false);
        EndGameFrame endGameFrame = new EndGameFrame(winner);
        endGameFrame.setVisible(true);

    }

    public static void saveQuit(String type) {
        GameState state = null;
        Game game = Game.getInstance();
        if (type.equals("TextFile")) {
            state = new GameState(new TextFileAdapter(), game);
        } else if (type.equals("Mongo")) {
            state = new GameState(new MongoAdapter(), game);
        }

        state.saveGame();
    }

    public static void loadPrevGame(String str) {
        DatabaseAdapter adapter = new TextFileAdapter();
        GameState prevGame;
        if (str.equals("TextFile")) {
            adapter = new TextFileAdapter();
        } else if (str.equals("Mongo")) {
            adapter = new MongoAdapter();
        }
        prevGame = adapter.loadGame();

        System.out.println("Is this working- GameController");

        try {
            System.out.println("before loading game and frame- GameController");
            Game loadThisGame = prevGame.getGame();

            Game.setInstance(loadThisGame);

            startGame();


            System.out.println("after loading game and frame- GameController");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
