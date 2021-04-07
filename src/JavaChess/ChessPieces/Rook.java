package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class Rook extends ChessPiece{
    public Rook() {
        super("rook",null);
    }
    public Rook(ActionsBehavior actions) {
        super("rook",actions);
    }
}
