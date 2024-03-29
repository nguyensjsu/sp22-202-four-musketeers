import greenfoot.*;

public class Knight extends ChessPiece {
    public Knight(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_knight.png" : "black_knight.png");
    }

    @Override
    protected IMoveSet getMoveSet() {
        return new KnightMoveSet(this);
    }
}
