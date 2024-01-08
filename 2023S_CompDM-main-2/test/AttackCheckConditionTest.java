package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.Controller.AttackController;
import src.main.java.domain.Phase.AttackPhase;
import src.main.java.domain.Player;
import src.main.java.domain.Board.Territory;

class AttackCheckConditionTest {

	static String armyType = "Infantry";
	static Player p1;
	static Player p2;
	static Player p3;
	static Territory attackerTerritory;
	static Territory defenderTerritory;
	static Territory notNeighborTerritory;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		p1 = new Player("Player 1", "Red");
		p2 = new Player("Player 2", "Green");
		p3 = new Player("Player 3", "Blue");
		attackerTerritory = new Territory("Attacker Territtory");
		defenderTerritory = new Territory("Defender Territory");
		notNeighborTerritory = new Territory("Not Neighbor Territory");
		
		attackerTerritory.setOwner(p1);
		defenderTerritory.setOwner(p2);
		notNeighborTerritory.setOwner(p2);
		attackerTerritory.getArmy().addArmy("Infantry", 10);
		defenderTerritory.getArmy().addArmy("Infantry", 3);
		attackerTerritory.addNeighbour(defenderTerritory);
		defenderTerritory.addNeighbour(attackerTerritory);
	}
	
	@BeforeEach
	public void reset(){
		attackerTerritory = new Territory("Attacker Territtory");
		defenderTerritory = new Territory("Defender Territory");
		attackerTerritory.setOwner(p1);
		defenderTerritory.setOwner(p2);
		attackerTerritory.getArmy().addArmy("Infantry", 10);
		defenderTerritory.getArmy().addArmy("Infantry", 3);
		attackerTerritory.addNeighbour(defenderTerritory);
		defenderTerritory.addNeighbour(attackerTerritory);
    }
	
	
	
	/* BLACK BOX TESTS THAT CHECKS VALIDATION */
	@Test
	void allValidTest() {
		boolean result = AttackPhase.checkCondition(attackerTerritory, defenderTerritory, p1, armyType);
        Assertions.assertEquals(true, result);
	}
	
	
	/* GLASS BOX TESTS */
	@Test
	void sameOwnerTest() {
		defenderTerritory.setOwner(p1);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			AttackPhase.checkCondition(attackerTerritory, defenderTerritory, p1, armyType);
        });
		
		String expectedMessage = "Owners must be different";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
			
	}
	
	@Test
	void notNeighborTest() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			AttackPhase.checkCondition(attackerTerritory, notNeighborTerritory, p1, armyType);
        });
		String expectedMessage = "Territories should be neighbor";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
	}
	
	@Test
	void notEnoughArmyTest() {
		attackerTerritory.getArmy().deleteArmy("Infantry", 9);
		
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			AttackPhase.checkCondition(attackerTerritory, defenderTerritory, p1, armyType);
        });
		String expectedMessage = "Attacker should have 2 army";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
	}
	

	@Test
	void currentPlayerOwnerTest() {
		Player currentPlayer = p3;
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
			AttackPhase.checkCondition(attackerTerritory, defenderTerritory, currentPlayer, armyType);
        });
		String expectedMessage = "Player must be the current player";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
	}
	
	
	

}
