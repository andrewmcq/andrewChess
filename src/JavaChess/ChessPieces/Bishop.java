package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class Bishop extends ChessPiece{
    public Bishop() {
        super("bishop",null);
    }
    public Bishop(ActionsBehavior actions) {
        super("bishop",actions);
    }
}
