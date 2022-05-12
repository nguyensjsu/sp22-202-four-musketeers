import greenfoot.*;

import javafx.util.Pair;

import java.util.HashSet;

public class RookMoveSet extends MoveSet {
    public RookMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(getVerticalMoves(curX, curY, moveX, moveY));
        moves.addAll(getHorizontalMoves(curX, curY, moveX, moveY));
        return moves;
    }
}
