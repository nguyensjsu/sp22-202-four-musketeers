import greenfoot.*;

public class Queen extends ChessPiece {
    public Queen(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_queen.png" : "black_queen.png");
    }

    @Override
    protected IMoveSet getMoveSet() {
        return new QueenMoveSet(this);
    }
}
