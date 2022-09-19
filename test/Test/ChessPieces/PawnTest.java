package Test.ChessPieces;
import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.Pawn;
import JavaChess.ChessPieces.ActionsBehaviors.PawnBehaviorStandard;
import JavaChess.ChessPieces.ActionsBehaviors.PawnBehaviorStartStandard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {
    private ChessPiece whitePawn;
    private ChessPiece blackPawn;
    private Board board;
    ArrayList <String> moves = new ArrayList<String>();

    @BeforeEach
    private void setUp() throws Exception{
        board = new Board();
        moves.clear();

    }

    //sets up pawns to be standard
    private void standardPawns() {
        whitePawn= new Pawn(new PawnBehaviorStandard(false));
        blackPawn= new Pawn(new PawnBehaviorStandard(true));
    }

    //makes sure the Standard pawn behavior returns the correct candidate moves for any position the the chess grid
    @Test
    public void testCandidateMovesStandard() {
        standardPawns();
        //setting pawns for testing on E3 and E5, should only return the square in front
        board.get("E3").setPiece(whitePawn);
        board.get("E5").setPiece(blackPawn);
        moves = whitePawn.candidateMoves(board);
        assertFalse(moves.contains("D4"));
        assertTrue(moves.contains("E4"));
        assertFalse(moves.contains("F4"));
        assertEquals(1, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertFalse(moves.contains("D4"));
        assertTrue(moves.contains("E4"));
        assertFalse(moves.contains("F4"));
        assertEquals(1, moves.size());

        // putting pawns on D4 and F4 to make the pawn return taking diagonals too
        board.get("D4").setPiece(new Pawn());
        board.get("F4").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("F4"));
        assertEquals(3, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("F4"));
        assertEquals(3, moves.size());

        //putting pawn in front of the test pawns so only diagonals return
        board.get("E4").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("F4"));
        assertFalse(moves.contains("E4"));
        assertEquals(2, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("D4"));
        assertTrue(moves.contains("F4"));
        assertFalse(moves.contains("E4"));
        assertEquals(2, moves.size());

        //resetting board
        board = new Board();

        //testing pawns options on the a edge with nothing to overtake
        board.get("A2").setPiece(whitePawn);
        board.get("A4").setPiece(blackPawn);
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("A3"));
        assertFalse(moves.contains("B3"));
        assertEquals(1, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("A3"));
        assertFalse(moves.contains("B3"));
        assertEquals(1, moves.size());

        //testing edge pawn with pawn to overtake
        board.get("B3").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("A3"));
        assertTrue(moves.contains("B3"));
        assertEquals(2, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("A3"));
        assertTrue(moves.contains("B3"));
        assertEquals(2, moves.size());

        //testing edge pawn with overtake as only option
        board.get("A3").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertFalse(moves.contains("A3"));
        assertTrue(moves.contains("B3"));
        assertEquals(1, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertFalse(moves.contains("A3"));
        assertTrue(moves.contains("B3"));
        assertEquals(1, moves.size());

        //testing H edge with open above and pawn to overtake
        board = new Board();
        board.get("H7").setPiece(whitePawn);
        board.get("G8").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("G8"));
        assertTrue(moves.contains("H8"));
        assertEquals(2, moves.size());
    }

    //testing that when a pawn  is moved two spaces, en passant is registered in candidate moves
    @Test
    public void testCandidateMovesStandardEnPassant(){
        //En passant for white to the left
        standardPawns();
        blackPawn = new Pawn(new PawnBehaviorStartStandard(true));
        board.get("C7").setPiece(blackPawn);
        board.get("D5").setPiece(whitePawn);
        board.makeMove("C7:C5");
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("C6"));

        //En Passant for white to the right
        board = new Board();
        board.get("E7").setPiece(blackPawn);
        board.get("D5").setPiece(whitePawn);
        board.makeMove("E7:E5");
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("E6"));

        //En Passant for black to left
        board = new Board();
        standardPawns();
        whitePawn = new Pawn(new PawnBehaviorStartStandard(false));
        board.get("C2").setPiece(whitePawn);
        board.get("D4").setPiece(blackPawn);
        board.makeMove("C2:C4");
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("C3"));

        //En Passant for black to right
        board = new Board();
        board.get("E2").setPiece(whitePawn);
        board.get("D4").setPiece(blackPawn);
        board.makeMove("E2:E4");
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("E3"));
    }

    //tests starting moves to make sure there is a the double move available to unmoved pawns;
    @Test
    public void testCandidateMovesStartStandard() {
        whitePawn = new Pawn(new PawnBehaviorStartStandard(false));
        blackPawn = new Pawn(new PawnBehaviorStartStandard(true));
        //testing a board where a pawn has only single jump and double jump avaliable straight
        board.get("E2").setPiece(whitePawn);
        board.get("E7").setPiece(blackPawn);
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("E3"));
        assertTrue(moves.contains("E4"));
        assertEquals(2, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E5"));
        assertEquals(2, moves.size());

        //Adding diagonal takes so the pawns have their maximum range of moves
        board.get("D3").setPiece(new Pawn());
        board.get("F3").setPiece(new Pawn());
        board.get("D6").setPiece(new Pawn());
        board.get("F6").setPiece(new Pawn());
        moves = whitePawn.candidateMoves(board);
        assertTrue(moves.contains("E3"));
        assertTrue(moves.contains("E4"));
        assertTrue(moves.contains("D3"));
        assertTrue(moves.contains("F3"));
        assertEquals(4, moves.size());
        moves = blackPawn.candidateMoves(board);
        assertTrue(moves.contains("E6"));
        assertTrue(moves.contains("E5"));
        assertTrue(moves.contains("D6"));
        assertTrue(moves.contains("F6"));
        assertEquals(4, moves.size());

    }
}
