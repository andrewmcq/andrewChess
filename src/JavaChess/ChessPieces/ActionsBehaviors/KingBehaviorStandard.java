package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class KingBehaviorStandard implements ActionsBehavior{
    /**
     * returns every square on the board in the same diagonal as the piece up to the first occupied square
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = new ArrayList<String>();
        String position = board.get(chessPiece).getPosition();
        //3 squares above
        if (position.charAt(1)+1<='8') {
            for (int i = -1; i < 2; i++) {
                if (position.charAt(0)+i <='H' && position.charAt(0)+i>='A') {
                    possibleActions.add(Character.toString(position.charAt(0)+i)+Character.toString(position.charAt(1)+1));
                }
            }
        }
        //3 squares below
        if (position.charAt(1)-1>='1') {
            for (int i = -1; i < 2; i++) {
                if (position.charAt(0)+i <='H' && position.charAt(0)+i>='A') {
                    possibleActions.add(Character.toString(position.charAt(0)+i)+Character.toString(position.charAt(1)-1));
                }
            }
        }
        //1 left side
        if (position.charAt(0)-1 >= 'A') {
            possibleActions.add(Character.toString(position.charAt(0)-1)+ Character.toString(position.charAt(1)));
        }
        //1 right side
        if (position.charAt(0)+1 <= 'H') {
            possibleActions.add(Character.toString(position.charAt(0)+1)+ Character.toString(position.charAt(1)));
        }
        return possibleActions;
    }
}
