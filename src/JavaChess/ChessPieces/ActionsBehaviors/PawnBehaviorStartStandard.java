package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;
import JavaChess.ChessPieces.ActionsBehaviors.PawnBehaviorStandard;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class PawnBehaviorStartStandard implements ActionsBehavior, StartBehavior{
    private boolean black;
    private ActionsBehavior standardPawn;
    public PawnBehaviorStartStandard(boolean black) {
        this.black = black;
        standardPawn = new PawnBehaviorStandard(black);
    }

    /**
     * open square above the pawn, and the diagonal adjacent squares if a piece is on them.
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = standardPawn.actions(chessPiece,board);
        String position = board.get(chessPiece).getPosition();
        int mult;
        if (black) {
            mult = -1;
        }
        else {
            mult = 1;
        }
        String move = Character.toString(position.charAt(0)) + Character.toString(position.charAt(1)+ (2*mult));
        if (!board.isOccupied(move)) possibleActions.add(move);
        return possibleActions;
    }

    @Override
    public void updateActions(ChessPiece piece) {
        piece.setActions(standardPawn);
    }
}
