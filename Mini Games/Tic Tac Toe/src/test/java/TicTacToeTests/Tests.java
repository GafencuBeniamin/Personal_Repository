
package TicTacToeTests;

import TicTacToeGame.TicTacToeGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author mihai.hulea
 */
public class Tests {
    TicTacToeGame testgame=new TicTacToeGame();
    @Test
    public void testCollumnAndRow() {
        assertEquals("Should print collumn number", 1, testgame.findCollumn(2));
        assertEquals("Should print row number", 0, testgame.findRow(2));
    }

    

}
