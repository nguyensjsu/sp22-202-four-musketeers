import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class Pawn extends ChessPiece {
    private static final List<Pair<Integer, Integer>> CAPTURES = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );
    private static final List<Pair<Integer, Integer>> EN_PASSANT = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );

    private int moveTwoTilesMoveNumber;

    public Pawn(boolean isWhite) {
        super(isWhite);
        setImage(isWhite ? "white_pawn.png" : "black_pawn.png");
    }

    @Override
    protected void move(int mouseX, int mouseY) {
        if (Math.abs(mouseY - getY()) == 2) {
            // Move two tiles
            moveTwoTilesMoveNumber = chessboard.moveNumber;
        } else {
            // En passant
            List<Pawn> pawn = chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class);
            if (isEnPassant(pawn)) {
                chessboard.removeObject(pawn.get(0));
            }
        }
        super.move(mouseX, mouseY);
    }

    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        int posX = getX();
        int posY = getY();

        // Move 1 tile
        if (curX == -1) {
            if (isTile(posX, posY - 1) && isEmptyTile(posX, posY - 1)) {
                moves.add(new Pair<>(posX, posY - 1));

                // Move 2 tiles
                if (!moved && isTile(posX, posY - 2) && isEmptyTile(posX, posY - 2)) {
                    moves.add(new Pair<>(posX, posY - 2));
                }
            }
        }

        // Normal captures
        for (Pair<Integer, Integer> move : CAPTURES) {
            // Pawn is special, need to explicitly separate checking own/enemy move
            if (curX == -1) {
                // Checking own move
                int x = posX + move.getKey();
                int y = posY + move.getValue();
                if (isTile(x, y) && isEnemy(x, y)) {
                    moves.add(new Pair<>(x, y));
                }
            } else {
                // Checking enemy move
                int x = posX + move.getKey();
                int y = posY - move.getValue();
                if (x == moveX && y == moveY) {
                    moves.add(new Pair<>(x, y));
                }
            }
        }

        // En passant
        if (curX == -1) {
            for (Pair<Integer, Integer> move : EN_PASSANT) {
                int x = posX + move.getKey();
                int y = posY + move.getValue();
                List<Pawn> pawn = chessboard.getObjectsAt(x, y + 1, Pawn.class);
                if (isTile(x, y) && isEnPassant(pawn)) {
                    moves.add(new Pair<>(x, y));
                }
            }
        }

        return moves;
    }

    private boolean isEnPassant(List<Pawn> pawn) {
        return !pawn.isEmpty() && pawn.get(0).isWhite != isWhite && pawn.get(0).moveTwoTilesMoveNumber == chessboard.moveNumber - 1;
    }
}
