package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;

public class RookBehaviorStandard implements ActionsBehavior{
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = new ArrayList<String>();
        String position = board.get(chessPiece).getPosition();
        String newPos = position;
        //Getting all the possible moves above the piece
        while (newPos.charAt(1) < '8') {
            newPos = Character.toString(newPos.charAt(0)) + Character.toString(newPos.charAt(1)+1);
            possibleActions.add(newPos);
            if (board.isOccupied(newPos)) break;
        }

        //getting possible moves below piece
        newPos = position;
        while (newPos.charAt(1) > '1') {
            newPos = Character.toString(newPos.charAt(0)) + Character.toString(newPos.charAt(1)-1);
            possibleActions.add(newPos);
            if (board.isOccupied(newPos)) break;
        }

        //getting moves to the right
        newPos = position;
        while (newPos.charAt(0) < 'H') {
            newPos = Character.toString(newPos.charAt(0)+1) + Character.toString(newPos.charAt(1));
            possibleActions.add(newPos);
            if (board.isOccupied(newPos)) break;
        }

        //getting moves to the left
        newPos = position;
        while (newPos.charAt(0) > 'A') {
            newPos = Character.toString(newPos.charAt(0)-1) + Character.toString(newPos.charAt(1));
            possibleActions.add(newPos);
            if (board.isOccupied(newPos)) break;
        }

        return possibleActions;
    }
}
