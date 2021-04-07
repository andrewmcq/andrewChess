package Test.ChessPieces;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.QueenBehaviorStandard;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import JavaChess.ChessPieces.Queen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

public class QueenTest {
    private ChessPiece queen;
    private ArrayList<String> moves = new ArrayList<String>();
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
        moves.clear();
    }

    //sets up the queen with standard queens behavior
    public void standardQueens() {
        queen = new Queen(new QueenBehaviorStandard());
    }

    /**
     * testing standard queen behavior moves
     */
    @Test
    public void testCandidateMovesStandard() {
        standardQueens();

        //testing queen in the bottom left corner
        board.get("A1").setPiece(queen);
        moves = queen.candidateMoves(board);
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
        assertTrue(moves.contains("B2"));
        assertTrue(moves.contains("C3"));
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("E5"));
        assertTrue(moves.contains("F6"));
        assertTrue(moves.contains("G7"));
        assertTrue(moves.contains("H8"));
        assertEquals(21,moves.size());

        //testing queen in the top right corner
        board = new Board();
        board.get("H8").setPiece(queen);
        moves = queen.candidateMoves(board);
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
        assertTrue(moves.contains("B2"));
        assertTrue(moves.contains("C3"));
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("E5"));
        assertTrue(moves.contains("F6"));
        assertTrue(moves.contains("G7"));
        assertTrue(moves.contains("A8"));
        assertEquals(21,moves.size());

        //testing queen that can only take adjacent squares
        board = new Board();
        board.get("E4").setPiece(queen);
        board.get("E5").setPiece(new Queen());
        board.get("E3").setPiece(new Queen());
        board.get("D4").setPiece(new Queen());
        board.get("D5").setPiece(new Queen());
        board.get("D3").setPiece(new Queen());
        board.get("F4").setPiece(new Queen());
        board.get("F5").setPiece(new Queen());
        board.get("F3").setPiece(new Queen());
        moves = queen.candidateMoves(board);
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
}
