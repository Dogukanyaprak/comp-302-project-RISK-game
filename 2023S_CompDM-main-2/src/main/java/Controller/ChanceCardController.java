package src.main.java.Controller;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Phase.ChancePhase;
import src.main.java.domain.ChanceBehavior.CardContext;
import src.main.java.domain.ChanceBehavior.ChanceCardBehavior;
import src.main.java.ui.GameFrame;

public class ChanceCardController {
    private static ChanceCardController instance;
    GameFrame frame = GameFrame.getInstance();
    ChanceCardBehavior behavior;
    ChancePhase chancePhase = ChancePhase.getInstance();

    private ChanceCardController() {

    }

    public static ChanceCardController getInstance() {
        if (null == instance) {
            instance = new ChanceCardController();
        }
        return instance;
    }


    public void setBehavior(ChanceCardBehavior behavior) {
        this.behavior = behavior;
    }

    public Territory getTerritory() {
        // TODO Auto-generated method stub
        return Game.getInstance().getMap().getTerritory(frame.getLastClickedCellId());
    }

    public void refreshUI() {
        frame.refresh();
    }

    public void start() {
    	CardContext context = new CardContext(behavior);
    	context.execute(ChanceCardController.getInstance().getTerritory());
    }

    public String getMessage() {
        return behavior.getMessage();
    }

    public void pickChance() {
        setBehavior(chancePhase.pickChance());
    }


}
