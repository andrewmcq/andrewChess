package Test.ChessPieces;
import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.RookBehaviorStartStandard;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.Rook;
import JavaChess.ChessPieces.ActionsBehaviors.RookBehaviorStandard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {
    private ChessPiece rook;
    private ArrayList<String> moves = new ArrayList<String>();
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        moves.clear();
    }

    //sets up rooks to standard
    public void standardRooks() {
        rook = new Rook(new RookBehaviorStandard());
    }

    /**
     * testing standard rook behavior
     */
    @Test
    public void testCandidateMovesStandard() {
        standardRooks();
        //testing a rook aligned in A1 corner
        board.get("A1").setPiece(rook);
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("A2"));
        assertTrue(moves.contains("A3"));
        assertTrue(moves.contains("A4"));
        assertTrue(moves.contains("A5"));
        assertTrue(moves.contains("A6"));
        assertTrue(moves.contains("A7"));
        assertTrue(moves.contains("A8"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("C1"));
        assertTrue(moves.contains("D1"));
        assertTrue(moves.contains("E1"));
        assertTrue(moves.contains("F1"));
        assertTrue(moves.contains("G1"));
        assertTrue(moves.contains("H1"));
        assertEquals(14,moves.size());

        //testing rook aligned in H8 corner
        board = new Board();
        board.get("H8").setPiece(rook);
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("H2"));
        assertTrue(moves.contains("H3"));
        assertTrue(moves.contains("H4"));
        assertTrue(moves.contains("H5"));
        assertTrue(moves.contains("H6"));
        assertTrue(moves.contains("H7"));
        assertTrue(moves.contains("H1"));
        assertTrue(moves.contains("B8"));
        assertTrue(moves.contains("C8"));
        assertTrue(moves.contains("D8"));
        assertTrue(moves.contains("E8"));
        assertTrue(moves.contains("F8"));
        assertTrue(moves.contains("G8"));
        assertTrue(moves.contains("A8"));
        assertEquals(14,moves.size());

        //tesing rook in the middle with nothing blocking
        board = new Board();
        board.get("E5").setPiece(rook);
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E7"));
        assertTrue(moves.contains("E8"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("E3"));
        assertTrue(moves.contains("E2"));
        assertTrue(moves.contains("E1"));
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("G5"));
        assertTrue(moves.contains("H5"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C5"));
        assertTrue(moves.contains("B5"));
        assertTrue(moves.contains("A5"));
        assertEquals(14,moves.size());

        //blocking rook with piece on E7
        board.get("E7").setPiece(new Rook());
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E7"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("E3"));
        assertTrue(moves.contains("E2"));
        assertTrue(moves.contains("E1"));
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("G5"));
        assertTrue(moves.contains("H5"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C5"));
        assertTrue(moves.contains("B5"));
        assertTrue(moves.contains("A5"));
        assertEquals(13,moves.size());

        //blocking rook with piece on E4
        board.get("E4").setPiece(new Rook());
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E7"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("G5"));
        assertTrue(moves.contains("H5"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C5"));
        assertTrue(moves.contains("B5"));
        assertTrue(moves.contains("A5"));
        assertEquals(10,moves.size());

        board.get("F5").setPiece(new Rook());
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E7"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C5"));
        assertTrue(moves.contains("B5"));
        assertTrue(moves.contains("A5"));
        assertEquals(8,moves.size());

        board.get("D5").setPiece(new Rook());
        moves = rook.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E7"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("D5"));
        assertEquals(5,moves.size());

    }

    /**
     * Start rook test behavior should be identical to normal
     */
    @Test
    public void testStartCandidateMoves() {
        rook = new Rook(new RookBehaviorStartStandard());
        board.get("E4").setPiece(rook);
        moves = rook.candidateMoves(board);
        rook.setActions(new RookBehaviorStandard());
        ArrayList<String> expected = rook.candidateMoves(board);
        for (int i=0; i<expected.size();i++) {
            assertTrue(expected.contains(moves.get(i)));
        }
    }
}
