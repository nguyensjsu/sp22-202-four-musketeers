import greenfoot.*;

import java.util.HashSet;

import javafx.util.Pair;

public class Bishop extends ChessPiece {
    public Bishop(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_bishop.png" : "black_bishop.png");
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        return getDiagonalMoves(curX, curY, moveX, moveY);
    }
}
