package src.main.java.domain.ChanceCards;

import src.main.java.Controller.ChanceCardController;
import src.main.java.domain.Game;
import src.main.java.domain.Player;
import src.main.java.domain.Board.Territory;

public class CoupCard{

    private String type = "Coup";


    public void execute(Territory t) {
        Player currentPlayer = Game.getInstance().getCurrentPlayer();
        System.out.println("Coop card execute" + t.getName());
        t.setOwner(currentPlayer);
        t.deleteArmy("Infantry", t.getArmy().getTroop("Infantry") - 1);
        t.deleteArmy("Cavalry", t.getArmy().getTroop("Cavalry"));
        t.deleteArmy("Artillery", t.getArmy().getTroop("Artillery"));

    }

    public String getType() {
        return type;
    }
}
