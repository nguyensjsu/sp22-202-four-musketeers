import greenfoot.*;

public class Pawn extends ChessPiece {
    private int stepTwo;

    public Pawn(Boolean color) {
        super(color);
        setImage(color ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        int mouseX = getMouseX();
        int mouseY = getMouseY();
        int x = getX();
        int y = getY();
        int diffX = mouseX - x;
        int diffY = mouseY - y;

        if (isVerticalMove()) {
            if (y == chessboard.DIM_Y - 2 && diffY == -2 && isTileEmpty(mouseX, mouseY) && isTileEmpty(mouseX, mouseY + 1)) {
                // First move allow move 2 tiles, check if 2 tiles in front are empty
                stepTwo = chessboard.move;
                move(mouseX, mouseY);
            } else if (diffY == -1 && isTileEmpty(mouseX, mouseY)) {
                // Move 1 tile
                move(mouseX, mouseY);
            }
        } else if (Math.abs(diffX) == 1 && diffY == -1) {
            if (!isTileEmpty(mouseX, mouseY) && getPieceColor(mouseX, mouseY) != color) {
                // Normal capture
                move(mouseX, mouseY);
            } else if (!chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).isEmpty()) {
                // En passant
                Pawn oppPawn = chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).get(0);
                if (color != oppPawn.color && oppPawn.stepTwo == chessboard.move - 1) {
                    move(mouseX, mouseY);
                    chessboard.removeObject(oppPawn);
                }
            }
        }

        ready = false;
    }
}
