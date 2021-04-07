package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;
import JavaChess.ChessPieces.ChessPiece;

import java.util.ArrayList;


public class BishopBehaviorStandard implements ActionsBehavior {
    /**
     * returns every square on the board in the same diagonal as the piece up to the first occupied square
     */
    @Override
    public ArrayList<String> actions(ChessPiece chessPiece, Board board) {
        ArrayList<String> possibleActions = new ArrayList<String>();
        String position = board.get(chessPiece).getPosition();
        String action;
        char r = position.charAt(0);
        char c = position.charAt(1);
        // Grabs all diagonal coordinates to top right of the board
        while (r < 'H' && c < '8') {
            r+=1;
            c+=1;
            action = Character.toString(r)+Character.toString(c);
            possibleActions.add(action);
            if (board.isOccupied(action)) break;
        }
        r = position.charAt(0);
        c = position.charAt(1);
        //grabs all coordinates to the bottom right
        while (r < 'H' && c > '1') {
            r+=1;
            c-=1;
            action = Character.toString(r)+Character.toString(c);
            possibleActions.add(action);
            if (board.isOccupied(action)) break;
        }
        r = position.charAt(0);
        c = position.charAt(1);
        //grabs all coordinates to the bottom left
        while (r > 'A' && c > '1') {
            r-=1;
            c-=1;
            action = Character.toString(r)+Character.toString(c);
            possibleActions.add(action);
            if (board.isOccupied(action)) break;
        }
        r = position.charAt(0);
        c = position.charAt(1);
        //grabs all coordinates to the top left
        while (r > 'A' && c < '8') {
            r-=1;
            c+=1;
            action = Character.toString(r)+Character.toString(c);
            possibleActions.add(action);
            if (board.isOccupied(action)) break;
        }
        return possibleActions;
    }
}
