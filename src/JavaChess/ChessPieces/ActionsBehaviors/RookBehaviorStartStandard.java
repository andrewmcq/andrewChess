package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

/**
 * class only serves to differentiating from moved rooks for castling
 */
public class RookBehaviorStartStandard implements ActionsBehavior,StartBehavior{
    /**
     * returns normal rook behavior moves
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ActionsBehavior rook = new RookBehaviorStandard();
        return rook.actions(chessPiece,board);
    }

    /**
     * reclassifies action as non-start behavior
     */
    @Override
    public void updateActions(ChessPiece piece) {
        piece.setActions(new RookBehaviorStandard());
    }
}
