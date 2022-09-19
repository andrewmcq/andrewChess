package JavaChess.ChessPieces;

import JavaChess.Board;
import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

import java.util.ArrayList;

/**
 * Abstract class for all board pieces to extend
 */
public abstract class ChessPiece {
    private String name;
    private ActionsBehavior actions;

    /**
     * Initialize each chess piece with a name and behavior
     */
    public ChessPiece (String name,ActionsBehavior actions) {
        this.name = name;
        this.actions = actions;
    }

    public ArrayList<String> candidateMoves(Board board) {
        return actions.actions(this,board);
    }

    //setters for private vars
    public void setActions(ActionsBehavior actions) { this.actions = actions; }

    //getters for private vars
    public ActionsBehavior getActions() { return actions; }
    public String getName() { return name; }


}
