import greenfoot.*;

public class Super extends ChessPiece {
    public Super(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_super.png" : "black_super.png");
    }

    @Override
    protected IMoveSet getMoveSet() {
        return new SuperMoveSet(this);
    }
}
