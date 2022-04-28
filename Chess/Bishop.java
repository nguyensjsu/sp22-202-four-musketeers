import greenfoot.*;

public class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_bishop.png" : "black_bishop.png");
    }

    @Override
    protected void move() {
        if (ready && isClickedAnywhere()) {
            if (isDiagonalMove() && isEmptyOrEnemy() && !isPathBlocked()) {
                move(getMouseX(), getMouseY());
            }
            ready = false;
        }
    }
}
