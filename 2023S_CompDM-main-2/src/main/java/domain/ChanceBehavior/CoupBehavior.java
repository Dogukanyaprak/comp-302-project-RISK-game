package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.ChanceCards.CoupCard;
import src.main.java.domain.ChanceCards.SabotageCard;

public class CoupBehavior implements ChanceCardBehavior{

    private String message = "Choose an opponent territory to coup!!!";
    @Override
    public void execute(Territory ter) {
        CoupCard card = new CoupCard();
        card.execute(ter);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
