package src.main.java.domain;

public class Dice {
    private static Dice instance;
    private static int MAX_SIDE = 6;
    private int value;

    private Dice() {
    }

    public static Dice getInstance() {
        if (instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public void roll() {
        value = (int) (Math.random() * MAX_SIDE) + 1;
    }

    public int getValue() {
        return value;
    }


}
