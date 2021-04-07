package JavaChess.ChessPieces.ActionsBehaviors;

import JavaChess.ChessPieces.ChessPiece;

/**
 * interface to identify any pieces that have behavior dependent on not moving
 */
public interface StartBehavior {
    /**
     * should update its action from a start behavior to the non start behavior
     */
    public void updateActions(ChessPiece piece);
}
