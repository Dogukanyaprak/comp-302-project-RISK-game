package src.main.java.domain.Phase;

import src.main.java.domain.ChanceBehaviourFactory;
import src.main.java.domain.ChanceBehavior.ChanceCardBehavior;

import java.util.ArrayList;
import java.util.Random;

public class ChancePhase {
    private static ChancePhase instance;
    private final Random random = new Random();

    private ChancePhase() {

    }

    public static ChancePhase getInstance() {
        if (instance == null) {
            instance = new ChancePhase();
        }
        return instance;
    }

    public ChanceCardBehavior pickChance() {

        ArrayList<String> names = ChanceBehaviourFactory.getNames();
        int randomNumber = random.nextInt(names.size());
        return ChanceBehaviourFactory.getBehaviour(names.get(randomNumber));

    }

}
