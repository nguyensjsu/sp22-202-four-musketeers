import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;

public class Queen extends ChessPiece {
    public Queen(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_queen.png" : "black_queen.png");
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(getVerticalMoves(curX, curY, moveX, moveY));
        moves.addAll(getHorizontalMoves(curX, curY, moveX, moveY));
        moves.addAll(getDiagonalMoves(curX, curY, moveX, moveY));
        return moves;
    }
}
