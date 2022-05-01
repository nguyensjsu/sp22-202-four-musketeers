import greenfoot.*;

import java.util.HashSet;
import java.util.List;

import javafx.util.Pair;

public class Knight extends ChessPiece {
    private static final List<Pair<Integer, Integer>> MOVES = List.of(
            new Pair<>(-2, -1), new Pair<>(-2, 1),
            new Pair<>(-1, -2), new Pair<>(-1, 2),
            new Pair<>(1, -2), new Pair<>(1, 2),
            new Pair<>(2, -1), new Pair<>(2, 1)
    );

    public Knight(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_knight.png" : "black_knight.png");
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        for (Pair<Integer, Integer> move : MOVES) {
            int x = getX() + move.getKey();
            int y = getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y))) {
                moves.add(new Pair<>(x, y));
            }
        }
        return moves;
    }
}
