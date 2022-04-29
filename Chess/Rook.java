import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;

public class Rook extends ChessPiece {
    public Rook(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_rook.png" : "black_rook.png");
    }

    @Override
    protected void move() {
        if (ready && isClickedAnywhere()) {
            if ((isVerticalMove() || isHorizontalMove()) && isEmptyOrEnemy() && !isPathBlocked()) {
                move(getMouseX(), getMouseY());
            }
            ready = false;
        }
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        moves.addAll(getVerticalMoves(curX, curY, moveX, moveY));
        moves.addAll(getHorizontalMoves(curX, curY, moveX, moveY));
        return moves;
    }
}
