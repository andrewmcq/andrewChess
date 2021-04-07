package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class Pawn extends ChessPiece{
    public Pawn() {
        super("pawn",null);
    }
    public Pawn(ActionsBehavior actions) {
        super("pawn",actions);
    }
}
