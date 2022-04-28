import greenfoot.*;

import java.util.List;

import javafx.util.Pair;

public class Knight extends ChessPiece {

    public Knight(Boolean color) {
        super(color);
        setImage(color ? "white_knight.png" : "black_knight.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        int mouseX = getMouseX();
        int mouseY = getMouseY();

        List<Pair<Integer, Integer>> vals = List.of(
                new Pair<>(-2, -1), new Pair<>(-2, 1),
                new Pair<>(-1, -2), new Pair<>(-1, 2),
                new Pair<>(1, -2), new Pair<>(1, 2),
                new Pair<>(2, -1), new Pair<>(2, 1)
        );

        boolean empty = isTileEmpty(mouseX, mouseY);

        //if tile is empty or other color
        if (empty || getPieceColor(mouseX, mouseY) != color) {
            for (Pair<Integer, Integer> p : vals) {
                if ((mouseX == getX() + p.getKey() && mouseY == getY() + p.getValue())) {
                    move(mouseX, mouseY);
                    break;
                }
            }
        }

        ready = false;
    }
}
