package Test;

import JavaChess.*;
import JavaChess.SetUp.StandardGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for testing everything relating to game
 */
public class GameTest {
    private Game game;

    /**
     * sets up game in standard classical way
     */
    public void standardSetUp() {
        game = new Game();
        game.setUp(new StandardGame());
    }

    /**
     * Makes sure turn flag swaps properly
     */
    @Test
    void testSwapTurns() {
        standardSetUp();
        assertEquals(0,game.getTurnFlag());
        game.swapTurns();
        assertEquals(1,game.getTurnFlag());
        game.swapTurns();
        assertEquals(0,game.getTurnFlag());
        game.swapTurns();
        assertEquals(1,game.getTurnFlag());
    }
}
