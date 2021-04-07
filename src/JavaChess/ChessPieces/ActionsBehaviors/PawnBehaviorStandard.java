package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class PawnBehaviorStandard implements ActionsBehavior {
    private boolean black;
    public PawnBehaviorStandard(boolean black) {
        this.black = black;
    }

    /**
     * returns all 3 squares above the pawns allowed direction.
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = new ArrayList<String>();
        String position = board.get(chessPiece).getPosition();
        int increment = 0;
        if (black) {
            increment = -1;
        }
        else {
            increment = 1;
        }
        char x = position.charAt(0);
        char y = (char) (position.charAt(1) + increment);
        x-=1;
        String newPos = Character.toString(x) + Character.toString(y);
        //if there is a piece to diagonally overtake
        if (x >= 'A' && x <= 'H' && y >= '1' && y <= '8' && board.isOccupied(newPos)) {
            possibleActions.add(newPos);
        }
        x+=1;
        newPos = Character.toString(x) + Character.toString(y);
        //just moving forward
        if (x >= 'A' && x <= 'H' && y >= '1' && y <= '8' && !board.isOccupied(newPos)) {
            possibleActions.add(newPos);
        }
        x+=1;
        newPos = Character.toString(x) + Character.toString(y);
        //if there is a piece to diagonally overtake
        if (x >= 'A' && x <= 'H' && y >= '1' && y <= '8' && board.isOccupied(newPos)) {
            possibleActions.add(newPos);
        }
        return possibleActions;
    }
}
