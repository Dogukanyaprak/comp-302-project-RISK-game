package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.ChanceCards.SabotageCard;

public class SabotageBehavior implements ChanceCardBehavior {

    @Override
    public void execute(Territory ter) {
        SabotageCard card = new SabotageCard();
        card.execute(ter);
    }

    public String getMessage() {
        String message = "Choose an opponent territory to sabotageee!!!";
        return message;
    }


}
