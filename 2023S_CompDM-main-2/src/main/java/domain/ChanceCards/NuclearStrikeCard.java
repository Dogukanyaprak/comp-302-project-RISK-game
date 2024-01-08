package src.main.java.domain.ChanceCards;

import java.util.Random;

import src.main.java.domain.Game;
import src.main.java.domain.Player;
import src.main.java.domain.Board.Territory;

public class NuclearStrikeCard {
	private String type = "NuclearStrike";
	private final Random random = new Random();


    public void execute(Territory t) {
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        deleteAllArmies(t);
        Territory currentPlayerTerritory = randomTerritory(currentPlayer);
        deleteAllArmies(currentPlayerTerritory);

    }

    public String getType() {
        return type;
    }
    
    private Territory randomTerritory(Player p) {
    	
    	int randomNumber = random.nextInt(p.getTerritories().size());
    	
    	return p.getTerritories().get(randomNumber);
    }
    
    private void deleteAllArmies(Territory t) {
    	t.deleteArmy("Infantry", t.getArmy().getTroop("Infantry"));
        t.deleteArmy("Cavalry", t.getArmy().getTroop("Cavalry"));
        t.deleteArmy("Artillery", t.getArmy().getTroop("Artillery"));
    	
    }
}
