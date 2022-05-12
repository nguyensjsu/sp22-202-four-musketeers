import greenfoot.*;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class KnightMoveSet extends MoveSet {
    public static final List<Pair<Integer, Integer>> MOVES = List.of(
            new Pair<>(-2, -1), new Pair<>(-2, 1),
            new Pair<>(-1, -2), new Pair<>(-1, 2),
            new Pair<>(1, -2), new Pair<>(1, 2),
            new Pair<>(2, -1), new Pair<>(2, 1)
    );

    public KnightMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        for (Pair<Integer, Integer> move : MOVES) {
            int x = piece.getX() + move.getKey();
            int y = piece.getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y))) {
                moves.add(new Pair<>(x, y));
            }
        }
        return moves;
    }
}
