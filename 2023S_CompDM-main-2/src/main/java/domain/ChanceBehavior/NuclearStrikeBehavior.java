package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.ChanceCards.NuclearStrikeCard;
import src.main.java.domain.ChanceCards.ReinforcementsCard;

public class NuclearStrikeBehavior implements ChanceCardBehavior{
	
	private final String message = "Choose an opponent territory to wipe out its armies, but it will have a surprise for you";

    @Override
    public void execute(Territory ter) {

        NuclearStrikeCard card = new NuclearStrikeCard();
        card.execute(ter);

    }

    public String getMessage() {
        return message;
    }

}
