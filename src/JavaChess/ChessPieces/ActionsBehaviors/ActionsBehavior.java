package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public interface ActionsBehavior {
    public ArrayList<String> actions(ChessPiece chessPiece, Board board);
}
