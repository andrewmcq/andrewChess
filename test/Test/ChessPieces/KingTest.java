package Test.ChessPieces;
import JavaChess.Board;
import JavaChess.ChessPieces.*;
import JavaChess.ChessPieces.ActionsBehaviors.KingBehaviorStandard;
import JavaChess.ChessPieces.ActionsBehaviors.KingBehaviorStartStandard;
import JavaChess.ChessPieces.ActionsBehaviors.RookBehaviorStartStandard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class KingTest {
    private ChessPiece king;
    private ArrayList<String> moves = new ArrayList<String>();
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        moves.clear();
    }

    //sets up king with standard king behavior
    public void standardKings() {
        king = new King(new KingBehaviorStandard());
    }

    //tests kings with standard action behavior
    @Test
    public void testCandidateMovesStandard() {
        standardKings();

        //testing King in the bottom left corner
        board.get("A1").setPiece(king);
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("A2"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("B2"));
        assertEquals(3,moves.size());

        //testing King in the top right corner
        board = new Board();
        board.get("H8").setPiece(king);
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("H7"));
        assertTrue(moves.contains("G8"));
        assertTrue(moves.contains("G7"));
        assertEquals(3,moves.size());

        //testing King in the middle
        board = new Board();
        board.get("E4").setPiece(king);
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("E3"));
        assertTrue(moves.contains("E5"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("F4"));
        assertTrue(moves.contains("F5"));
        assertEquals(8,moves.size());
    }

    /**
     * testing castling
     */
    @Test
    public void testCastling() {
        king = new King(new KingBehaviorStartStandard());
        ChessPiece r1 = new Rook();
        ChessPiece r2 = new Rook();
        //testing unmoved king with ;'moved rooks
        //should just return the same as normal king
        board.get("E1").setPiece(king);
        board.get("A1").setPiece(r1);
        board.get("H1").setPiece(r2);
        moves = king.candidateMoves(board);
        assertFalse(moves.contains("C1"));
        assertFalse(moves.contains("G1"));
        assertEquals(5,moves.size());

        //setting rooks as starter rooks and now castling should appear
        r1.setActions(new RookBehaviorStartStandard());
        r2.setActions(new RookBehaviorStartStandard());
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("C1"));
        assertTrue(moves.contains("G1"));
        assertEquals(7,moves.size());

        //blocking on side of the castling
        board.get("G1").setPiece(new King());
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("C1"));
        assertFalse(moves.contains("G1"));
        assertEquals(6,moves.size());

        //testing castling for above
        board = new Board();
        board.get("E8").setPiece(king);
        board.get("A8").setPiece(r1);
        board.get("H8").setPiece(r2);
        moves = king.candidateMoves(board);
        assertTrue(moves.contains("C8"));
        assertTrue(moves.contains("G8"));
        assertEquals(7,moves.size());

    }
}
