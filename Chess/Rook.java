import greenfoot.*;

public class Rook extends ChessPiece {
    public Rook(Boolean color) {
        super(color);
        setImage(color ? "white_rook.png" : "black_rook.png");
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
