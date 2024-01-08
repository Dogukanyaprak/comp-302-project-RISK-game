package src.main.java.domain.Phase;

import src.main.java.domain.Board.Territory;

public class FortifyPhase {

    public static void fortify(Territory from, Territory to, String type, int amount) {
        from.deleteArmy(type, amount);
        to.addArmy(type, amount);
    }

}
