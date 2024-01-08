package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.java.domain.Army;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArmyTest {
	private Army armyInstance;
	@BeforeEach
	void setUp() throws Exception {
		armyInstance = new Army();
	}
	
	//Glass box tests
    @Test
    public void test_army_creation(){
        Army army = new Army();

        Assertions.assertTrue(army.repOk());
    }

    @Test
    public void test_false_input(){
        Army army = new Army();

        assertThrows(RuntimeException.class, () -> {
            army.getTroop("AAA");
        });

        assertThrows(RuntimeException.class, () -> {
            army.addArmy("AAA", 5);
        });

        assertThrows(RuntimeException.class, () -> {
            army.deleteArmy("AAA", 5);
        });

    }

    @Test
    public void test_correct_input(){
        Army army = new Army();

        assertDoesNotThrow(() -> {
            army.getTroop("Infantry");
            army.getTroop("Cavalry");
            army.getTroop("Artillery");
        });
    }
    
    @Test
    public void test_remove_unsuccessBB(){
    	armyInstance.addArmy("Infantry", 50);
        assertThrows(ArithmeticException.class, () -> {
            armyInstance.deleteArmy("Infantry",  55);
        });
    }

    @Test
    public void test_initial_values(){
        Army army = new Army();
        int tot = army.getTroop("Infantry") + army.getTroop("Cavalry") + army.getTroop("Artillery");
        Assertions.assertEquals(0, army.getTroop("Infantry"));
        Assertions.assertEquals(0, army.getTroop("Cavalry"));
        Assertions.assertEquals(0, army.getTroop("Artillery"));
        Assertions.assertEquals(0, tot);
    }

    
    @Test
    public void test_remove_more_than_capacity(){
        Army army = new Army();
        army.addArmy("Infantry", 5);
        assertThrows(ArithmeticException.class, () -> {
            army.deleteArmy("Infantry",  10);
        });
    }
    
    
    //Black box tests
    @Test
    public void test_add_army(){
        Army army = new Army();
        army.addArmy("Infantry", 5);
        Assertions.assertEquals(5, army.getTroop("Infantry"));
    }
    
    @Test
    public void test_remove_success(){
        Army army = new Army();
        army.addArmy("Infantry", 10);
        army.deleteArmy("Infantry", 5);
        Assertions.assertEquals(5, army.getTroop("Infantry"));
    }
    
    @Test
    public void test_remove_successBB(){
    	armyInstance.addArmy("Infantry", 50);
        armyInstance.deleteArmy("Infantry", 5);
        Assertions.assertEquals(45, armyInstance.getTroop("Infantry"));
    }
    
    
    
}
