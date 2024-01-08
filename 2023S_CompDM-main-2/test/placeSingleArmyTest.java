package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import src.main.java.Controller.InitializingArmyController;
import src.main.java.domain.Board.Map;
import src.main.java.domain.Board.Territory;
import src.main.java.domain.Game;
import src.main.java.domain.Player;

import java.util.ArrayList;

public class placeSingleArmyTest {

    static Game game;
    static String[] nameTerritory;

    @BeforeAll
    static void setup() {
        game = Game.getInstance();
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        game.setPlayers(names);
        nameTerritory = Map.getNameTerritory();
        ;
    }

    /*
    BLACK BOX TESTING
     */
    @Test
    void territory_not_exists() {

        int retVal = InitializingArmyController.placeSingleArmy(null, null);
        Assertions.assertNotEquals(0, retVal);

    }

    @Test
    void territory_exists() {
        Territory ter = Game.getInstance().getMap().getTerritory(nameTerritory[0]);
        Player currPlayer = Game.getInstance().getCurrentPlayer();
        int retVal = InitializingArmyController.placeSingleArmy(ter, currPlayer);
        Assertions.assertNotEquals(-1, retVal);
    }

    /*
    GLASS-BOX TESTING
     */

    @Test
    void not_owned_territory() {
        Territory ter = game.getMap().getTerritory("Brazil");
        Player currPlayer = game.getCurrentPlayer();
        ter.setOwner(currPlayer);

        Assertions.assertEquals(-3, InitializingArmyController.placeSingleArmy(ter, null));
    }

    @Test
    void not_visible_territory() {
        Territory ter = Map.getMap().getTerritory("Serbia");
        ter.toggleVisibility();
        Assertions.assertEquals(-2, InitializingArmyController.placeSingleArmy(ter, Game.getInstance().getCurrentPlayer()));
    }

    @Test
    void successful_army_placement() {

        int retVal;
        String terName = "Iceland";
        Player currPlayer = game.getCurrentPlayer();
        Territory ter = game.getMap().getTerritory(terName);

        ter.setOwner(currPlayer);
        ter.addArmy("Infantry", 5);

        currPlayer.getArmy().addArmy("Infantry", 5);
        ter.setOwner(currPlayer);

        retVal = InitializingArmyController.placeSingleArmy(ter, currPlayer);

        Assertions.assertEquals(0, retVal);
        Assertions.assertEquals(6, ter.getArmy().getTroop("Infantry"));
        Assertions.assertEquals(4, currPlayer.getArmy().getTroop("Infantry"));
    }

}
