package Test;

import JavaChess.*;
import JavaChess.ChessPieces.ActionsBehaviors.KingBehaviorStartStandard;
import JavaChess.ChessPieces.ActionsBehaviors.PawnBehaviorStartStandard;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.King;
import JavaChess.ChessPieces.Pawn;
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

    //Testing that the game doesn't return any playable moves one a threefold repetition occurs resulting in a draw
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

    //Testing that after 50 consecutive moves with no pawn move or captures results in a draw
    //a move is designated as both players completing a consecutive turn
    @Test
    void testFiftyMoveRule(){
        //setting a board with only kings placed on e1 and e8
        game = new Game();
        Player[] players = game.getPlayers();
        players[0] = new Player();
        players[1] = new Player();
        players[0].setOpponent(players[1]);
        players[1].setOpponent(players[0]);
        Board board = new Board();
        players[0].setBoard(board);
        players[1].setBoard(board);
        ChessPiece[] whiteKing = new ChessPiece[1];
        whiteKing[0] = new King(new KingBehaviorStartStandard());
        ChessPiece[] blackKing = new ChessPiece[1];
        blackKing[0] = new King(new KingBehaviorStartStandard());
        players[0].addPieces(whiteKing);
        players[1].addPieces(blackKing);
        board.get("E1").setPiece(whiteKing[0]);
        board.get("E8").setPiece(blackKing[0]);

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");

        // ten moves above this

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");

        //20 moves above this

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");

        //30 moves above

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");

        //40 moves above

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");

        //50 moves above, now get moves should be empty.
        assertEquals(0,game.getMoves().size());
    }

    //Testing that after 48 consecutive moves with a pawn capture resets the count
    //a move is designated as both players completing a consecutive turn
    @Test
    void testFiftyMoveRuleReset(){
        //setting a board with only kings placed on e1 and e8, and a pawn at e2
        game = new Game();
        Player[] players = game.getPlayers();
        players[0] = new Player();
        players[1] = new Player();
        players[0].setOpponent(players[1]);
        players[1].setOpponent(players[0]);
        Board board = new Board();
        players[0].setBoard(board);
        players[1].setBoard(board);
        ChessPiece[] whiteKing = new ChessPiece[2];
        whiteKing[0] = new King(new KingBehaviorStartStandard());
        whiteKing[1]= new Pawn(new PawnBehaviorStartStandard(false));
        ChessPiece[] blackKing = new ChessPiece[1];
        blackKing[0] = new King(new KingBehaviorStartStandard());
        players[0].addPieces(whiteKing);
        players[1].addPieces(blackKing);
        board.get("E1").setPiece(whiteKing[0]);
        board.get("E2").setPiece(whiteKing[1]);
        board.get("E8").setPiece(blackKing[0]);

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");

        // ten moves above this

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");

        //20 moves above this

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");

        //30 moves above

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H5:H6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H6:H7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A4:A3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H7:H8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A3:A2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("H8:G8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A2:A1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("G8:F8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("A1:B1");

        //40 moves above

        assertNotEquals(0,game.getMoves().size());
        game.playMove("F8:E8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("B1:C1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E8:D8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("C1:D1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("D8:C8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("D1:E1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("C8:B8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("E1:F1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("B8:A8");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("F1:G1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A8:A7");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("G1:H1");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A7:A6");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H1:H2");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A6:A5");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H2:H3");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("A5:A4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H3:H4");

        assertNotEquals(0,game.getMoves().size());
        game.playMove("E2:E4");
        assertNotEquals(0,game.getMoves().size());
        game.playMove("H4:H5");

        //50 moves above, now get moves should be empty.
        assertNotEquals(0,game.getMoves().size());
    }
}
