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
    protected void move() {
        if (ready && isClickedAnywhere()) {
            if (isKnightMove() && isEmptyOrEnemy()) {
                move(getMouseX(), getMouseY());
            }
            ready = false;
        }
    }

    private boolean isKnightMove() {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        int x = getX();
        int y = getY();
        for (Pair<Integer, Integer> move : MOVES) {
            if (mouseX == x + move.getKey() && mouseY == y + move.getValue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();
        for (Pair<Integer, Integer> move : MOVES) {
            int x = getX() + move.getKey();
            int y = getY() + move.getValue();
            if (isTile(x, y) && isEmptyOrEnemy(x, y)) {
                moves.add(new Pair<>(x, y));
            }
        }
        return moves;
    }
}
