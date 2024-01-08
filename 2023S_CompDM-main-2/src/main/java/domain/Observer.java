package src.main.java.domain;

import src.main.java.domain.Board.Territory;

public interface Observer {
    void update(Territory territory);
}
