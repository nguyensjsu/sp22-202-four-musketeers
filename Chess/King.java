import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class King extends ChessPiece {
    private static final List<Pair<Integer, Integer>> MOVES = List.of(
            new Pair<>(-1, -1), new Pair<>(0, -1),
            new Pair<>(1, -1), new Pair<>(1, 0),
            new Pair<>(1, 1), new Pair<>(0, 1),
            new Pair<>(-1, 1), new Pair<>(-1, 0)
    );

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
            if (isEmptyTile(1, y) && isEmptyTile(2, y) && isEmptyTile(3, y)) {
                List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                    move(mouseX, mouseY);
                    leftRook.get(0).setLocation(3, y);
                }
            }
        } else if (mouseX == 6 && mouseY == y) {
            // Castle right
            if (isEmptyTile(5, y) && isEmptyTile(6, y)) {
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
            if (isEmptyTile(1, y) && isEmptyTile(2, y)) {
                List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                    move(mouseX, mouseY);
                    leftRook.get(0).setLocation(2, y);
                }
            }
        } else if (mouseX == 5 && mouseY == y) {
            // Castle right
            if (isEmptyTile(4, y) && isEmptyTile(5, y) && isEmptyTile(6, y)) {
                List<Rook> rightRook = chessboard.getObjectsAt(7, y, Rook.class);
                if (!rightRook.isEmpty() && !rightRook.get(0).moved) {
                    move(mouseX, mouseY);
                    rightRook.get(0).setLocation(4, y);
                }
            }
        }
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        // TODO: Castling
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        for (Pair<Integer, Integer> move : MOVES) {
            int x = getX() + move.getKey();
            int y = getY() + move.getValue();
            if (isTile(x, y) && isEmptyOrEnemy(x, y)) {
                moves.add(new Pair<>(x, y));
            }
        }
        return moves;
    }
}
