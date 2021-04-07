package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class Queen extends ChessPiece{
    public Queen() {
        super("queen",null);
    }
    public Queen(ActionsBehavior actions) {
        super("queen", actions);
    }
}
