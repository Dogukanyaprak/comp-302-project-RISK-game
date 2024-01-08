package src.main.java.Controller;

import src.main.java.domain.Game;
import src.main.java.domain.Player;
import src.main.java.domain.Board.Continent;
import src.main.java.domain.Board.Territory;
import src.main.java.ui.TradeFrame;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeController {
    static Game game;
    static TradeFrame frame;
    private static ArrayList<int[]> validCombinations = new ArrayList<>();
    private static ArrayList<int[]> validResults = new ArrayList<>();

    public TradeController() {
        tradeTypeAdder();
    }


    public static void tradeTypeAdder() {
        validCombinations.add(new int[]{3, 0, 0}); // infantry, cavalry, artillery
        validCombinations.add(new int[]{2, 1, 0});
        validCombinations.add(new int[]{2, 0, 1});
        validCombinations.add(new int[]{1, 2, 0});
        validCombinations.add(new int[]{0, 2, 1});

        validResults.add(new int[]{0, 1, 0});
        validResults.add(new int[]{0, 2, 0});
        validResults.add(new int[]{0, 0, 2});
        validResults.add(new int[]{0, 1, 1});
        validResults.add(new int[]{0, 0, 3});
    }


    /**
     * Determines if the given combination of infantry, cavalry, and artillery units
     * is a valid trade combination.
     * <p>
     * Requires: infantry, cavalry, and artillery are non-negative integers.
     * Modifies: None.
     * Effects: Returns the index of the valid trade combination in the validCombinations list,
     * or -1 if the combination is invalid.
     *
     * @param infantry  the number of infantry units.
     * @param cavalry   the number of cavalry units.
     * @param artillery the number of artillery units.
     * @return the index of the valid trade combination, or -1 if the combination is invalid.
     */
    public static int isValidTrade(int infantry, int cavalry, int artillery) {
        for (int[] combination : validCombinations) {
            if (infantry == combination[0] && cavalry == combination[1] && artillery == combination[2]) {
                return validCombinations.indexOf(combination);
            }
        }
        return -1;
    }


    public static void updateArmyCards(int[] army, Player player, int v) {
        player.getArmyDeck().trade(v);
        String[] t = {"Infantry", "Cavalry", "Artillery"};
        for (int i = 0; i < 3; i++) {
            if (army[i] != 0) {
                player.getArmy().addArmy(t[i], army[i]);
            }
        }
    }


    public static void tradeArmy(Player currentPlayer, int infantry, int cavalry, int artillery) {
        int v = isValidTrade(infantry, cavalry, artillery);

        if (v != -1) {
            int[] army = validResults.get(v);

            HashMap<String, Integer> deck = currentPlayer.getArmyDeck().getArmyCards();

            if (deck.get("Infantry") >= infantry &
                    deck.get("Cavalry") >= cavalry &
                    deck.get("Artillery") >= artillery) {

                updateArmyCards(army, currentPlayer, v);
                frame.updateFrame();
                return;

            }

            frame.showError("not enough card");
            return;
        }

        frame.showError("invalid combination");
    }

    public static TradeFrame getFrame() {
        return frame;
    }

    public static void setFrame(TradeFrame frame) {
        TradeController.frame = frame;
    }
    
    public static void tradeTerritory(Player currentPlayer, Continent continent) {
    	
        ArrayList<String> deck = currentPlayer.getTerritoryDeck().getDeck();
        ArrayList<String> terr = new ArrayList<>();
    	for(Territory t : continent.getTerritories()) {
    		if(t.isVisible() ) {
    			if(!deck.contains(t.getName())) {
    				frame.showError("not enough card for choosen continent");
        			return;
    			}
    			
    			terr.add(t.getName());
    		}
    	
    	}
    	updateTradeCards(terr, currentPlayer);
    	frame.showError("successful trade!");
    }
    
    public static void updateTradeCards(ArrayList<String> terr, Player player) {
        for (int i = 0; i < terr.size(); i++) {
            player.getTerritoryDeck().removeCard(terr.get(i));
        }
    }

}
