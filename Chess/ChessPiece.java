import greenfoot.*;

public abstract class ChessPiece extends Actor {
    protected boolean color; // False for black, true for white
    protected boolean moved = false; // For castling, can't castle if king or rook moved
    protected boolean selected = false;
    protected boolean ready = false;
    protected Chessboard chessboard;

    public ChessPiece(Boolean color) {
        this.color = color;
    }

    @Override
    public void act() {
        chessboard = (Chessboard) getWorld();
        if (color == chessboard.turn) {
            select();
            move();
            changeStatus();
            capture();
        }
    }

    private void select() {
        int x = getX();
        int y = getY();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if (chessboard.getObjectsAt(x, y, Select.class).isEmpty()) {
                addSelection(x, y);
            } else {
                deleteSelection();
            }
        } else if (isClickedAnywhere() && selected) {
            deleteSelection();
        }
    }

    protected abstract void move();

    private void changeStatus() {
        if (selected) {
            ready = true;
        }
    }

    private void capture() {
        ChessPiece touchPiece = (ChessPiece) getOneIntersectingObject(ChessPiece.class);
        if (touchPiece != null && touchPiece.color != color) {
            chessboard.removeObject(touchPiece);
        }
    }

    private void addSelection(int x, int y) {
        Select select = new Select();
        chessboard.addObject(select, x, y);
        selected = true;
    }

    private void deleteSelection() {
        Select select = chessboard.getObjects(Select.class).get(0);
        chessboard.removeObject(select);
        selected = false;
    }

    protected boolean isEmptyOrEnemy() {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return isTileEmpty(mouseX, mouseY) || getPieceColor(mouseX, mouseY) != color;
    }

    protected boolean isVerticalMove() {
        return getMouseX() == getX();
    }

    protected boolean isHorizontalMove() {
        return getMouseY() == getY();
    }

    protected boolean isDiagonalMove() {
        return Math.abs(getMouseX() - getX()) == Math.abs(getMouseY() - getY());
    }

    protected boolean isPathBlocked() {
        int mouseX = getMouseX();
        int mouseY = getMouseY();

        int x = getX();
        int y = getY();

        int diffX = mouseX - x;
        int diffY = mouseY - y;

        int absDiffX = Math.abs(mouseX - x);
        int absDiffY = Math.abs(mouseY - y);

        int multX = 0;
        if (diffX != 0) {
            multX = diffX < 0 ? -1 : 1;
        }

        int multY = 0;
        if (diffY != 0) {
            multY = diffY < 0 ? -1 : 1;
        }

        if (isVerticalMove()) {
            for (int i = 1; i < absDiffY; i++) {
                if (!isTileEmpty(mouseX, y + (multY * i))) {
                    return true;
                }
            }
        } else if (isHorizontalMove()) {
            for (int i = 1; i < absDiffX; i++) {
                if (!isTileEmpty(x + (multX * i), mouseY)) {
                    return true;
                }
            }
        } else if (isDiagonalMove()) {
            // Diagonal
            for (int i = 1; i < absDiffX; i++) {
                if (!isTileEmpty(x + (multX * i), y + (multY * i))) {
                    return true;
                }
            }
        }

        return false;
    }

    // Move history should prob go here
    protected void move(int x, int y) {
        moved = true;

        setLocation(x, y);
        chessboard.turn = !chessboard.turn;

        String pieceType = getClass().getSimpleName().substring(0, 1);
        if (pieceType.equals("K")) {
            pieceType = getClass().getSimpleName().substring(0, 2);
        }

        // +1 to not start from 0, 8 to have distance measured from bottom as opposed to top
        chessboard.processMove(x + 1, 8 - y + 1, pieceType);
        chessboard.move++;

        chessboard.timerActor.startTimer();
    }

    protected boolean isClickedAnywhere() {
        return Greenfoot.mouseClicked(null);
    }

    protected int getMouseX() {
        return Greenfoot.getMouseInfo().getX();
    }

    protected int getMouseY() {
        return Greenfoot.getMouseInfo().getY();
    }

    protected boolean isTileEmpty(int x, int y) {
        return chessboard.getObjectsAt(x, y, ChessPiece.class).isEmpty();
    }

    protected boolean getPieceColor(int x, int y) {
        return getPieceAt(x, y).color;
    }

    protected ChessPiece getPieceAt(int x, int y) {
        return chessboard.getObjectsAt(x, y, ChessPiece.class).get(0);
    }
}
