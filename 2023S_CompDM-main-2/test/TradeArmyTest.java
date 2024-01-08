package test;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.main.java.domain.Army;

class TradeArmyTest {
	private Army armyInstance;
	private HashMap<String, Integer> army;

	@BeforeEach
	void setUp() throws Exception {
		armyInstance = new Army();
		armyInstance.addArmy("Infantry", 50);
		armyInstance.addArmy("Artillery", 5);
	}
	
	//glass box

	@Test
	public void testInfantryToCavalry_SuccessfulTrade() {
		Army army = new Army();
		army.addArmy("Infantry", 50);
		army.tradeArmy("Infantry", "Cavalry", 10);

        Assertions.assertEquals(0, army.getTroop("Infantry"));
        Assertions.assertEquals(10, army.getTroop("Cavalry"));
	}
	
	@Test
	public void testInfantryToArtillery_UnsuccessfulTrade() {
		Army army = new Army();
		army.addArmy("Infantry", 50);
		army.tradeArmy("Infantry", "Artillery", 10);

        Assertions.assertEquals(50, army.getTroop("Infantry"));
        Assertions.assertEquals(0, army.getTroop("Artillery"));
	}
	
	
	@Test
	public void testArtilleryToCavalry_UnsuccessfulTrade() {
		Army army = new Army();
		army.addArmy("Artillery", 5);
		army.tradeArmy("Artillery", "Cavalry", -3);

        Assertions.assertEquals(5, army.getTroop("Artillery"));
        Assertions.assertEquals(0, army.getTroop("Cavalry"));
	}
	
	//black box
	
	@Test
	public void testArtilleryToInfantry_UnsuccessfulTrade() {
		armyInstance.tradeArmy("Artillery", "Infantry", 6);

        Assertions.assertEquals(5, armyInstance.getTroop("Artillery"));
        Assertions.assertEquals(50, armyInstance.getTroop("Infantry"));
	}
	

	
	@Test
	public void testInfantryToArtillery_SuccessfulTrade() {
		armyInstance.tradeArmy("Infantry", "Artillery", 3);

        Assertions.assertEquals(20, armyInstance.getTroop("Infantry"));
        Assertions.assertEquals(8, armyInstance.getTroop("Artillery"));
	}
}
