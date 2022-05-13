import greenfoot.*;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public abstract class MoveSet implements IMoveSet {
    protected ChessPiece piece;
    protected Chessboard chessboard;

    public MoveSet(ChessPiece piece) {
        this.piece = piece;
        chessboard = piece.chessboard;
    }

    protected HashSet<Pair<Integer, Integer>> getVerticalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> verticalMoves = new HashSet<>();

        // Up
        for (int y = piece.getY() - 1; y > 0; y--) {
            if (addMove(curX, curY, moveX, moveY, piece.getX(), y, verticalMoves)) {
                break;
            }
        }

        // Down
        for (int y = piece.getY() + 1; y < Chessboard.DIM_Y; y++) {
            if (addMove(curX, curY, moveX, moveY, piece.getX(), y, verticalMoves)) {
                break;
            }
        }

        return verticalMoves;
    }

    protected HashSet<Pair<Integer, Integer>> getHorizontalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> horizontalMoves = new HashSet<>();

        // Left
        for (int x = piece.getX() - 1; x >= 0; x--) {
            if (addMove(curX, curY, moveX, moveY, x, piece.getY(), horizontalMoves)) {
                break;
            }
        }

        // Right
        for (int x = piece.getX() + 1; x < Chessboard.DIM_X - 2; x++) {
            if (addMove(curX, curY, moveX, moveY, x, piece.getY(), horizontalMoves)) {
                break;
            }
        }

        return horizontalMoves;
    }

    protected HashSet<Pair<Integer, Integer>> getDiagonalMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> diagonalMoves = new HashSet<>();

        // Up left
        int y = piece.getY() - 1;
        int x = piece.getX() - 1;
        while (y > 0 && x >= 0) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y--;
            x--;
        }

        // Up right
        y = piece.getY() - 1;
        x = piece.getX() + 1;
        while (y > 0 && x < Chessboard.DIM_X - 2) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y--;
            x++;
        }

        // Down left
        y = piece.getY() + 1;
        x = piece.getX() - 1;
        while (y < Chessboard.DIM_Y && x >= 0) {
            if (addMove(curX, curY, moveX, moveY, x, y, diagonalMoves)) {
                break;
            }
            y++;
            x--;
        }

        // Down right
        y = piece.getY() + 1;
        x = piece.getX() + 1;
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
        if (piece.isWhite != this.piece.isWhite) {
            moves.add(new Pair<>(x, y));
        }
        return true;
    }

    protected boolean isTile(int x, int y) {
        return !chessboard.getObjectsAt(x, y, Tile.class).isEmpty();
    }

    protected boolean isEmpty(int x, int y) {
        return chessboard.getObjectsAt(x, y, ChessPiece.class).isEmpty();
    }

    protected boolean isEnemy(int x, int y) {
        List<ChessPiece> piece = chessboard.getObjectsAt(x, y, ChessPiece.class);
        return !piece.isEmpty() && piece.get(0).isWhite != this.piece.isWhite;
    }
}
