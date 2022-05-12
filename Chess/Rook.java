import greenfoot.*;

public class Rook extends ChessPiece {
    public Rook(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_rook.png" : "black_rook.png");
    }

    @Override
    protected MoveSet getMoveSet() {
        return new RookMoveSet(this);
    }
}
