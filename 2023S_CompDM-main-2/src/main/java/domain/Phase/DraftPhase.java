package src.main.java.domain.Phase;

import src.main.java.domain.Player;

public class DraftPhase {

    public static void giveArmyToPlayer(Player player) {

        int tot = player.getTerritories().size();

        tot = Math.max(tot / 3, 3);

        player.getArmy().addArmy("Infantry", tot);
    }

}
