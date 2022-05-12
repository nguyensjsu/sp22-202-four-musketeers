import greenfoot.*;

public class King extends ChessPiece {
    public King(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_king.png" : "black_king.png");
    }

    @Override
    protected void move(int mouseX, int mouseY) {
        // Castling, update rook position
        if (Math.abs(mouseX - getX()) == 2) {
            int y = Chessboard.DIM_Y - 1;
            if (mouseX == 2) {
                chessboard.getObjectsAt(0, y, Rook.class).get(0).setLocation(3, y);
            } else if (mouseX == 6) {
                chessboard.getObjectsAt(7, y, Rook.class).get(0).setLocation(5, y);
            } else if (mouseX == 1) {
                chessboard.getObjectsAt(0, y, Rook.class).get(0).setLocation(2, y);
            } else if (mouseX == 5) {
                chessboard.getObjectsAt(7, y, Rook.class).get(0).setLocation(4, y);
            }
            Greenfoot.playSound("castle.mp3");
            move(mouseX, mouseY, true);
        } else {
            super.move(mouseX, mouseY);
        }
    }

    @Override
    protected MoveSet getMoveSet() {
        return new KingMoveSet(this);
    }
}
