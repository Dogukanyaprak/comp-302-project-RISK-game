package src.main.java.Controller;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Phase.FortifyPhase;
import src.main.java.domain.Player;


public class FortifyController {

    static final Game game = Game.getInstance();
    private static Territory fromTerr;
    private static Territory toTerr;
    private static int numTroop = 1;

    private static String armyType = "Infantry";

    public static void fortify() {

        if (check(toTerr, fromTerr, numTroop, game.getCurrentPlayer(), armyType)) {
            FortifyPhase.fortify(fromTerr, toTerr, armyType, numTroop);
            /*
            gameFrame.updateTerritoryStatus(toTerr.getName());
            gameFrame.updateTerritoryStatus(fromTerr.getName());
            gameFrame.refresh();
            */
        }
    }


    public static boolean check(Territory to, Territory from, int num, Player player, String type) throws IllegalArgumentException {
        if (!player.equals(to.getOwner())) {
            //System.out.println("fortify invalid from end territory");
            throw new IllegalArgumentException("fortify invalid from end territory");
            //return false;
        }

        if (!player.equals(from.getOwner())) {
            //System.out.println("fortify invalid, from start territory");
            throw new IllegalArgumentException("fortify invalid from start territory");
            //return false;
        }

        if (num <= 0) {
            //System.out.println("fortify invalid number");
            throw new IllegalArgumentException("fortify invalid number");
            //return false;
        }

        if (from.getArmy().getTroop(type) < num + 1) {
            //System.out.println("fortify invalid not enough");
            throw new IllegalArgumentException("fortify invalid not enough");
            //return false;
        }


        return true;
    }


    public static void setFromTerr(String fromTerrID) {
        fromTerr = game.getMap().getTerritory(fromTerrID);
        System.out.println("Source teritory setted = " + fromTerr.getName());
    }


    public static void setToTerr(String toTerrID) {
        toTerr = game.getMap().getTerritory(toTerrID);
        System.out.println("Target territory setted = " + toTerr.getName());
    }


    public static void setNumTroop(int numTroop) {
        FortifyController.numTroop = numTroop;
    }


    public static void setArmyType(String armyType) {
        FortifyController.armyType = armyType;
    }

}
