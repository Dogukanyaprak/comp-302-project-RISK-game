package src.main.java.domain;

import src.main.java.domain.Board.Map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class TerritoryDeck implements Serializable {

    private static final String[] cardNames = Map.getNameTerritory();
    private static final ArrayList<String> availableCards = new ArrayList<>();
    private final ArrayList<String> deck = new ArrayList<>();

    public TerritoryDeck() {
    	
    }
    
    
    @Override
	public String toString() {
		return "deck: " + deck ;
	}


	public static void init() {
        Collections.addAll(availableCards, cardNames);
    }

    public ArrayList<String> getDeck() {
        return deck;
    }

    public void addCard(String terName) {
        deck.add(terName);
        availableCards.remove(terName);
    }

    public void removeCard(String terName) {
        deck.remove(terName);
        availableCards.add(terName);
    }
    
    public void pickRandomTerritory() {
        Random random = new Random();
        int val = random.nextInt(availableCards.size() + 1) - 1;
        String card = availableCards.get(val);
        availableCards.remove(val);
        deck.add(card);
    }
}
