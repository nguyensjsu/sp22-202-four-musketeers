import greenfoot.*;

public class Queen extends ChessPiece {
    public Queen(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_queen.png" : "black_queen.png");
    }

    @Override
    protected void move() {
        if (ready && isClickedAnywhere()) {
            if ((isVerticalMove() || isHorizontalMove() || isDiagonalMove()) && isEmptyOrEnemy() && !isPathBlocked()) {
                move(getMouseX(), getMouseY());
            }
            ready = false;
        }
    }
}
