package src.main.java.domain.ChanceBehavior;

import src.main.java.Controller.ChanceCardController;
import src.main.java.domain.Board.Territory;

public class CardContext {

    private final ChanceCardBehavior behavior;
    private final ChanceCardController controller = ChanceCardController.getInstance();

    public CardContext(ChanceCardBehavior behavior) {
        this.behavior = behavior;
    }

    public void execute(Territory ter) {
        behavior.execute(ter);
        controller.refreshUI();
    }
}
