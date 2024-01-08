package src.main.java.domain.Phase;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Dice;
import src.main.java.domain.Game;
import src.main.java.domain.Player;

public class AttackPhase {
    private static AttackPhase phase;
    private static int attackerVal = 1;
    private static int defenderVal = 1;

    private AttackPhase() {

    }

    public static AttackPhase getPhase() {
        if (phase == null) {
            phase = new AttackPhase();
        }
        return phase;
    }

    public static void rollDices() {
        Dice dice = Dice.getInstance();
        dice.roll();
        attackerVal = dice.getValue();
        dice.roll();
        defenderVal = dice.getValue();
    }

    public static int attack(Territory from, Territory to, String type) {
        Player fromOwner = from.getOwner();
        Player toOwner = to.getOwner();
        if (attackerVal > defenderVal) {
        	if(to.getArmy().getTotalArmyPower() > 0) {
            to.deleteArmy(type, 1);
        	}
            //Occupation
            if (to.getArmy().getTotalArmyPower() == 0) {
                to.setOwner(fromOwner);
                from.deleteArmy(type, 1);
                to.addArmy(type, 1);
                fromOwner.pickRandomCard();
                //Player dead
                if (toOwner.getTerritories().size() == 0) {
                    Game.getInstance().killPlayer(toOwner);
                }
                
            }
        } else {
            from.deleteArmy(type, 2);
        }

        return -1;
    }

    public static int getAttackerVal() {
        return attackerVal;
    }

    public static int getDefenderVal() {
        return defenderVal;
    }

    public static boolean checkCondition(Territory attackTer, Territory defenderTer, Player player, String armyType) throws IllegalArgumentException {
        // requires: The attackTer, defenderTer, and player objects must not be null.
        // modifies: Does not modify any objects
        // effects: Returns true if all the conditions are satisfied; otherwise,
        // throws an IllegalArgumentException with an appropriate error message.

        if (!player.equals(attackTer.getOwner())) {
            throw new IllegalArgumentException("Player must be the current player");
        }

        if (attackTer.getArmy().getTroop(armyType) < 2) {
            throw new IllegalArgumentException("Attacker should have 2 army");
        }

        if (attackTer.getOwner().equals(defenderTer.getOwner())) {
            throw new IllegalArgumentException("Owners must be different");
        }

        if (!attackTer.isNeighbour(defenderTer)) {
            throw new IllegalArgumentException("Territories should be neighbor");
        }

        // TODO: Attacker only need more army on the attacking type
        /*
        if (attackTer.getArmy().getTotalArmyPower() < defenderTer.getArmyCapacity()) {
            throw new IllegalArgumentException("Attacker should have more army");
        }

         */

        return true;
    }

}
