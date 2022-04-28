import greenfoot.*;

public class Queen extends ChessPiece {
    public Queen(Boolean color) {
        super(color);
        setImage(color ? "white_queen.png" : "black_queen.png");
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
