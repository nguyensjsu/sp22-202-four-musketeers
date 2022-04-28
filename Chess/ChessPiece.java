import greenfoot.*;

public abstract class ChessPiece extends Actor {
    protected boolean selected = false;
    protected boolean ready = false;
    protected boolean occupied = false;
    protected boolean color; // false for black, true for white
    protected boolean moved = false; // for castling, can't castle if king or rook moved
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

    protected void select() {
        int x = getX();
        int y = getY();

        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if (chessboard.getObjects(Select.class).size() == 0) {
                addSelection(x, y);
            } else if (chessboard.getObjectsAt(x, y, Select.class).size() == 1) {
                deleteSelection();
            } else {
                addSelection(x, y);
            }
        } else if (isClickedAnywhere() && selected) {
            deleteSelection();
        }
    }

    private void changeStatus() {
        if (selected) {
            ready = true;
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

    protected abstract void move();

    //movehistory should prob go here
    protected void move(int x, int y) {
        moved = true;

        setLocation(x, y);
        chessboard.turn = !chessboard.turn;

        String pieceType = getClass().getSimpleName().substring(0, 1);
        if (pieceType.equals("K")) {
            pieceType = getClass().getSimpleName().substring(0, 2);
        }
        //+1 to not start from 0,8 to have distance measured from bottom as opposed to top

        chessboard.processMove(x + 1, 8 - y + 1, pieceType);
        chessboard.move++;

        chessboard.timerActor.startTimer();
    }

    private void capture() {
        ChessPiece touchPiece = (ChessPiece) getOneIntersectingObject(ChessPiece.class);
        if (touchPiece != null && touchPiece.color != color) {
            chessboard.removeObject(touchPiece);
        }
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
