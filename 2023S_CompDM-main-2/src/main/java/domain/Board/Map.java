package src.main.java.domain.Board;

import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    public static final int TER_COUNT = 42;
    private static final String[] nameTerritory = {"Alaska", "North West Territory", "Greenland", "Alberta", "Ontario",
            "Quebec", "Western United States", "Eastern United States", "Central America", "Venezuela",
            "Peru", "Brazil", "Argentina", "Iceland", "Scandinavia",
            "Ukraine", "Great Britain", "Northern Europe", "Western Europe", "Southern Europe",
            "North Africa", "Egypt", "East Africa", "Congo", "South Africa",
            "Madagascar", "Ural", "Serbia", "Yakutsk", "Kamchatka",
            "Irkutsk", "Afghanistan", "China", "Mongolia", "Japan",
            "Middle East", "India", "Siam", "Indonesia", "New Guinea",
            "Western Australia", "Eastern Australia"};
    private static Map map;
    private final int[][] adjTerritories = {
            {1, 3, 29},
            {0, 2, 3, 4},
            {1, 4, 5, 13},
            {0, 1, 4, 6},
            {1, 2, 3, 5, 6, 7},
            {2, 4, 7},
            {3, 4, 7, 8},
            {4, 5, 6, 8},
            {6, 7, 9},
            {9, 10, 11},
            {9, 11, 12},
            {9, 10, 12, 20},
            {10, 11},
            {2, 14, 16},
            {13, 15, 16, 17},
            {14, 17, 19, 26, 31, 35},
            {13, 14, 17, 18},
            {14, 15, 16, 18, 19},
            {16, 17, 19, 20},
            {15, 17, 18, 20, 21, 35},
            {11, 18, 19, 21, 22, 23},
            {19, 20, 22, 35},
            {20, 21, 23, 24, 25, 35},
            {20, 22, 24},
            {22, 23, 25},
            {22, 24},
            {15, 27, 31, 32},
            {26, 28, 30, 32, 33},
            {27, 29, 30},
            {0, 28, 30, 33, 34},
            {27, 28, 29, 33},
            {15, 26, 32, 35, 36},
            {26, 27, 31, 33, 36, 37},
            {27, 29, 32, 34},
            {29, 33},
            {15, 19, 21, 22, 31, 36},
            {31, 32, 35, 37},
            {32, 36, 38},
            {37, 39, 40},
            {38, 40, 41},
            {38, 39, 41},
            {39, 40}};
    //Including array for Continents(rows)
    private final int[][] incContinents = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16, 17, 18, 19},
            {20, 21, 22, 23, 24, 25},
            {26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37},
            {38, 39, 40, 41}};
    private final String[] nameContinent = {"North America", "South America", "Europe", "Africa", "Asia", "Australia"};
    private final ArrayList<Continent> continents = new ArrayList<>();
    private final ArrayList<Territory> territories = new ArrayList<>();

    private Map() {
        createTerritories();
        createContinents();
        connectNeighbours();
    }

    public static Map getMap() {
        if (map == null) {
            map = new Map();
        }
        return map;
    }

    public static String[] getNameTerritory() {
        return nameTerritory;
    }

    private void createTerritories() {
        for (int i = 0; i < TER_COUNT; i++) {
            String territoryName = nameTerritory[i];
            Territory territory = new Territory(territoryName);
            territories.add(territory);
        }

    }

    private void connectNeighbours() {
        for (int t = 0; t < TER_COUNT; t++) {
            for (int n = 0; n < adjTerritories[t].length; n++) {
                Territory terr = territories.get(t);
                Territory neigh = territories.get(adjTerritories[t][n]);
                terr.addNeighbour(neigh);
            }
        }
    }

    private void createContinents() {
        for (int c = 0; c < incContinents.length; c++) {
            String ContinentName = nameContinent[c];
            Continent continent = new Continent(ContinentName);
            for (int index : incContinents[c]) {
                Territory territory = territories.get(index);
                continent.addTerritory(territory);
            }
            continents.add(continent);

        }
    }

    public Continent getContinent(int id) {
        return continents.get(id);
    }

    public Territory getTerritory(String name) {
        for (Territory t : territories) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public ArrayList<Continent> getAllContinents() {
        return continents;
    }

    public ArrayList<Territory> getAllTerritories() {
        return territories;
    }

    public int[][] getIncContinents() {
        return incContinents;
    }


}
