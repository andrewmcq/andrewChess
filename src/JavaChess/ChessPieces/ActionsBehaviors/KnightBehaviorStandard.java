package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class KnightBehaviorStandard implements ActionsBehavior{
    /**
     * returns every square on the board in the same diagonal as the piece up to the first occupied square
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = new ArrayList<String>();
        String position = board.get(chessPiece).getPosition();

        //moves 2 spaces above knight
        if (position.charAt(1)+2 <= '8') {
            //1 to the right
            if (position.charAt(0)+1<='H') possibleActions.add(Character.toString(position.charAt(0)+1)+ Character.toString(position.charAt(1)+2));
            //1 to the left
            if (position.charAt(0)-1>='A') possibleActions.add(Character.toString(position.charAt(0)-1)+ Character.toString(position.charAt(1)+2));
        }
        //moves 2 spaces below knight
        if (position.charAt(1)-2 >= '1') {
            //1 to the right
            if (position.charAt(0)+1<='H') possibleActions.add(Character.toString(position.charAt(0)+1)+ Character.toString(position.charAt(1)-2));
            //1 to the left
            if (position.charAt(0)-1>='A') possibleActions.add(Character.toString(position.charAt(0)-1)+ Character.toString(position.charAt(1)-2));
        }
        //moves 2 spaces to the right of the knight
        if (position.charAt(0)+2 <= 'H') {
            //1 above
            if (position.charAt(1)+1<='8') possibleActions.add(Character.toString(position.charAt(0)+2)+ Character.toString(position.charAt(1)+1));
            //1 below
            if (position.charAt(1)-1>='1') possibleActions.add(Character.toString(position.charAt(0)+2)+ Character.toString(position.charAt(1)-1));
        }
        //moves 2 spaces to the left of the knight
        if (position.charAt(0)-2 >= 'A') {
            //1 above
            if (position.charAt(1)+1<='8') possibleActions.add(Character.toString(position.charAt(0)-2)+ Character.toString(position.charAt(1)+1));
            //1 below
            if (position.charAt(1)-1>='1') possibleActions.add(Character.toString(position.charAt(0)-2)+ Character.toString(position.charAt(1)-1));
        }
        return possibleActions;
    }
}
