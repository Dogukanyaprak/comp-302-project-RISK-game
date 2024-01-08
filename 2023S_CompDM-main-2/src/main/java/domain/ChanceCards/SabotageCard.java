package src.main.java.domain.ChanceCards;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Dice;

public class SabotageCard{
    private String type = "Sabotage";
    private Dice dice = Dice.getInstance();

    public void execute(Territory t) {
    	
    	dice.roll();
    	
        int diceValue = dice.getValue();
        
        if (diceValue < 6) {
        	t.deleteArmy("Infantry", 1);
        }else {
        	t.deleteArmy("Infantry", 2);
        }
       
    }

    public String getType() {
        return type;
    }

	
}
