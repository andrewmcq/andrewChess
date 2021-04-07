package Test;
import JavaChess.*;

import JavaChess.ChessPieces.ActionsBehaviors.QueenBehaviorStandard;
import JavaChess.ChessPieces.Bishop;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.Pawn;
import JavaChess.ChessPieces.Queen;
import JavaChess.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing methods specific to Board class
 */
public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp () throws Exception {
        board = new Board();
    }

    /**
     * Makes sure initialized board consists of 64 squares with proper chess coordinates
     */
    @Test
    public void testBoard() {
        Square[][] b = board.getBoard();
        String expected = "A1";
        for (int i=0;i<8;i++) {
            expected = Character.toString(expected.charAt(0))+"1";
            for (int j=0; j<8;j++){
                assertEquals(null,b[i][j].getPiece());
                assertEquals(expected,b[i][j].getPosition());
                expected = Character.toString(expected.charAt(0))+Character.toString(expected.charAt(1)+1);
            }
            expected = Character.toString(expected.charAt(0)+1)+Character.toString(expected.charAt(1));
        }
    }

    //Makes sure that Chess coordinates convert to array indices properly
    @Test
    public void testConvertCoord() {
        int[] coord = new int[]{0, 0};
        assertArrayEquals(coord, board.convertCoord("A1"));
        coord = new int[] {0,7};
        assertArrayEquals(coord, board.convertCoord("A8"));
        coord = new int[] {7,0};
        assertArrayEquals(coord, board.convertCoord("H1"));
        coord = new int[] {7,7};
        assertArrayEquals(coord, board.convertCoord("H8"));
        coord = new int[] {1,3};
        assertArrayEquals(coord, board.convertCoord("B4"));
        coord = new int[] {4,3};
        assertArrayEquals(coord, board.convertCoord("E4"));
    }

    //makes sure isOccupied returns true if a square is occupied, and returns false if the quare.piece is null
    @Test
    public void testIsOccupied() {
        assertFalse(board.isOccupied("E3"));
        board.get("C3").setPiece(new Pawn());
        assertTrue(board.isOccupied("C3"));
        assertFalse(board.isOccupied("A5"));
        board.get("A5").setPiece(new Bishop());
        assertTrue(board.isOccupied("A5"));
    }

    /**
     * tests that the copy of the board is not the same bject and only points to the same pieces
     */
    @Test
    public void testCopy() {
        ChessPiece pawn1 = new Pawn();
        ChessPiece pawn2 = new Pawn();
        board.get("E5").setPiece(pawn1);
        board.get("A1").setPiece(pawn2);
        Board copy = board.copy();
        assertNotEquals(board,copy);
        for(int i=0; i < 8; i++) {
            for (int j=0; j<8; j++) {
                assertNotEquals(board.getBoard()[i][j],copy.getBoard()[i][j]);
                assertEquals(board.getBoard()[i][j].getPiece(),copy.getBoard()[i][j].getPiece());
                assertEquals(board.getBoard()[i][j].getPosition(),copy.getBoard()[i][j].getPosition());
            }
        }

    }

    /**
     * testing that make move overwrites the squares, chess piece and nothing else
     */
    @Test
    public void testMakeMove() {
        ChessPiece queen = new Queen(new QueenBehaviorStandard());
        board.get("E4").setPiece(queen);
        //making sure squares to be tested are completely independent
        assertNotEquals(board.get("E4"),board.get("E8"));
        assertNotEquals(board.get("E4").getPiece(),board.get("E8").getPiece() );
        assertNotEquals(board.get("E4").getPosition(),board.get("E8").getPosition() );

        //copying the queen on E4 to E8
        board.makeMove("E4:E8");
        //making sure the squares arent the same and the piece moved
        assertNotEquals(board.get("E4"),board.get("E8"));
        assertNotEquals(board.get("E4").getPiece(),board.get("E8").getPiece() );
        assertNotEquals(board.get("E4").getPosition(),board.get("E8").getPosition() );
        //checking that the queen is now in the e8 pos square
        assertEquals(queen,board.get("E8").getPiece());


    }

    /**
     * testing set Board sets the board properly
     */
    @Test
    public void testSetBoard() {
        Board b = new Board();
        b.get("E3").setPiece(new Queen(new QueenBehaviorStandard()));
        board.setBoard(b.getBoard());
        assertEquals(board.getBoard(),b.getBoard());
    }

}
