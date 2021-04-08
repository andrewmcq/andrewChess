package Test;
import JavaChess.*;

import JavaChess.ChessPieces.ActionsBehaviors.*;
import JavaChess.ChessPieces.ChessPiece;
import JavaChess.ChessPieces.*;
import JavaChess.SetUp.StandardGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;
    private Player opponent;

    /**
     * Initializes each test with a player pointing to an opponent with empty pieces and an empty board
     */
    @BeforeEach
    public void setUp () throws Exception {
        Board board = new Board();

        player = new Player();
        opponent = new Player();

        player.setBoard(board);
        opponent.setBoard(board);

        player.setOpponent(opponent);
        opponent.setOpponent(player);
    }

    /**
     * Tests constructor works correctly
     */
    @Test
    public void testConstructor() {
        assertEquals(0,player.getPieces().size());
    }

    /**
     * tests adding an array of pieces to the player list at once
     */
    @Test
    public void testAddPieces() {
        ChessPiece[] pieces = new ChessPiece[3];
        pieces[0] = new King();
        pieces[1] = new Pawn();
        pieces[2] = new Queen();
        player.addPieces(pieces);
        assertEquals(pieces[0],player.getPieces().get(0));
        assertEquals(pieces[1],player.getPieces().get(1));
        assertEquals(pieces[2],player.getPieces().get(2));
        ChessPiece[] pieces2 = new ChessPiece[1];
        pieces2[0] = new Pawn();
        player.addPieces(pieces2);
        assertEquals(pieces2[0],player.getPieces().get(3));
    }

    /**
     * testing that a player with any number of given pieces will return every legal move they have the option of making
     */
    @Test
    public void testGetAvailableMoves() {
        ArrayList<String> moves;
        //setting up a board with a king, a queen, then an opposing queen, and a opposing king in a vertical line
        ChessPiece[] p = {new King(new KingBehaviorStandard()), new Queen(new QueenBehaviorStandard())};
        ChessPiece[] o = {new King(new KingBehaviorStandard()), new Queen(new QueenBehaviorStandard())};
        player.addPieces(p);
        opponent.addPieces(o);
        player.getBoard().get("E3").setPiece(p[0]);
        player.getBoard().get("E4").setPiece(p[1]);
        player.getBoard().get("E5").setPiece(o[1]);
        player.getBoard().get("E6").setPiece(o[0]);
        moves = player.getAvailableMoves();
        //the queen should have only one move, and the other 7 should be king moves(ie starting with E3)
        assertEquals(6,moves.size());
        assertTrue(moves.contains("E3:D2"));
        assertTrue(moves.contains("E3:E2"));
        assertTrue(moves.contains("E3:F2"));
        assertTrue(moves.contains("E3:D3"));
        assertTrue(moves.contains("E3:F3"));
        assertTrue(moves.contains("E4:E5"));

        // putting a king in the corner with only 1 available move
        Board b = new Board();
        player.setBoard(b);
        opponent.setBoard(b);
        player.update();
        opponent.update();
        ChessPiece[] p1 = {new King(new KingBehaviorStandard())};
        ChessPiece[] p2 = {new King(new KingBehaviorStandard()), new Rook(new RookBehaviorStandard()), new Rook(new RookBehaviorStandard())};
        player.addPieces(p1);
        opponent.addPieces(p2);
        player.getBoard().get("A1").setPiece(p1[0]);
        player.getBoard().get("H8").setPiece(p2[0]);
        player.getBoard().get("B8").setPiece(p2[1]);
        player.getBoard().get("H3").setPiece(p2[2]);
        //Only move should be A2
        moves = player.getAvailableMoves();
        assertTrue(moves.contains("A1:A2"));
        assertEquals(1,moves.size());
    }

    @Test
    public void testAvailableMovesWithSetup() {
        Game g = new Game();
        g.setUp(new StandardGame());
        player = g.getPlayers()[0];
        opponent = g.getPlayers()[1];
        ArrayList<String> moves = player.getAvailableMoves();
        //pawn moves
        assertTrue(moves.contains("A2:A3"));
        assertTrue(moves.contains("A2:A4"));
        assertTrue(moves.contains("B2:B3"));
        assertTrue(moves.contains("B2:B4"));
        assertTrue(moves.contains("C2:C3"));
        assertTrue(moves.contains("C2:C4"));
        assertTrue(moves.contains("D2:D3"));
        assertTrue(moves.contains("D2:D4"));
        assertTrue(moves.contains("E2:E3"));
        assertTrue(moves.contains("E2:E4"));
        assertTrue(moves.contains("F2:F3"));
        assertTrue(moves.contains("F2:F4"));
        assertTrue(moves.contains("G2:G3"));
        assertTrue(moves.contains("G2:G4"));
        assertTrue(moves.contains("H2:H3"));
        assertTrue(moves.contains("H2:H4"));
        //knight moves
        assertTrue(moves.contains("B1:A3"));
        assertTrue(moves.contains("B1:C3"));
        assertTrue(moves.contains("G1:H3"));
        assertTrue(moves.contains("G1:F3"));
        assertEquals(20,moves.size());
        player.playMove("E2:E4");
        assertTrue(opponent.getAvailableMoves().contains("D7:D5"));
        opponent.playMove("D7:D5");
        moves = player.getAvailableMoves();
        //pawn moves
        assertTrue(moves.contains("A2:A3"));
        assertTrue(moves.contains("A2:A4"));
        assertTrue(moves.contains("B2:B3"));
        assertTrue(moves.contains("B2:B4"));
        assertTrue(moves.contains("C2:C3"));
        assertTrue(moves.contains("C2:C4"));
        assertTrue(moves.contains("D2:D3"));
        assertTrue(moves.contains("D2:D4"));
        assertTrue(moves.contains("E4:E5"));
        assertTrue(moves.contains("E4:D5")); //pawn takes pawn
        assertTrue(moves.contains("F2:F3"));
        assertTrue(moves.contains("F2:F4"));
        assertTrue(moves.contains("G2:G3"));
        assertTrue(moves.contains("G2:G4"));
        assertTrue(moves.contains("H2:H3"));
        assertTrue(moves.contains("H2:H4"));
        //knight moves
        assertTrue(moves.contains("B1:A3"));
        assertTrue(moves.contains("B1:C3"));
        assertTrue(moves.contains("G1:H3"));
        assertTrue(moves.contains("G1:F3"));
        assertTrue(moves.contains("G1:E2"));
        // king move
        assertTrue(moves.contains("E1:E2"));
        //queen moves
        assertTrue(moves.contains("D1:E2"));
        assertTrue(moves.contains("D1:F3"));
        assertTrue(moves.contains("D1:G4"));
        assertTrue(moves.contains("D1:H5"));
        //bishop moves
        assertTrue(moves.contains("F1:E2"));
        assertTrue(moves.contains("F1:D3"));
        assertTrue(moves.contains("F1:C4"));
        assertTrue(moves.contains("F1:B5"));
        assertTrue(moves.contains("F1:A6"));
        assertEquals(31,moves.size());
        player.playMove("F1:B5");

        // opponent in check
        moves = opponent.getAvailableMoves();
        //pawn move
        assertTrue(moves.contains("C7:C6"));
        //knight moves
        assertTrue(moves.contains("B8:C6"));
        assertTrue(moves.contains("B8:D7"));
        //bishop move
        assertTrue(moves.contains("C8:D7"));
        //queen move
        assertTrue(moves.contains("D8:D7"));
        assertEquals(5,moves.size());



    }

    /**
     * tests isValidMove function to ensure that it correctly filters possible moves
     */
    @Test
    public void testSelfCheck() {
        //setting a king a queen in a vertical line, then an enemy queen and king in a vertical line, pinning the queens so the valid moves should only be king
        //or queen takes queen
        ChessPiece[] p = {new King(new KingBehaviorStandard()), new Queen(new QueenBehaviorStandard())};
        ChessPiece[] o = {new King(new KingBehaviorStandard()), new Queen(new QueenBehaviorStandard())};
        player.addPieces(p);
        opponent.addPieces(o);
        player.getBoard().get("E3").setPiece(p[0]);
        player.getBoard().get("E4").setPiece(p[1]);
        player.getBoard().get("E5").setPiece(o[1]);
        player.getBoard().get("E6").setPiece(o[0]);
        assertTrue(player.selfCheck("E3:D3"));
        assertTrue(player.selfCheck("E4:E5"));
        assertFalse(player.selfCheck("E3:D4"));
        assertFalse(player.selfCheck("E3:F4"));
        assertFalse(player.selfCheck("E4:B4"));
        assertFalse(player.selfCheck("E4:D3"));
    }

    /**
     * testing for properly filtering out illegal castling
     */
    @Test
    public void testSelfCheckCastling() {
        ChessPiece[] p = {new King(new KingBehaviorStartStandard()), new Rook(new RookBehaviorStartStandard())};
        ChessPiece[] o = {new Rook(new RookBehaviorStandard())};
        player.addPieces(p);
        opponent.addPieces(o);
        player.getBoard().get("E1").setPiece(p[0]);
        player.getBoard().get("A1").setPiece(p[1]);
        player.getBoard().get("A8").setPiece(o[0]);
        //King does not pass through attacks so should be able to castle
        assertTrue(player.selfCheck("E1:C1"));
        player.getBoard().get("A8").setPiece(null);
        player.getBoard().get("B8").setPiece(o[0]);
        assertTrue(player.selfCheck("E1:C1"));
        //should not be able to castle now that will castle into a check
        player.getBoard().get("B8").setPiece(null);
        player.getBoard().get("C8").setPiece(o[0]);
        assertFalse(player.selfCheck("E1:C1"));
        player.getBoard().get("C8").setPiece(null);
        player.getBoard().get("D8").setPiece(o[0]);
        assertFalse(player.selfCheck("E1:C1"));
        player.getBoard().get("D8").setPiece(null);
        player.getBoard().get("E8").setPiece(o[0]);
        assertFalse(player.selfCheck("E1:C1"));

        //testing options in the top right
        player.setBoard(new Board());
        opponent.setBoard(player.getBoard());
        player.getBoard().get("E8").setPiece(p[0]);
        player.getBoard().get("H8").setPiece(p[1]);
        player.getBoard().get("H1").setPiece(o[0]);
        //Rooks aligned should be able to castle right
        assertTrue(player.selfCheck("E8:G8"));
        //Rook no attacks king squares passing through
        player.getBoard().get("H1").setPiece(null);
        player.getBoard().get("G1").setPiece(o[0]);
        assertFalse(player.selfCheck("E8:G8"));
        player.getBoard().get("G1").setPiece(null);
        player.getBoard().get("F1").setPiece(o[0]);
        assertFalse(player.selfCheck("E8:G8"));
        player.getBoard().get("F1").setPiece(null);
        player.getBoard().get("E1").setPiece(o[0]);
        assertFalse(player.selfCheck("E8:G8"));

    }

    /**
     * makes sure update removes any elements from players pieces that are no longer on the board
     */
    @Test
    public void testUpdate() {
        ChessPiece[] p = {new King(new KingBehaviorStandard()), new Queen(new QueenBehaviorStandard())};
        //testing removing all pieces because they were not placed
        player.addPieces(p);
        player.update();
        assertEquals(0,player.getPieces().size());

        //testing removing only 1 piece as the other was placed
        player.addPieces(p);
        Board b = new Board();
        player.setBoard(b);
        b.get("A1").setPiece(p[1]);
        player.update();
        assertEquals(1,player.getPieces().size());
        assertTrue(player.getPieces().contains(p[1]));

        //testing having a piece on the board and then removing it
        b.get("A1").setPiece(new Pawn(new PawnBehaviorStandard(false)));
        player.update();
        assertEquals(0,player.getPieces().size());
    }

    /**
     * Makes sure maing a moves properly calls the board function, updates the opponent, and updates any pieces with starting behaviors
     */
    @Test
    public void testPlayMove() {
        //setting a king and a pawn, with   king in the back rank, and a pawn right in front
        ChessPiece[] w = {new King(new KingBehaviorStandard()), new Pawn(new PawnBehaviorStartStandard(false))};
        ChessPiece[] b = {new King(new KingBehaviorStandard()), new Pawn(new PawnBehaviorStartStandard(true))};
        player.addPieces(w);
        opponent.addPieces(b);
        player.getBoard().get("E1").setPiece(w[0]);
        player.getBoard().get("E2").setPiece(w[1]);
        player.getBoard().get("E8").setPiece(b[0]);
        player.getBoard().get("E7").setPiece(b[1]);

        //checking that making the move flips the behavior
        assertTrue(w[1].getActions() instanceof StartBehavior);
        player.playMove("E2:E4");
        assertFalse(w[1].getActions() instanceof StartBehavior);
        //making sure the board changed
        assertNull(player.getBoard().get("E2").getPiece());
        assertEquals(w[1],player.getBoard().get("E4").getPiece());

        //testing that a move without a start behavior also works
        player.playMove("E4:E5");
        assertFalse(w[1].getActions() instanceof StartBehavior);
    }

    /**
     * tests playing a move that is a valid castle moves the rook
     */
    @Test
    public void testPlayMoveCastle() {
        ChessPiece[] w = {new King(new KingBehaviorStartStandard()), new Rook(new RookBehaviorStartStandard()), new Rook(new RookBehaviorStartStandard())};
        ChessPiece[] b = {new King(new KingBehaviorStartStandard()), new Rook(new RookBehaviorStartStandard()), new Rook(new RookBehaviorStartStandard())};
        player.addPieces(w);
        opponent.addPieces(b);
        player.getBoard();
        player.getBoard().get("E1").setPiece(w[0]);
        player.getBoard().get("E8").setPiece(b[0]);
        player.getBoard().get("A1").setPiece(w[1]);
        player.getBoard().get("A8").setPiece(b[1]);
        player.getBoard().get("H1").setPiece(w[2]);
        player.getBoard().get("H8").setPiece(b[2]);
        //testing a long castle for white
        assertTrue(w[0].getActions() instanceof StartBehavior);
        assertTrue(w[1].getActions() instanceof StartBehavior);
        assertTrue(w[2].getActions() instanceof StartBehavior);
        player.playMove("E1:C1");
        assertEquals("C1",player.getBoard().get(w[0]).getPosition());
        assertEquals("D1",player.getBoard().get(w[1]).getPosition());
        assertFalse(w[0].getActions() instanceof StartBehavior);
        assertFalse(w[1].getActions() instanceof StartBehavior);
        assertTrue(w[2].getActions() instanceof StartBehavior);

        //testing a shot castle for black
        assertTrue(b[0].getActions() instanceof StartBehavior);
        assertTrue(b[1].getActions() instanceof StartBehavior);
        assertTrue(b[2].getActions() instanceof StartBehavior);
        opponent.playMove("E8:G8");
        assertEquals("G8",player.getBoard().get(b[0]).getPosition());
        assertEquals("F8",player.getBoard().get(b[2]).getPosition());
        assertFalse(b[0].getActions() instanceof StartBehavior);
        assertTrue(b[1].getActions() instanceof StartBehavior);
        assertFalse(b[2].getActions() instanceof StartBehavior);
    }

    /**
     * testing pawn promotion
     */
    @Test
    public void testPlayMovePawnPromotion() {
        ChessPiece[] w = {new King(new KingBehaviorStartStandard()), new Pawn(new PawnBehaviorStandard(false))};
        ChessPiece[] b = {new King(new KingBehaviorStartStandard()), new Pawn(new PawnBehaviorStandard(true))};
        player.addPieces(w);
        opponent.addPieces(b);
        player.getBoard().get("A7").setPiece(w[1]);
        opponent.getBoard().get("H2").setPiece(b[1]);

        //whites pawn promotion
        assertEquals(w[1], player.getBoard().get("A7").getPiece());
        assertNull(player.getBoard().get("A8").getPiece());
        player.playMove("A7:A8");
        ChessPiece result = player.getBoard().get("A8").getPiece();
        assertTrue(player.getPieces().contains(result));
        assertTrue(result instanceof Queen);
        assertTrue(result.getActions() instanceof QueenBehaviorStandard);
        assertFalse(player.getPieces().contains(w[1]));

        //blacks pawn promotion
        assertEquals(b[1], player.getBoard().get("H2").getPiece());
        assertNull(player.getBoard().get("H1").getPiece());
        opponent.playMove("H2:H1");
        result = player.getBoard().get("H1").getPiece();
        assertTrue(opponent.getPieces().contains(result));
        assertTrue(result instanceof Queen);
        assertTrue(result.getActions() instanceof QueenBehaviorStandard);
        assertFalse(player.getPieces().contains(b[1]));

    }
}
