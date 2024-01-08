package src.main.java.Controller;

import src.main.java.domain.Game;
import src.main.java.ui.BuildingGameFrame;

import java.util.ArrayList;


public class BuildingFrameController {

    static Game game = Game.getInstance();
    static BuildingGameFrame buildingGameFrame;

    private BuildingFrameController() {
    }


    public static void setBuildingGameFrame(BuildingGameFrame buildingGameFrame) {
        BuildingFrameController.buildingGameFrame = buildingGameFrame;
    }

    public static void updateNumberOfPlayers(int numberOfPlayers) {
        buildingGameFrame.updatePlayersField(numberOfPlayers);
    }

    public static void updatePlayerNames(ArrayList<String> playerNames) {
        game.setPlayers(playerNames);
        game.initialize();
    }


}
