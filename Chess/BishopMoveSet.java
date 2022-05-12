import greenfoot.*;

import javafx.util.Pair;

import java.util.HashSet;

public class BishopMoveSet extends MoveSet {
    public BishopMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        return getDiagonalMoves(curX, curY, moveX, moveY);
    }
}
