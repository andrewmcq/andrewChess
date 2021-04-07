package Test.ChessPieces;
import JavaChess.Board;
import JavaChess.ChessPieces.*;
import JavaChess.ChessPieces.ActionsBehaviors.BishopBehaviorStandard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class BishopTest {
    private ChessPiece bishop;
    private ArrayList<String> moves = new ArrayList<String>();
    private Board board;

    @BeforeEach
    public void setUp() throws Exception {
        board = new Board();
        moves.clear();
    }

    //sets up bishop with it's standard behavior
    private void standardBishops() {
        bishop = new Bishop(new BishopBehaviorStandard());
    }

    /**
     * testing standard bishop behavior moves
     */
    @Test
    public void testCandidateMovesStandard() {
        standardBishops();
        //placing bishop in H8 corner in empty board
        board.get("H8").setPiece(bishop);
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("G7"));
        assertTrue(moves.contains("F6"));
        assertTrue(moves.contains("E5"));
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("C3"));
        assertTrue(moves.contains("B2"));
        assertTrue(moves.contains("A1"));
        assertEquals(7,moves.size());

        //placing bishop in G8 on empty board
        board = new Board();
        board.get("G8").setPiece(bishop);
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("H7"));
        assertTrue(moves.contains("F7"));
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C4"));
        assertTrue(moves.contains("B3"));
        assertTrue(moves.contains("A2"));
        assertEquals(7,moves.size());

        //placing bishop in middle of empty board
        board = new Board();
        board.get("E4").setPiece(bishop);
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("G6"));
        assertTrue(moves.contains("H7"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("G2"));
        assertTrue(moves.contains("H1"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("C2"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C6"));
        assertTrue(moves.contains("B7"));
        assertTrue(moves.contains("A8"));
        assertEquals(13,moves.size());

        //blocking on diagonal by placing a piece in F5 upper right diagonal
        board.get("F5").setPiece(new Bishop());
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("G2"));
        assertTrue(moves.contains("H1"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("C2"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("D5"));
        assertTrue(moves.contains("C6"));
        assertTrue(moves.contains("B7"));
        assertTrue(moves.contains("A8"));
        assertEquals(11,moves.size());

        //blocking the upper left diagonal D5
        board.get("D5").setPiece(new Bishop());
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("G2"));
        assertTrue(moves.contains("H1"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("C2"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("D5"));
        assertEquals(8,moves.size());

        //blocking the bottom right diagonal F3
        board.get("F3").setPiece(new Bishop());
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("C2"));
        assertTrue(moves.contains("B1"));
        assertTrue(moves.contains("D5"));
        assertEquals(6,moves.size());

        //blocking the bottom left diagonal D3
        board.get("D3").setPiece(new Bishop());
        moves = bishop.candidateMoves(board);
        assertTrue(moves.contains("F5"));
        assertTrue(moves.contains("F3"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("D5"));
        assertEquals(4,moves.size());
    }
}
