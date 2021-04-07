package JavaChess.ChessPieces;

import JavaChess.ChessPieces.ActionsBehaviors.ActionsBehavior;

public class Knight extends ChessPiece{
    public Knight() {
        super("knight",null);
    }
    public Knight(ActionsBehavior actions) {
        super("knight",actions);
    }
}
