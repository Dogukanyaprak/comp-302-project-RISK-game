package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.ChanceCards.RebellionCard;

public class RebellionBehavior implements ChanceCardBehavior{
    private final String message = "Choose an opponent territory to rebellll!!!";
    @Override
    public void execute(Territory ter) {

        RebellionCard card = new RebellionCard();
        card.execute(ter);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
