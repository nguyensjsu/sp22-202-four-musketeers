import greenfoot.*;

import java.util.List;
import java.util.HashSet;

import javafx.util.Pair;

public abstract class ChessPiece extends Actor {
    protected boolean isWhite;
    protected boolean moved = false; // For castling, can't castle if king or rook moved
    protected boolean ready = false;
    protected Chessboard chessboard;

    public ChessPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public void act() {
        chessboard = (Chessboard) getWorld();
        if (isWhite == chessboard.isWhiteTurn) {
            move();
            select();
            changeStatus();
            capture();
        }
    }

    private void move() {
        if (isSelected() && isClickedAnywhere()) {
            int mouseX = getMouseX();
            int mouseY = getMouseY();
            if (!chessboard.getObjectsAt(mouseX, mouseY, Valid.class).isEmpty()) {
                move(mouseX, mouseY);
            }
        }
    }

    private void select() {
        int x = getX();
        int y = getY();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if (!isSelected()) {
                addSelection(x, y);
            } else {
                deleteSelection();
            }
        } else if (isClickedAnywhere() && isSelected()) {
            deleteSelection();
        }
    }

    private void addSelection(int x, int y) {
        chessboard.addObject(new Select(), x, y);

        // Show valid moves
        HashSet<Pair<Integer, Integer>> moves = getPossibleMoves();
        for (Pair<Integer, Integer> move : moves) {
            int moveX = move.getKey();
            int moveY = move.getValue();
            if (isValidMove(moveX, moveY)) {
                chessboard.addObject(new Valid(), moveX, moveY);
            }
        }
    }

    private void deleteSelection() {
        chessboard.clearSelection();
        chessboard.clearValidMoves();
    }

    private void changeStatus() {
        if (isSelected()) {
            ready = true;
        }
    }

    private void capture() {
        ChessPiece touchPiece = (ChessPiece) getOneIntersectingObject(ChessPiece.class);
        if (touchPiece != null && touchPiece.isWhite != isWhite) {
            chessboard.removeObject(touchPiece);
        }
    }

    // Move history should prob go here
    protected void move(int mouseX, int mouseY) {
        moved = true;
        setLocation(mouseX, mouseY);
        updateCheck();

        chessboard.isWhiteTurn = !chessboard.isWhiteTurn;

        String pieceType = getClass().getSimpleName().substring(0, 1);
        if (pieceType.equals("K")) {
            pieceType = getClass().getSimpleName().substring(0, 2);
        }

        // +1 to not start from 0, 8 to have distance measured from bottom as opposed to top
        chessboard.processMove(mouseX + 1, 8 - mouseY + 1, pieceType);
        chessboard.moveNumber++;

        chessboard.timerActor.startTimer();
    }

    private void updateCheck() {
        // Remove check tile
        chessboard.removeObjects(chessboard.getObjects(Check.class));

        // Set check for enemy king
        King enemyKing = getEnemyKing();
        int enemyKingX = enemyKing.getX();
        int enemyKingY = enemyKing.getY();

        // Moves for this team against the enemy
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        for (ChessPiece piece : chessboard.getObjects(ChessPiece.class)) {
            if (piece.isWhite == isWhite) {
                moves.addAll(piece.getPossibleMoves());
            }
        }

        // Add check tile
        if (moves.contains(new Pair<>(enemyKingX, enemyKingY))) {
            chessboard.addObject(new Check(), enemyKingX, enemyKingY);
        }
    }

    private boolean isValidMove(int moveX, int moveY) {
        // Can't capture enemy king
        if (!chessboard.getObjectsAt(moveX, moveY, King.class).isEmpty()) {
            return false;
        }

        // Can't endanger own king
        King ownKing = getOwnKing();
        int kingX = ownKing.getX();
        int kingY = ownKing.getY();
        HashSet<Pair<Integer, Integer>> enemyMoves = getEnemyMoves(moveX, moveY);

        // This piece is not king, check current king position
        if (this != ownKing) {
            return !enemyMoves.contains(new Pair<>(kingX, kingY));
        }

        // This piece is king, check future king position
        if (Math.abs(moveX - kingX) != 2) {
            return !enemyMoves.contains(new Pair<>(moveX, moveY));
        }

        // Castling path must also be safe
        if (moveX < kingX) {
            // Castle left
            enemyMoves.addAll(getEnemyMoves(moveX + 1, moveY));
            return !enemyMoves.contains(new Pair<>(moveX + 1, moveY));
        } else {
            // Castle right
            enemyMoves.addAll(getEnemyMoves(moveX - 1, moveY));
            return !enemyMoves.contains(new Pair<>(moveX - 1, moveY));
        }
    }

    private King getOwnKing() {
        for (King king : chessboard.getObjects(King.class)) {
            if (king.isWhite == isWhite) {
                return king;
            }
        }
        return null;
    }

    private King getEnemyKing() {
        for (King king : chessboard.getObjects(King.class)) {
            if (king.isWhite != isWhite) {
                return king;
            }
        }
        return null;
    }

    private HashSet<Pair<Integer, Integer>> getEnemyMoves(int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> enemyMoves = new HashSet<>();
        for (ChessPiece piece : chessboard.getObjects(ChessPiece.class)) {
            // Enemy and not captured (planned)
            if (piece.isWhite != isWhite && (piece.getX() != moveX || piece.getY() != moveY)) {
                enemyMoves.addAll(piece.getPossibleMoves(getX(), getY(), moveX, moveY));
            }
        }
        return enemyMoves;
    }

    protected abstract HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY);

    protected HashSet<Pair<Integer, Integer>> getPossibleMoves() {
        return getPossibleMoves(-1, -1, -1, -1);
    }

    protected HashSet<Pair<Integer, Integer>> getVerticalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> verticalMoves = new HashSet<>();

        // Up
        for (int y = getY() - 1; y > 0; y--) {
            if (addMove(curX, curY, moveX, moveY, getX(), y, verticalMoves)) {
                break;
            }
        }

        // Down
        for (int y = getY() + 1; y < Chessboard.DIM_Y; y++) {
            if (addMove(curX, curY, moveX, moveY, getX(), y, verticalMoves)) {
                break;
            }
        }

        return verticalMoves;
    }

    protected HashSet<Pair<Integer, Integer>> getHorizontalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> horizontalMoves = new HashSet<>();

        // Left
        for (int x = getX() - 1; x >= 0; x--) {
            if (addMove(curX, curY, moveX, moveY, x, getY(), horizontalMoves)) {
                break;
            }
        }

        // Right
        for (int x = getX() + 1; x < Chessboard.DIM_X - 2; x++) {
            if (addMove(curX, curY, moveX, moveY, x, getY(), horizontalMoves)) {
                break;
            }
        }

        return horizontalMoves;
    }

    protected HashSet<Pair<Integer, Integer>> getDiagonalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> diagonalMoves = new HashSet<>();

        // Up left
        int y = getY() - 1;
        int x = getX() - 1;
        while (y > 0 && x >= 0) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y--;
            x--;
        }

        // Up right
        y = getY() - 1;
        x = getX() + 1;
        while (y > 0 && x < Chessboard.DIM_X - 2) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y--;
            x++;
        }

        // Down left
        y = getY() + 1;
        x = getX() - 1;
        while (y < Chessboard.DIM_Y && x >= 0) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y++;
            x--;
        }

        // Down right
        y = getY() + 1;
        x = getX() + 1;
        while (y < Chessboard.DIM_Y && x < Chessboard.DIM_X - 2) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y++;
            x++;
        }

        return diagonalMoves;
    }

    private boolean addMove(int curX, int curY, int moveX, int moveY, int x, int y, HashSet<Pair<Integer, Integer>> moves) {
        // Old position
        if (x == curX && y == curY) {
            moves.add(new Pair<>(x, y));
            return false;
        }

        // New position
        if (x == moveX && y == moveY) {
            moves.add(new Pair<>(x, y));
            return true;
        }

        // Empty
        List<ChessPiece> possiblePiece = chessboard.getObjectsAt(x, y, ChessPiece.class);
        if (possiblePiece.isEmpty()) {
            moves.add(new Pair<>(x, y));
            return false;
        }

        // Enemy
        ChessPiece piece = possiblePiece.get(0);
        if (piece.isWhite != isWhite) {
            moves.add(new Pair<>(x, y));
        }
        return true;
    }

    protected boolean isEnemy(int x, int y) {
        List<ChessPiece> piece = chessboard.getObjectsAt(x, y, ChessPiece.class);
        return !piece.isEmpty() && piece.get(0).isWhite != isWhite;
    }

    protected boolean isEmptyOrEnemy() {
        return isEmptyOrEnemy(getMouseX(), getMouseY());
    }

    protected boolean isEmptyOrEnemy(int x, int y) {
        return isEmptyTile(x, y) || isWhitePiece(x, y) != isWhite;
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

    private boolean isSelected() {
        return !chessboard.getObjectsAt(getX(), getY(), Select.class).isEmpty();
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

    protected boolean isEmptyTile(int x, int y) {
        return chessboard.getObjectsAt(x, y, ChessPiece.class).isEmpty();
    }

    protected boolean isTile(int x, int y) {
        return !chessboard.getObjectsAt(x, y, Tile.class).isEmpty();
    }

    protected boolean isWhitePiece(int x, int y) {
        return getPieceAt(x, y).isWhite;
    }

    protected ChessPiece getPieceAt(int x, int y) {
        return chessboard.getObjectsAt(x, y, ChessPiece.class).get(0);
    }
}
