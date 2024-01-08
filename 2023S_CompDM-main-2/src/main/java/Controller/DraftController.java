package src.main.java.Controller;

import src.main.java.domain.Board.Map;
import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Phase.DraftPhase;
import src.main.java.domain.Player;
import src.main.java.ui.GameFrame;

public class DraftController {


    public static void start() {
        Game game = Game.getInstance();
        GameFrame gameFrame = GameFrame.getInstance();
        DraftPhase.giveArmyToPlayer(game.getCurrentPlayer());
        gameFrame.updateTurnText(game.getCurrentPlayer());
    }

    public static void placeSingleArmy(String terName, String type) {
        Game game = Game.getInstance();

        Territory ter = Map.getMap().getTerritory(terName);
        Player currPlayer = game.getCurrentPlayer();
        if (ter.getOwner() == currPlayer) {
            try {
                currPlayer.getArmy().deleteArmy(type, 1);
                ter.addArmy(type, 1);
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }

        }


    }

}
