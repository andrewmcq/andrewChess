package JavaChess;

import java.util.Locale;

import JavaChess.ChessPieces.*;

/**
 * Class to handle storing information in the board
 */
public class Board {
    private Square[][] board = new Square[8][8];

    /**
     * Initializes whole board to 64 squares with null pieces and regular chess coordinates
     */
    public Board() {
        String pos = "A1";
        for (int i=0; i<8; i++) {
            pos = Character.toString(pos.charAt(0))+ "1";
            for (int j=0; j<8; j++) {
                board[i][j] = new Square(null,pos);
                pos = Character.toString(pos.charAt(0))+  Character.toString(pos.charAt(1)+1);
            }
            pos = Character.toString(pos.charAt(0)+1)+  Character.toString(pos.charAt(1));
        }
    }

    /**
     * Makes a move by overwriting the piece on 1 square with a piece on another
     */
    public void makeMove(String move){
        String start = Character.toString(move.charAt(0)) + Character.toString(move.charAt(1)) ;
        String end = Character.toString(move.charAt(3)) + Character.toString(move.charAt(4)) ;
        this.get(end).setPiece(this.get(start).getPiece());
        this.get(start).setPiece(null);
    }

    /**
     * Method that takes in a chess coordinate and returns the Square in that place, returns null if no chess piece there
     */
    public Square get(String rc) {
        int[] coord = convertCoord(rc);
        return board[coord[0]][coord[1]];
    }
    /**
     * Method that takes in a chess Piece and returns the Square in that place, returns null if no chess piece there
     */
    public Square get(ChessPiece piece) {
        for(int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (board[i][j].getPiece() == piece) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Method that returns a board that has differnt memory for the board but is functionally the same
     */
    public Board copy() {
        Board newBoard = new Board();
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                newBoard.getBoard()[i][j].setPiece(board[i][j].getPiece());
            }
        }
        return newBoard;
    }

    /**
     * converts chess coordinates
     */
    public int[] convertCoord(String rc) {
        rc = rc.toUpperCase(Locale.ROOT);
        assert(rc.charAt(0) >='A' && rc.charAt(0)<='H');
        assert(rc.charAt(1) >='1' && rc.charAt(1)<='8');
        int [] coord = new int[2];
        coord[0] = rc.charAt(0) - 'A';
        coord[1] = Character.getNumericValue(rc.charAt(1))-1;
        return coord;
    }

    /**
     * tells if the inputted square is occupied
     */
    public boolean isOccupied(String pos) {
        if (this.get(pos).getPiece() != null) {
            return true;
        }
        return false;
    }

    //getters for private vars
    public Square[][] getBoard() { return board; }

    //setters for private vars
    public void setBoard(Square[][] board) { this.board = board; }
}
