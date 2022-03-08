package isp.lab3;

import isp.lab3.exercise5.VendingMachine;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VendingMachineTest {

    VendingMachine testvm = new VendingMachine();
    @Test
    public void displayItemsTest() {
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        testvm.displayProducts();
        
        String expectedOutput = "Bounty ID: 1 PRICE: 3\n" + "Snickers ID: 2 PRICE: 3\n" +
                                "7Days Cacao ID: 3 PRICE: 4\n" +
                                "7Days Vanilla ID: 4 PRICE: 4\n" +
                                "Mars ID: 5 PRICE: 3\n" +
                                "Twix ID: 6 PRICE: 3\n" +
                                "Aqua Carpatica ID: 7 PRICE: 3\n" +
                                "Coca Cola ID: 8 PRICE: 5\n" +
                                "Pepsi ID: 9 PRICE: 5\n" +
                                "Sprite ID: 10 PRICE: 5\n" +
                                "Dr. Pepper ID: 11 PRICE: 5\n" +
                                "Milky Way ID: 12 PRICE: 2\n" +
                                "Teasers ID: 13 PRICE: 2\n";
        
        assertEquals("Should display the products", expectedOutput, outContent.toString());     
    }
    @Test
    public void insertCoinTest () {
        testvm.insertCoin(7);
        testvm.insertCoin(8);
        assertEquals("Should be 15 coins", 15, testvm.getCoins());
    }
    @Test
    public void selectProductTest () {
        testvm.insertCoin(7);
        assertEquals("Should print the product with id=5", "Selected product is: Mars", testvm.selectProduct(5));
    }
    
}
