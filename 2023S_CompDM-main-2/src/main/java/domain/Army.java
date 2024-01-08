package src.main.java.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class Army implements Serializable {
    // Overview: Represents an army with Infantry, Cavalry, and Artillery troops.

    // Abstract function: AF(this) = Army with Infantry troops = army.get("Infantry"),
    //                                Cavalry troops = army.get("Cavalry"),
    //                                Artillery troops = army.get("Artillery").

    // Representation invariant: army HashMap should not be null and should only contain
    //                           entries for "Infantry", "Cavalry", and "Artillery".
    // Representation invariant more formally
    // Formally:
    // 1. army != null
    // 2. army.containsKey("Infantry") && army.containsKey("Cavalry") && army.containsKey("Artillery")
    // 3. army.get("Infantry") != null && army.get("Cavalry") != null && army.get("Artillery") != null
    // 4. army.size == 3

    // RepOk method: Checks if the representation invariant is satisfied.
    private static final int INF_POWER = 1;
    private static final int CAV_POWER = 5;
    private static final int ART_POWER = 10;
    private final HashMap<String, Integer> army;

    public Army() {
        army = new HashMap<>();
        army.put("Infantry", 0);
        army.put("Artillery", 0);
        army.put("Cavalry", 0);
    }

    public boolean repOk() {
        return army != null && army.get("Infantry") != null && army.get("Cavalry") != null && army.get("Artillery") != null && army.size() == 3;
    }

    private void checkType(String type) {
        // Requires: None
        // Modifies: None
        // Effects: Throws an IllegalArgumentException if the type parameter is not equal to
        //          "Infantry", "Artillery", or "Cavalry" (case sensitive).s
        if (!(Objects.equals(type, "Infantry") || Objects.equals(type, "Artillery") || Objects.equals(type, "Cavalry"))) {
            throw new IllegalArgumentException("Type is wrong, valid type Infantry-Artillery-Cavalry CASE SENSITIVE");
        }
    }

    public int getTroop(String type) {
        // Requires: None
        // Modifies: None
        // Effects: Returns the number of troops of the specified type in the army HashMap.
        return army.get(type);
    }

    public void addArmy(String type, int amount) {
        // Requires: type is not null. amount is greater than or equal to 0.
        // Modifies: Modifies the army HashMap by adding amount troops of the specified type.
        // Effects: None
        checkType(type);

        army.put(type, army.get(type) + amount);

    }

    public void deleteArmy(String type, int amount) {

        // Requires: amount is greater than or equal to 0.
        // Modifies: Modifies the army HashMap by removing amount troops of the specified type.
        // Effects: Throws an ArithmeticException if the amount is greater than the existing number of troops of the specified type in the army HashMap.

        checkType(type);

        if (amount > army.get(type)) {
            throw new ArithmeticException("Amount is greater than existing army");
        }

        army.put(type, army.get(type) - amount);

    }
    public HashMap<String, Integer> getArmy() {
        return army;
    }

    public int getTotalArmyPower() {
        return army.get("Infantry") * INF_POWER + army.get("Cavalry") * CAV_POWER + army.get("Artillery") * ART_POWER;
    }

    @Override
    public String toString() {
        // Requires: None
        // Modifies: None
        // Effects: Returns a string representation of the number of troops in the army HashMap, in the format "InfantryCount CavalryCount ArtilleryCount".
        return army.get("Infantry") + " " + army.get("Cavalry") + " " + army.get("Artillery");
    }

    public void tradeArmy(String type, String newType, int amount) {

        // Requires: type and newType should be one of this three strings: "Infantry", "Cavalry" and "Artillery"
        // Modifies: Modifies the army HashMap by trading troops from one type to another based on the specified parameters.
        // Effects: Depending on the combination of type and newType, the function trades troops between the types according to the specified amount.
        //          Throws an ArithmeticException if the amount is greater than the existing number of troops for the specified type in the army HashMap.

        if (amount < 0) {
            return;
        }

        switch (type) {
            case "Infantry":
                switch (newType) {
                    case "Cavalry":
                        if (army.get("Infantry") >= amount * 5) {
                            addArmy("Cavalry", amount);
                            deleteArmy("Infantry", amount * 5);
                        }

                    case "Artillery":
                        if (army.get("Infantry") >= amount * 10) {
                            addArmy("Artillery", amount);
                            deleteArmy("Infantry", amount * 10);
                        }
                }
            case "Cavalry":
                switch (newType) {
                    case "Infantry":
                        if (army.get("Cavalry") >= amount) {
                            addArmy("Infantry", amount * 5);
                            deleteArmy("Cavalry", amount);
                        }

                    case "Artillery":
                        if (army.get("Cavalry") >= amount * 2) {
                            addArmy("Artillery", amount);
                            deleteArmy("Cavalry", amount * 2);
                        }
                }

            case "Artillery":
                switch (newType) {
                    case "Infantry":
                        if (army.get("Artillery") >= amount) {
                            addArmy("Infantry", amount * 10);
                            deleteArmy("Artillery", amount);
                        }

                    case "Cavalry":
                        if (army.get("Artillery") >= amount) {
                            addArmy("Cavalry", amount * 2);
                            deleteArmy("Artillery", amount);
                        }

                }
        }

    }

}