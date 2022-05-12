import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class PawnMoveSet extends MoveSet {
    private static final List<Pair<Integer, Integer>> CAPTURES = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );
    private static final List<Pair<Integer, Integer>> EN_PASSANT = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );

    public PawnMoveSet(ChessPiece piece) {
        super(piece);
    }

    @Override
    public HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        int posX = piece.getX();
        int posY = piece.getY();

        // Move 1 tile
        if (curX == -1) {
            int y = isCheckingNoMoves ? posY + 1 : posY - 1;
            if (isTile(posX, y) && isEmpty(posX, y)) {
                moves.add(new Pair<>(posX, y));

                // Move 2 tiles
                y = isCheckingNoMoves ? y + 1 : y - 1;
                if (!piece.moved && isTile(posX, y) && isEmpty(posX, y)) {
                    moves.add(new Pair<>(posX, y));
                }
            }
        }

        // Normal captures
        for (Pair<Integer, Integer> move : CAPTURES) {
            // Pawn is special, need to explicitly separate checking game state vs own moves vs enemy moves
            if (curX == -1) {
                // Checking own move
                int x = posX + move.getKey();
                int y = isCheckingNoMoves ? posY - move.getValue() : posY + move.getValue();
                if (isTile(x, y) && isEnemy(x, y)) {
                    moves.add(new Pair<>(x, y));
                }
            } else {
                // Checking enemy move
                int x = posX + move.getKey();
                int y = isCheckingNoMoves ? posY + move.getValue() : posY - move.getValue();
                moves.add(new Pair<>(x, y));
            }
        }

        // En passant
        if (curX == -1) {
            for (Pair<Integer, Integer> move : EN_PASSANT) {
                int x = posX + move.getKey();
                int y = isCheckingNoMoves ? posY - move.getValue() : posY + move.getValue();
                int targetY = isCheckingNoMoves ? y - 1 : y + 1;
                List<Pawn> pawn = chessboard.getObjectsAt(x, targetY, Pawn.class);
                if (isTile(x, y) && ((Pawn) piece).isEnPassant(pawn)) {
                    moves.add(new Pair<>(x, y));
                }
            }
        }

        return moves;
    }
}
