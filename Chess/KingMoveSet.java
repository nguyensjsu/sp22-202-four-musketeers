import greenfoot.*;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class KingMoveSet extends MoveSet {
    private static final List<Pair<Integer, Integer>> MOVES = List.of(
            new Pair<>(-1, -1), new Pair<>(0, -1),
            new Pair<>(1, -1), new Pair<>(1, 0),
            new Pair<>(1, 1), new Pair<>(0, 1),
            new Pair<>(-1, 1), new Pair<>(-1, 0)
    );

    public KingMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        // Normal moves
        for (Pair<Integer, Integer> move : MOVES) {
            int x = piece.getX() + move.getKey();
            int y = piece.getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y) || (x == moveX && y == moveY))) {
                moves.add(new Pair<>(x, y));
            }
        }

        // Castling
        if (!piece.moved) {
            int y = Chessboard.DIM_Y - 1;

            if (piece.isWhite) {
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
