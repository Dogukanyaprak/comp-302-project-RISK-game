package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import src.main.java.Controller.TradeController;

public class BerkTradeTest {
	

	TradeController tradeController=new TradeController();
	
	@Test
	public void testIsValidTrade_InsufficientInfantry_ReturnsNegativeOne() {
	    int infantry = 1;
	    int cavalry = 3;
	    int artillery = 1;
	    
	    int actualIndex = TradeController.isValidTrade(infantry, cavalry, artillery);
	    
	    assertEquals(-1, actualIndex);
	}
	
	@Test
	public void testIsValidTrade_ValidTrade_ReturnsValidCombinationIndex() {
	    int infantry = 2;
	    int cavalry = 1;
	    int artillery = 0;
	    int expectedIndex = 1;
	    
	    int actualIndex = TradeController.isValidTrade(infantry, cavalry, artillery);
	    
	    assertEquals(expectedIndex, actualIndex);
	}

	@Test
	public void testIsValidTrade_InvalidTrade_ReturnsNegativeOne() {
	    int infantry = 3;
	    int cavalry = 1;
	    int artillery = 1;
	    
	    int actualIndex = TradeController.isValidTrade(infantry, cavalry, artillery);
	    
	    assertEquals(-1, actualIndex);
	}
    
	@Test
	public void testIsValidTrade_ZeroUnitsAndNegative_ReturnsNegativeOne() {
	    int infantry = 0;
	    int cavalry = 0;
	    int artillery = -1;
	    
	    int actualIndex = TradeController.isValidTrade(infantry, cavalry, artillery);
	    
	    assertEquals(-1, actualIndex);
	}
    
	@Test
	public void testIsValidTrade_MaxValidTradeCombination_ReturnsValidCombinationIndex() {
	    int infantry = 0;
	    int cavalry = 2;
	    int artillery = 1;
	    int expectedIndex = 4;
	    
	    
	    int actualIndex = TradeController.isValidTrade(infantry, cavalry, artillery);
	    
	    assertEquals(expectedIndex, actualIndex);
	}

}
