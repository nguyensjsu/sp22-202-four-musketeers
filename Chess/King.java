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
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        // Normal moves
        for (Pair<Integer, Integer> move : MOVES) {
            int x = getX() + move.getKey();
            int y = getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y) || (x == moveX && y == moveY))) {
                moves.add(new Pair<>(x, y));
            }
        }

        // Castling
        if (!moved) {
            int y = Chessboard.DIM_Y - 1;

            if (isWhite) {
                // Castle left
                if (isEmpty(1, y) && isEmpty(2, y) && isEmpty(3, y)) {
                    List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                    if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                        moves.add(new Pair<>(2, y));
                    }
                }

                // Castle right
                if (isEmpty(5, y) && isEmpty(6, y)) {
                    List<Rook> rightRook = chessboard.getObjectsAt(7, y, Rook.class);
                    if (!rightRook.isEmpty() && !rightRook.get(0).moved) {
                        moves.add(new Pair<>(6, y));
                    }
                }
            } else {
                // Castle left
                if (isEmpty(1, y) && isEmpty(2, y)) {
                    List<Rook> leftRook = chessboard.getObjectsAt(0, y, Rook.class);
                    if (!leftRook.isEmpty() && !leftRook.get(0).moved) {
                        moves.add(new Pair<>(1, y));
                    }
                }

                // Castle right
                if (isEmpty(4, y) && isEmpty(5, y) && isEmpty(6, y)) {
                    List<Rook> rightRook = chessboard.getObjectsAt(7, y, Rook.class);
                    if (!rightRook.isEmpty() && !rightRook.get(0).moved) {
                        moves.add(new Pair<>(5, y));
                    }
                }
            }
        }

        return moves;
    }
}
