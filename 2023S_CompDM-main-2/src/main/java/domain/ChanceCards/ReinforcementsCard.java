package src.main.java.domain.ChanceCards;

import src.main.java.domain.Dice;

import src.main.java.domain.Board.Territory;

public class ReinforcementsCard {

    private String type = "Reinforcements";
    private Dice dice = Dice.getInstance();
    
    public void execute(Territory t) {
    	dice.roll();
    	
        int diceValue = dice.getValue();
        
        if (diceValue < 6) {
        	t.addArmy("Infantry", 1);
        }else {
        	t.addArmy("Infantry", 2);
        }
        


    }
    public String getType() {
        return type;
    }
}
