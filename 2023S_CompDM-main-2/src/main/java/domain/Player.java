package src.main.java.domain;

import src.main.java.domain.Board.Territory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Serializable {
    private final ArrayList<Territory> territories = new ArrayList<Territory>();
    private final Army army = new Army();
    private final String name;
    private final String color;
    private final ArmyDeck armyDeck;
    private final TerritoryDeck territoryDeck;
    private boolean alive;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.alive = true;
        armyDeck = new ArmyDeck();
        this.territoryDeck = new TerritoryDeck();
    }

    public String getColor() {
        return color;
    }

    public Army getArmy() {
        return army;
    }

    public ArmyDeck getArmyDeck() {
        return armyDeck;
    }
    
    

    public TerritoryDeck getTerritoryDeck() {
		return territoryDeck;
	}

	public String getName() {
        return name;
    }


    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void addTerritory(Territory t) {
        territories.add(t);
    }

    public void removeTerritory(Territory t) {
        territories.remove(t);
    }
    
    public void pickRandomCard() {
    	Random r = new Random();
    	int i = r.nextInt(2);
    	
    	if(i == 0) {
    		armyDeck.pickRandomArmy();
    	}else {
    		territoryDeck.pickRandomTerritory();
    	}
    }

}
