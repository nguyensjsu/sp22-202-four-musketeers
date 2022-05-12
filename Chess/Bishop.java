import greenfoot.*;

public class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_bishop.png" : "black_bishop.png");
    }

    @Override
    protected MoveSet getMoveSet() {
        return new BishopMoveSet(this);
    }
}
