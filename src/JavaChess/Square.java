package JavaChess;

import JavaChess.ChessPieces.ChessPiece;

/**
 * Elements inside of board to keep the chess piece and the postion of the square grouped
 */
public class Square {
    private ChessPiece piece;
    private String position;

    /**
     * Initializes potiion and piece in the constructor, both can be null
     */
    public Square(ChessPiece piece, String position) {
        this.piece = piece;
        this.position = position;
    }

    //getters for private vars
    public ChessPiece getPiece() { return piece; }
    public String getPosition() { return position; }

    //setters for private vars
    public void setPiece(ChessPiece piece) { this.piece=piece; }
}
