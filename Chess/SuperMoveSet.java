import javafx.util.Pair;

import java.util.HashSet;

public class SuperMoveSet extends MoveSet {
    public SuperMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        // Queen
        moves.addAll(getVerticalMoves(curX, curY, moveX, moveY));
        moves.addAll(getHorizontalMoves(curX, curY, moveX, moveY));
        moves.addAll(getDiagonalMoves(curX, curY, moveX, moveY));

        // Knight
        for (Pair<Integer, Integer> move : KnightMoveSet.MOVES) {
            int x = piece.getX() + move.getKey();
            int y = piece.getY() + move.getValue();
            if (isTile(x, y) && (isEmpty(x, y) || isEnemy(x, y))) {
                moves.add(new Pair<>(x, y));
            }
        }

        return moves;
    }
}
