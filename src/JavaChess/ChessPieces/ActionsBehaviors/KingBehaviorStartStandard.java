package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

/**
 * handles unmoved king behavior allowing for castling
 */
public class KingBehaviorStartStandard implements ActionsBehavior,StartBehavior{

    /**
     * returns all standard king moves as well as castling if applicable
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ActionsBehavior standardKing = new KingBehaviorStandard();
        ArrayList<String> actions = standardKing.actions(chessPiece,board);
        String pos = board.get(chessPiece).getPosition();
        //white castling
        if (pos.equals("E1")) {
            //castling long
            if (board.get("A1").getPiece().getActions() instanceof StartBehavior &&
                    !board.isOccupied("D1") && !board.isOccupied("C1") && !board.isOccupied("B1")) {
                actions.add("C1");
            }
            if (board.get("H1").getPiece().getActions() instanceof StartBehavior &&
                    !board.isOccupied("F1") && !board.isOccupied("G1")) {
                actions.add("G1");
            }
        }
        //black castling
        if (pos.equals("E8")) {
            //long
            if (board.get("A8").getPiece().getActions() instanceof StartBehavior &&
                    !board.isOccupied("D8") && !board.isOccupied("C8") && !board.isOccupied("B8")) {
                actions.add("C8");
            }
            //short
            if (board.get("H8").getPiece().getActions() instanceof StartBehavior &&
                    !board.isOccupied("F8") && !board.isOccupied("G8")) {
                actions.add("G8");
            }
        }
        return actions;
    }

    /**
     * upon moving the king, lose castling ability
     */
    @Override
    public void updateActions(ChessPiece piece) {
        piece.setActions(new KingBehaviorStandard());
    }
}
