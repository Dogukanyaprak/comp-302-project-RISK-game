package src.main.java.Controller;


import src.main.java.domain.Board.Map;
import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Player;

public class InitializingArmyController {

    private static int coloredTer = 0;
    private static int placedArmy = 0;


    public static int ownTerritory(Territory selectedTerritory, Player currentPlayer) {

        if (null == selectedTerritory) {
            return -1;
        }

        if (!selectedTerritory.isVisible()) {
            return -2;
        }
        if (selectedTerritory.getOwner() != null) {
            return -3;
        }

        coloredTer++;
        selectedTerritory.setOwner(currentPlayer);
        selectedTerritory.addArmy("Infantry", 1);
        placedArmy++;
        currentPlayer.getArmy().deleteArmy("Infantry", 1);

        return 0;
    }

    public static int placeSingleArmy(Territory selectedTerritory, Player currentPlayer) {

        // Requires:
        // Modifies: selectedTerritory's army, placedArmy, currentPlayer's army.
        // Effects: If terr is a valid territory:
        //            - If the selected territory is not visible, returns -2.
        //            - If the currentPlayer is not the owner of the selected territory, returns -3.
        //            - Otherwise, adds an infantry army to the selected territory, increments placedArmy by 1,
        //              and decreases the currentPlayer's infantry army count by 1. Returns 0.
        //          If terr is not a valid territory, returns -1.

        if (null == selectedTerritory) {
            return -1;
        }

        if (!selectedTerritory.isVisible()) {
            return -2;
        }

        if (currentPlayer != selectedTerritory.getOwner()) {
            return -3;
        }

        selectedTerritory.addArmy("Infantry", 1);
        placedArmy++;
        currentPlayer.getArmy().deleteArmy("Infantry", 1);

        return 0;

    }

    private static int nonVis() {
        int notVis = 0;
        for (Territory tempTer : Game.getInstance().getMap().getAllTerritories()) {
            if (!tempTer.isVisible()) ++notVis;
        }
        return notVis;
    }

    public static void placeArmy(String terName) {
        int retVal = -99;

        Game game = Game.getInstance();
        Territory ter = game.getMap().getTerritory(terName);
        Player currPlayer = game.getCurrentPlayer();

        if ((coloredTer + nonVis()) < Map.TER_COUNT) {
            retVal = InitializingArmyController.ownTerritory(ter, currPlayer);
        } else if (game.getTotalArmy() > placedArmy) {
            retVal = InitializingArmyController.placeSingleArmy(ter, currPlayer);
        }

        if (0 == retVal) {
            game.endPlayerTurn();
        }
    }

    public static boolean isAllArmiesPlaced() {
        return Game.getInstance().getTotalArmy() == placedArmy;
    }


}