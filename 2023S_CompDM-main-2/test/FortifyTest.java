package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.Controller.FortifyController;
import src.main.java.domain.Board.Territory;
import src.main.java.domain.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FortifyTest {

    static Territory fromTer;
    static Territory toTer;
    static Territory testTerr;
    static Player player1;
    static Player player2;

    @BeforeAll
    static void setUpFront() throws Exception{
        player1 = new Player("Player 1", "red");
        player2 = new Player("Player 2", "Blue");
        fromTer = new Territory("Attacker territory");
        toTer = new Territory("Attacker territory 2");
        testTerr = new Territory("Player 2 ter");

        fromTer.setOwner(player1);
        toTer.setOwner(player1);
        testTerr.setOwner(player2);
        fromTer.addArmy("Infantry", 10);
        toTer.addArmy("Infantry", 20);
        testTerr.addArmy("Infantry", 25);
        fromTer.addNeighbour(toTer);
        toTer.addNeighbour(fromTer);

    }
    @BeforeEach
    public void reset() {

        fromTer = new Territory("Attacker territory");
        toTer = new Territory("Attacker territory 2");

        fromTer.setOwner(player1);
        toTer.setOwner(player1);
        testTerr.setOwner(player2);
        fromTer.addArmy("Infantry", 10);
        toTer.addArmy("Infantry", 20);
        testTerr.addArmy("Infantry", 25);
        fromTer.addNeighbour(toTer);
        toTer.addNeighbour(fromTer);
    }

    /* GLASS BOX TESTS */
    @Test
    void notEnoughArmyTest() {
        fromTer.deleteArmy("Infantry",5);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FortifyController.check(toTer, fromTer,10,player1,"Infantry" );
        });
        String expectedMessage = "fortify invalid not enough";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    @Test
    void invalidNumberInputToMoveTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FortifyController.check(toTer, fromTer,-5,player1,"Infantry" );
        });
        String expectedMessage = "fortify invalid number";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    @Test
    void fromTerritoryTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FortifyController.check(testTerr, fromTer,5,player1,"Infantry" );
        });
        String expectedMessage = "fortify invalid from end territory";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    @Test
    void toTerritoryTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FortifyController.check(toTer, testTerr,5,player1,"Infantry" );
        });
        String expectedMessage = "fortify invalid from start territory";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    /* BLACK BOX TEST */
    @Test
    void allValidTest() {
        boolean result = FortifyController.check(toTer, fromTer,5,player1,"Infantry" );
        Assertions.assertEquals(true,result);
    }
    

}
