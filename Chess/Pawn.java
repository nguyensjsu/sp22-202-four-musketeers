import greenfoot.*;

public class Pawn extends ChessPiece {
    int stepTwo;

    public Pawn(Boolean color) {
        super(color);
        setImage(color ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        //get the location of the click
        int mouseX = getMouseX();
        int mouseY = getMouseY();

        int x = getX();
        int y = getY();

        int delta_x = mouseX - x;
        int delta_y = mouseY - y;

        //only advancing
        if (mouseX == x) {
            //if first move allow 2 spaces. (need to check 2 spaces ahead to allow move)
            if (y == chessboard.DIM_Y - 2 && delta_y == -2) {
                //check if the 2 spaces in front of it are blank
                if (isTileEmpty(mouseX, mouseY) && isTileEmpty(mouseX, mouseY + 1)) {
                    stepTwo = chessboard.move;
                    move(mouseX, mouseY);
                }
            }
            //else allow for 1 move forward
            else if (delta_y == -1 && isTileEmpty(mouseX, mouseY)) {
                move(mouseX, mouseY);
            }
        }
        //capturing
        else if (Math.abs(delta_x) == 1 && delta_y == -1) {

            //case 1 capturing a standard piece
            //check if the space is not empty and if the piece there is not the same color
            if (!isTileEmpty(mouseX, mouseY) && getPieceColor(mouseX, mouseY) != color) {
                move(mouseX, mouseY);
            }
            //case 2 en passant
            else if (chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).size() != 0) {
                Pawn oppPawn = chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).get(0);
                if (color != oppPawn.color) {
                    if (oppPawn.stepTwo == chessboard.move - 1) {
                        move(mouseX, mouseY);
                        chessboard.removeObject(oppPawn);
                    }
                }
            }
        }
        ready = false;
    }
}
