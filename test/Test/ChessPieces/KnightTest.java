package Test.ChessPieces;
import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.Knight;
import JavaChess.ChessPieces.ActionsBehaviors.KnightBehaviorStandard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {
    private ChessPiece knight;
    private ArrayList<String> moves = new ArrayList<String>();
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        moves.clear();
    }

    //initializes knights with standard chess behavior
    public void standardKnights() {
        knight = new Knight(new KnightBehaviorStandard());
    }

    /**
     * testing standard knight behavior moves
     */
    @Test
    public void testCandidateMovesStandard() {
        standardKnights();

        //testing knight in bottom left corner A1
        board.get("A1").setPiece(knight);
        moves = knight.candidateMoves(board);
        assertTrue(moves.contains("C2"));
        assertTrue(moves.contains("B3"));
        assertEquals(2,moves.size());

        //testing knight in top right corner H8
        board = new Board();
        board.get("H8").setPiece(knight);
        moves = knight.candidateMoves(board);
        assertTrue(moves.contains("F7"));
        assertTrue(moves.contains("G6"));
        assertEquals(2,moves.size());

        //testing knight in the middle of the board F4
        board = new Board();
        board.get("F4").setPiece(knight);
        moves = knight.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("G6"));
        assertTrue(moves.contains("H5"));
        assertTrue(moves.contains("H3"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("E2"));
        assertTrue(moves.contains("G2"));
        assertEquals(8,moves.size());

        //testing knight in middle of board with a piece in its attack squares
        board.get("E6").setPiece(new Knight());
        moves = knight.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertEquals(8,moves.size());
    }
}
