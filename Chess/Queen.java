import greenfoot.*;

public class Queen extends ChessPiece {
    public Queen(Boolean color) {
        super(color);
        setImage(color ? "white_queen.png" : "black_queen.png");
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

        int absDiffX = Math.abs(mouseX - x);
        int absDiffY = Math.abs(mouseY - y);

        boolean empty = isTileEmpty(mouseX, mouseY);

        if (empty || getPieceColor(mouseX, mouseY) != color) {
            if (mouseX == x) {
                int mult = (diffY < 0) ? -1 : 1;

                for (int i = 1; i < absDiffY; i++) {
                    if (!isTileEmpty(mouseX, y + (mult * i))) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    move(mouseX, mouseY);
                }

            } else if (mouseY == y) {
                int mult = (diffX < 0) ? -1 : 1;

                for (int i = 1; i < absDiffX; i++) {
                    if (!isTileEmpty(x + (mult * i), mouseY)) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    move(mouseX, mouseY);
                }
            } else if (absDiffX == absDiffY) {
                int multX = 1;
                int multY = 1;

                if (diffX < 0) {
                    multX = -1;
                }
                if (diffY < 0) {
                    multY = -1;
                }

                for (int i = 1; i < absDiffX; i++) {
                    if (!isTileEmpty(x + (multX * i), y + (multY * i))) {
                        occupied = true;
                        break;
                    }
                }

                if (!occupied) {
                    move(mouseX, mouseY);
                }
            }
        }

        ready = false;
        occupied = false;
    }
}
