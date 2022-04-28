import greenfoot.*;

import java.util.List;

import javafx.util.Pair;

public class Knight extends ChessPiece {

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
        List<Pair<Integer, Integer>> vals = List.of(
                new Pair<>(-2, -1), new Pair<>(-2, 1),
                new Pair<>(-1, -2), new Pair<>(-1, 2),
                new Pair<>(1, -2), new Pair<>(1, 2),
                new Pair<>(2, -1), new Pair<>(2, 1)
        );

        int mouseX = getMouseX();
        int mouseY = getMouseY();
        int x = getX();
        int y = getY();

        for (Pair<Integer, Integer> p : vals) {
            if (mouseX == x + p.getKey() && mouseY == y + p.getValue()) {
                return true;
            }
        }
        return false;
    }
}
