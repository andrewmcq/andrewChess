package Test.SetUp;

import java.util.ArrayList;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.KingBehaviorStartStandard;
import JavaChess.ChessPieces.ActionsBehaviors.PawnBehaviorStartStandard;
import JavaChess.ChessPieces.ActionsBehaviors.StartBehavior;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.Game;
import JavaChess.Player;
import JavaChess.SetUp.SetUp;
import JavaChess.SetUp.StandardGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * testing the successful standard board setup for classical chess
 */
public class StandardGameTest {
    Game game;
    SetUp standard;

    @BeforeEach
    public void setUp() {
        standard = new StandardGame();
        game = new Game();
    }

    /**
     * Tests the initialization of all information to have a classical chess starting position
     */
    @Test
    public void testSetUpGame() {
        standard.setUpGame(game);
        Player[] players= game.getPlayers();
        Board board = players[0].getBoard();
        ArrayList<ChessPiece> white= players[0].getPieces();
        ArrayList<ChessPiece> black= players[1].getPieces();

        assertNotEquals(white,black);

        //Testing Board points to same white pieces in player
        //King
        assertEquals(board.get("E1").getPiece(),white.get(0));
        //Queen
        assertEquals(board.get("D1").getPiece(),white.get(1));
        //Bishops
        assertEquals(board.get("F1").getPiece(),white.get(2));
        assertEquals(board.get("C1").getPiece(),white.get(3));
        assertNotEquals(board.get("F1").getPiece(),board.get("C1").getPiece());
        //Knights
        assertEquals(board.get("B1").getPiece(),white.get(4));
        assertEquals(board.get("G1").getPiece(),white.get(5));
        assertNotEquals(board.get("B1").getPiece(),board.get("G1").getPiece());
        //Rooks
        assertEquals(board.get("A1").getPiece(),white.get(6));
        assertEquals(board.get("H1").getPiece(),white.get(7));
        assertNotEquals(board.get("A1").getPiece(),board.get("H1").getPiece());
        //Pawns
        assertEquals(board.get("A2").getPiece(),white.get(8));
        assertEquals(board.get("B2").getPiece(),white.get(9));
        assertEquals(board.get("C2").getPiece(),white.get(10));
        assertEquals(board.get("D2").getPiece(),white.get(11));
        assertEquals(board.get("E2").getPiece(),white.get(12));
        assertEquals(board.get("F2").getPiece(),white.get(13));
        assertEquals(board.get("G2").getPiece(),white.get(14));
        assertEquals(board.get("H2").getPiece(),white.get(15));

        //Testing black
        //King
        assertEquals(board.get("E8").getPiece(),black.get(0));
        //Queen
        assertEquals(board.get("D8").getPiece(),black.get(1));
        //Bishops
        assertEquals(board.get("F8").getPiece(),black.get(2));
        assertEquals(board.get("C8").getPiece(),black.get(3));
        assertNotEquals(board.get("F8").getPiece(),board.get("C8").getPiece());
        //Knights
        assertEquals(board.get("B8").getPiece(),black.get(4));
        assertEquals(board.get("G8").getPiece(),black.get(5));
        assertNotEquals(board.get("B8").getPiece(),board.get("G8").getPiece());
        //Rooks
        assertEquals(board.get("A8").getPiece(),black.get(6));
        assertEquals(board.get("H8").getPiece(),black.get(7));
        assertNotEquals(board.get("A8").getPiece(),board.get("H8").getPiece());
        //Pawns
        assertEquals(board.get("A7").getPiece(),black.get(8));
        assertEquals(board.get("B7").getPiece(),black.get(9));
        assertEquals(board.get("C7").getPiece(),black.get(10));
        assertEquals(board.get("D7").getPiece(),black.get(11));
        assertEquals(board.get("E7").getPiece(),black.get(12));
        assertEquals(board.get("F7").getPiece(),black.get(13));
        assertEquals(board.get("G7").getPiece(),black.get(14));
        assertEquals(board.get("H7").getPiece(),black.get(15));

        //making sure kings start with correct behavior
        assertTrue(black.get(0).getActions() instanceof KingBehaviorStartStandard);
        assertTrue(white.get(0).getActions() instanceof KingBehaviorStartStandard);
        //Making sure all pawns are rooks standard start behavior pawns
        for (int i=6; i<16;i++) {
            assertTrue(black.get(i).getActions() instanceof StartBehavior);
            assertTrue(white.get(i).getActions() instanceof StartBehavior);
        }
    }
}
