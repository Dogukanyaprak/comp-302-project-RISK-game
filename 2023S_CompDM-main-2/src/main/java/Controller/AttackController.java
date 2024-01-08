package src.main.java.Controller;

import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Phase.AttackPhase;
import src.main.java.domain.Player;
import src.main.java.ui.GameFrame;

import java.util.ArrayList;

public class AttackController {

    private static final Game game = Game.getInstance();
    private static Territory attacker;
    private static Territory defender;
    private static String armyType = "Infantry";

    public static String getArmyType() {
        return armyType;
    }

    public static void setArmyType(String armyType) {
        AttackController.armyType = armyType;
    }

    public static void attack() {

        GameFrame gameFrame = GameFrame.getInstance();
        //TODO: Move this to attackPhase
        boolean isValid = AttackPhase.checkCondition(attacker, defender, game.getCurrentPlayer(), armyType);
        System.out.println("From attack controller-attack isValid =  " + isValid);

        if (isValid) {
            AttackPhase.rollDices();
            gameFrame.animateDice(AttackPhase.getAttackerVal(), AttackPhase.getDefenderVal());
        }
    }

    public static void attackUpdate() {
    	GameFrame gameFrame = GameFrame.getInstance();
    	if(AttackPhase.getAttackerVal() > AttackPhase.getDefenderVal()) {
    		gameFrame.animateFadeOut(defender.getName(), -1);
    	}
    	else {
    		gameFrame.animateFadeOut(attacker.getName(), -2);
    	}
    }

    public static void attackFinish() {
    	GameFrame gameFrame = GameFrame.getInstance();
        AttackPhase.attack(attacker, defender, getArmyType());

        if (Game.getInstance().getAlivePlayers().size() == 1) {
            GameController.endGame(Game.getInstance().getCurrentPlayer().getName());
        }

        gameFrame.updateTerritoryStatus(attacker.getName());
        gameFrame.updateTerritoryStatus(defender.getName());
        gameFrame.refresh();
    }

    public static void setAttacker(String attackerID) {

        attacker = game.getMap().getTerritory(attackerID);
        System.out.println("Attacker setted = " + attacker.getName());

    }

    public static void setDefender(String defenderID) {
        defender = game.getMap().getTerritory(defenderID);
        System.out.println("Defender setted = " + defender.getName());
    }


    public static ArrayList<String> getAttackable() {
        ArrayList<String> attackable = new ArrayList<>();
        for (Territory t : attacker.getNeighbours()) {
            if (t.getOwner() != attacker.getOwner() && t.isVisible()) {
                attackable.add(t.getName());
            }
        }
        return attackable;
    }




}
