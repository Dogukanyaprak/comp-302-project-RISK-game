package src.main.java.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class ArmyDeck implements Serializable {

    private static final ArrayList<ArmyDeck> allDecks = new ArrayList<>();
    private static final ArrayList<String> sysCards = new ArrayList<>();
    private final HashMap<String, Integer> armyCards;


    public ArmyDeck() {

        armyCards = new HashMap<>();
        armyCards.put("Infantry", 0);
        armyCards.put("Artillery", 0);
        armyCards.put("Cavalry", 0);

        allDecks.add(this);

    }

    public static void initialize() {

        for (int i = 0; i < Game.getInstance().getNumberOfPlayers(); ++i) {

            for (int j = 0; j < 3; ++j) sysCards.add("Infantry");
            for (int j = 0; j < 2; ++j) sysCards.add("Cavalry");
            for (int j = 0; j < 1; ++j) sysCards.add("Artillery");

        }

        Collections.shuffle(sysCards);
    }

    public void addArmyCard(String type, int count) {
        armyCards.put(type, armyCards.get(type) + count);
    }

    public HashMap<String, Integer> getArmyCards() {
        return armyCards;
    }

    public void removeArmyCard(String type, int count) {

        int initial = armyCards.get(type);

        if (count > initial) {
            throw new ArithmeticException();
        } else {
            armyCards.put(type, initial - count);
        }

    }

    public void pickRandomArmy() {
        Random random = new Random();

        if (sysCards.size() == 0) clearAll();

        int val = random.nextInt(sysCards.size() + 1) - 1;

        addArmyCard(sysCards.get(val), 1);
        sysCards.remove(val);

    }

    private void clear() {
        armyCards.clear();
        armyCards.put("Infantry", 0);
        armyCards.put("Artillery", 0);
        armyCards.put("Cavalry", 0);
    }

    private void clearAll() {
        for (ArmyDeck deck : allDecks) {
            deck.clear();
        }
    }

    /*

    Options will be as following
    1 - 3 Infantry cards => 1 Cavalry
    2 - 2 Infantry cards + 1 Cavalry card => 2 Cavalry
    3 - 2 Infantry cards + 1 Artillery card => 2 Artillery
    4 - 1 Infantry card + 2 Cavalry cards => 1 Cavalry + 1 Artillery
    5 - 1 Artillery + 2 Cavalry cards => 3 Artillery
     */
    public void trade(int option) {
        // TODO: We need to check if player has enough cards for the given option,


        switch (option) {
            case 0:
                removeArmyCard("Infantry", 3);
                break;
            case 1:
                removeArmyCard("Infantry", 2);
                removeArmyCard("Cavalry", 1);
                break;
            case 2:
                removeArmyCard("Infantry", 2);
                removeArmyCard("Artillery", 1);
                break;
            case 3:
                removeArmyCard("Infantry", 1);
                removeArmyCard("Cavalry", 2);
                break;
            case 4:
                removeArmyCard("Artillery", 1);
                removeArmyCard("Cavalry", 2);
                break;
        }
    }

}
