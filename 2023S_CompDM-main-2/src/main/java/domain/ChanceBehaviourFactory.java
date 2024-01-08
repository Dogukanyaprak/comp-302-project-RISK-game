package src.main.java.domain;

import src.main.java.domain.ChanceBehavior.*;

import java.util.ArrayList;

public class ChanceBehaviourFactory {

    public static ArrayList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        names.add("Sabotage");
        names.add("Reinforcement");
        names.add("Coup");
        names.add("Rebellion");
        names.add("NuclearStrike");
        return names;
    }

    public static ChanceCardBehavior getBehaviour(String name) {
        if (name.equalsIgnoreCase("Sabotage")) {
            return new SabotageBehavior();
        }
        if (name.equalsIgnoreCase("Reinforcement")) {
            return new ReinforcementBehavior();
        }
        if (name.equalsIgnoreCase("Coup")) {
            return new CoupBehavior();
        }
        if (name.equalsIgnoreCase("Rebellion")) {
            return new RebellionBehavior();
        }
        return new NuclearStrikeBehavior();

    }
}
