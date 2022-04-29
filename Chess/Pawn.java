import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class Pawn extends ChessPiece {
    private static final List<Pair<Integer, Integer>> MOVES = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );

    private int stepTwo;

    public Pawn(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        int mouseX = getMouseX();
        int mouseY = getMouseY();
        int x = getX();
        int y = getY();
        int diffX = mouseX - x;
        int diffY = mouseY - y;

        if (isVerticalMove()) {
            if (y == Chessboard.DIM_Y - 2 && diffY == -2 && isEmptyTile(mouseX, mouseY) && isEmptyTile(mouseX, mouseY + 1)) {
                // First move allow move 2 tiles, check if 2 tiles in front are empty
                stepTwo = chessboard.moveNumber;
                move(mouseX, mouseY);
            } else if (diffY == -1 && isEmptyTile(mouseX, mouseY)) {
                // Move 1 tile
                move(mouseX, mouseY);
            }
        } else if (Math.abs(diffX) == 1 && diffY == -1) {
            if (!isEmptyTile(mouseX, mouseY) && isWhitePiece(mouseX, mouseY) != isWhite) {
                // Normal capture
                move(mouseX, mouseY);
            } else if (!chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).isEmpty()) {
                // En passant
                Pawn oppPawn = chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class).get(0);
                if (isWhite != oppPawn.isWhite && oppPawn.stepTwo == chessboard.moveNumber - 1) {
                    move(mouseX, mouseY);
                    chessboard.removeObject(oppPawn);
                }
            }
        }

        ready = false;
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        // TODO: Separate moves vs captures
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
