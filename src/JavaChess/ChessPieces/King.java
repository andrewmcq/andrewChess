package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class King extends ChessPiece{
    public King() {
        super("king",null);
    }
    public King(ActionsBehavior actions) {
        super("king", actions);
    }
}
