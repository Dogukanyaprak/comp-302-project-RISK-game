package src.main.java.Controller;

import src.main.java.domain.Board.Map;
import src.main.java.domain.Board.Territory;

public class MapRecreationController {

    public static void toggleTerritory(String cellId) {

        Territory territoryToMakeVisible = Map.getMap().getTerritory(cellId);

        territoryToMakeVisible.toggleVisibility();

    }

}
