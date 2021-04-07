package JavaChess.SetUp;

import JavaChess.Board;
import JavaChess.ChessPieces.*;
import JavaChess.ChessPieces.ActionsBehaviors.*;
import JavaChess.Game;
import JavaChess.Player;
import JavaChess.Square;

/**
 * Class to handle setting up standard chess matches
 */
public class StandardGame implements SetUp {
    /**
     * method to set up standard match properly
     */
    @Override
    public void setUpGame(Game game) {
        Player[] players = game.getPlayers();
        Board board = new Board();
        ChessPiece[][] pieces = new ChessPiece[2][16];
        //players[0] = white
        //players[1] = black
        boolean isBlack = false;
        // initializing the players with their 16 pieces, with kings in the 0 list index.
        for(int i=0;i<2;i++){
            players[i] = new Player();
            for (int j=0;j<16;j++){
                if (j>7){
                    if(i==1) isBlack = true;
                    pieces[i][j] = new Pawn(new PawnBehaviorStartStandard(isBlack));
                }
                else if (j>5) {
                    pieces[i][j] = new Rook(new RookBehaviorStartStandard());
                }
                else if (j>3) {
                    pieces[i][j] = new Knight(new KnightBehaviorStandard());
                }
                else if (j>1) {
                    pieces[i][j] = new Bishop(new BishopBehaviorStandard());
                }
                else if (j>0) {
                    pieces[i][j] = new Queen(new QueenBehaviorStandard());
                }
                else {
                    pieces[i][j] = new King(new KingBehaviorStartStandard());
                }
            }
            players[i].addPieces(pieces[i]);
            players[i].setBoard(board);
        }
        //setting players as their opponents
        players[0].setOpponent(players[1]);
        players[1].setOpponent(players[0]);

        //putting pawns on the correct board placement
        Square[][] b = board.getBoard();
        for (int i=15; i>7; i--){
            b[i-8][1].setPiece(pieces[0][i]);
            b[i-8][6].setPiece(pieces[1][i]);
        }
        //placing rooks
        b[0][0].setPiece(pieces[0][6]);
        b[7][0].setPiece(pieces[0][7]);
        b[0][7].setPiece(pieces[1][6]);
        b[7][7].setPiece(pieces[1][7]);
        //placing knights
        b[1][0].setPiece(pieces[0][4]);
        b[6][0].setPiece(pieces[0][5]);
        b[1][7].setPiece(pieces[1][4]);
        b[6][7].setPiece(pieces[1][5]);
        //placing bishops
        b[2][0].setPiece(pieces[0][3]);
        b[5][0].setPiece(pieces[0][2]);
        b[2][7].setPiece(pieces[1][3]);
        b[5][7].setPiece(pieces[1][2]);
        //placing queens
        b[3][0].setPiece(pieces[0][1]);
        b[3][7].setPiece(pieces[1][1]);
        //placing kings
        b[4][0].setPiece(pieces[0][0]);
        b[4][7].setPiece(pieces[1][0]);
    }
}
