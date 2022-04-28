import greenfoot.*;

public class Rook extends ChessPiece {
    public Rook(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_rook.png" : "black_rook.png");
    }

    @Override
    protected void move() {
        if (ready && isClickedAnywhere()) {
            if ((isVerticalMove() || isHorizontalMove()) && isEmptyOrEnemy() && !isPathBlocked()) {
                move(getMouseX(), getMouseY());
            }
            ready = false;
        }
    }
}
