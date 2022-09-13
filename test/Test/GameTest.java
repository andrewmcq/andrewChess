package Test;

import JavaChess.*;
import JavaChess.SetUp.StandardGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

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

    /**
     * testing turn flag flipped and move sent to player
     */
    @Test
    void testPlayMove() {
        standardSetUp();
        assertEquals(0,game.getTurnFlag());
        game.playMove("A2:A4");
        assertEquals(1,game.getTurnFlag());
        assertNull(game.getPlayers()[0].getBoard().get("A2").getPiece());
    }

    /**
     * makes sure all the moves from player function get to main
     */
    @Test
    void testGetMoves() {
        standardSetUp();
        Player p = game.getPlayers()[0];
        assertEquals(game.getMoves(),p.getAvailableMoves());
    }

    @Test
    void testMoves() {
        ArrayList<String> moves;
        standardSetUp();
        moves = game.getMoves();
        game.playMove("E2:E4");
        moves = game.getMoves();
        game.playMove("D7:D5");
        moves = game.getMoves();
        game.playMove("E4:D5");
    }

    @Test
    void testThreefoldRepetition() {
        ArrayList<String> moves;
        standardSetUp();
        //testing standard threefold
        game.getMoves();
        game.playMove("D2:D4"); // D pawn 2 forward
        game.getMoves();
        game.playMove("D7:D5"); // D pawn 2 forward
        game.getMoves();
        game.playMove("D1:D3"); // queen behind D pawn
        game.getMoves();
        game.playMove("D8:D6"); // queen behind D pawn
        game.getMoves();
        game.playMove("D3:D1"); //queen back to king
        game.getMoves();
        game.playMove("D6:D8"); //queen back to king : 2nd occourence of this position
        game.getMoves();
        game.playMove("D1:D3"); // queen behind D pawn
        game.getMoves();
        game.playMove("D8:D6"); // queen behind D pawn
        game.getMoves();
        game.playMove("D3:D1"); //queen back to king
        game.getMoves();
        game.playMove("D6:D8"); //queen back to king : 3 fold repetition game should end
        moves = game.getMoves();
        assertTrue(moves.size()==0);
    }
}
