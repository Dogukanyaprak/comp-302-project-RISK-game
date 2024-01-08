package src.main.java.domain.Board;


import java.io.Serializable;
import java.util.ArrayList;

public class Continent implements Serializable {

    String name;
    ArrayList<Territory> territories = new ArrayList<Territory>();
    boolean visible;

    //constructor
    public Continent(String name) {
        this.name = name;
        visible = true;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }


}


