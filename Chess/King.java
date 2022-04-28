import greenfoot.*;

import java.util.List;

public class King extends ChessPiece {
    public King(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_king.png" : "black_king.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        int mouseX = getMouseX();
        int mouseY = getMouseY();
        int absDiffX = Math.abs(mouseX - getX());
        int absDiffY = Math.abs(mouseY - getY());

        if (isEmptyOrEnemy()) {
            if (absDiffX <= 1 && absDiffY <= 1) {
                move(mouseX, mouseY);
            } else if (!moved) {
                if (isWhite) {
                    castleWhite(mouseX, mouseY);
                } else {
                    castleBlack(mouseX, mouseY);
                }
            }
        }

        ready = false;
    }

    private void castleWhite(int mouseX, int mouseY) {
        int y = Chessboard.DIM_Y - 1;
        if (mouseX == 2 && mouseY == y) {
            // Castle left
            if (isTileEmpty(1, y) && isTileEmpty(2, y) && isTileEmpty(3, y)) {
                List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                    move(mouseX, mouseY);
                    leftRook.get(0).setLocation(3, y);
                }
            }
        } else if (mouseX == 6 && mouseY == y) {
            // Castle right
            if (isTileEmpty(5, y) && isTileEmpty(6, y)) {
                List<Rook> rightRook = chessboard.getObjectsAt(7, y, Rook.class);
                if (!rightRook.isEmpty() && !rightRook.get(0).moved) {
                    move(mouseX, mouseY);
                    rightRook.get(0).setLocation(5, y);
                }
            }
        }
    }

    private void castleBlack(int mouseX, int mouseY) {
        int y = Chessboard.DIM_Y - 1;
        if (mouseX == 1 && mouseY == y) {
            // Castle left
            if (isTileEmpty(1, y) && isTileEmpty(2, y)) {
                List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                    move(mouseX, mouseY);
                    leftRook.get(0).setLocation(2, y);
                }
            }
        } else if (mouseX == 5 && mouseY == y) {
            // Castle right
            if (isTileEmpty(4, y) && isTileEmpty(5, y) && isTileEmpty(6, y)) {
                List<Rook> rightRook = chessboard.getObjectsAt(7, y, Rook.class);
                if (!rightRook.isEmpty() && !rightRook.get(0).moved) {
                    move(mouseX, mouseY);
                    rightRook.get(0).setLocation(4, y);
                }
            }
        }
    }
}
