package src.main.java.domain.ChanceCards;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Player;

import java.util.HashMap;

public class RebellionCard {

    private String type = "Rebellion";

    public void execute(Territory ter) {

        Player currentPlayer = Game.getInstance().getCurrentPlayer();

        Territory toBeAdded = null;

        for (Territory playerTer : currentPlayer.getTerritories()) {
            if (ter.isNeighbour(playerTer)) {
                toBeAdded = playerTer;
            }
        }
        if (toBeAdded == null) {
            toBeAdded = currentPlayer.getTerritories().get(0);
        }

        int numberOfArmies = ter.getArmy().getTroop("Infantry");
        ter.deleteArmy("Infantry", numberOfArmies / 2);
        toBeAdded.addArmy("Infantry", numberOfArmies / 2);
    }

    public String getType() {
        return type;
    }
}
