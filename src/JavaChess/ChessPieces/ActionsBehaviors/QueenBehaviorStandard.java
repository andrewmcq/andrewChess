package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class QueenBehaviorStandard implements ActionsBehavior{
    /**
     * returns all squares in straight directions and diagonals
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions;
        //make use of the rooks and bishops calculations for possible actions
        ActionsBehavior rook = new RookBehaviorStandard();
        ActionsBehavior bishop = new BishopBehaviorStandard();
        possibleActions = rook.actions(chessPiece,board);
        possibleActions.addAll(bishop.actions(chessPiece,board));
        return possibleActions;
    }
}
