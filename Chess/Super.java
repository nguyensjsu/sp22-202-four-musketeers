import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;

public class Super extends ChessPiece {
    public Super(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_super.png" : "black_super.png");
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        // Queen
        moves.addAll(getVerticalMoves(curX, curY, moveX, moveY));
        moves.addAll(getHorizontalMoves(curX, curY, moveX, moveY));
        moves.addAll(getDiagonalMoves(curX, curY, moveX, moveY));

        // Knight
        for (Pair<Integer, Integer> move : Knight.MOVES) {
            int x = getX() + move.getKey();
            int y = getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y))) {
                moves.add(new Pair<>(x, y));
            }
        }

        return moves;
    }
}
