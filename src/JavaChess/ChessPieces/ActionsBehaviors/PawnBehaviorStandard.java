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

        //checking En Passant
        String prevMove = board.getLastMove();
        //only true if last move was a double pawn move
        if (prevMove!=null&&prevMove.charAt(6)=='p' && java.lang.Math.abs(prevMove.charAt(1) - prevMove.charAt(4)) ==2){
            //checks if the en passant is 1 over from the column, and then is next to it
            if (java.lang.Math.abs(prevMove.charAt(0) - position.charAt(0))==1 && position.charAt(1) == prevMove.charAt(4)){
                possibleActions.add(Character.toString(prevMove.charAt(3))+Character.toString(y));
            }
        }
        return possibleActions;
    }
}
