package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.ChanceCards.ReinforcementsCard;

public class ReinforcementBehavior implements ChanceCardBehavior {
    private final String message = "Choose a territory to reinforce!!!";

    @Override
    public void execute(Territory ter) {

        ReinforcementsCard card = new ReinforcementsCard();
        card.execute(ter);

    }

    public String getMessage() {
        return message;
    }
}
