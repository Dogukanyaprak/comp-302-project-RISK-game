package src.main.java.domain.ChanceBehavior;

import src.main.java.domain.Board.Territory;

public interface ChanceCardBehavior {
    void execute(Territory ter);

    String getMessage();
}
