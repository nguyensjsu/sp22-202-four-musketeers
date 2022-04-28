import greenfoot.*;

public class Bishop extends ChessPiece {
    public Bishop(Boolean color) {
        super(color);
        setImage(color ? "white_bishop.png" : "black_bishop.png");
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

        //if tile is empty or other color
        if (empty || getPieceColor(mouseX, mouseY) != color) {
            if (absDiffX == absDiffY) {
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
