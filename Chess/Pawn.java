import greenfoot.*;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;

public class Pawn extends ChessPiece
{
    private static final List<Pair<Integer, Integer>> CAPTURES = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );
    private static final List<Pair<Integer, Integer>> EN_PASSANT = List.of(
            new Pair<>(-1, -1), new Pair<>(1, -1)
    );

    private PromotionButton observer;
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
            super.move(mouseX, mouseY);
        } else {
            List<Pawn> pawn = chessboard.getObjectsAt(mouseX, mouseY + 1, Pawn.class);
            if (isEnPassant(pawn)) {
                // En passant
                chessboard.removeObject(pawn.get(0));
                Greenfoot.playSound("capture.mp3");
                move(mouseX, mouseY, true);
            } else if (mouseY == 1) 
            {
                promotionObserver obs = promotionObserver.getInstance();
                obs.openPromotion(this, mouseX, mouseY);
               
            } else {
                super.move(mouseX, mouseY);
            }
        }
    }

    public void removePawn()
    {
        removed = true;
        chessboard.removeObject(this);
    }
    @Override
    protected HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> moves = new HashSet<>();

        int posX = getX();
        int posY = getY();

        // Move 1 tile
        if (curX == -1) {
            int y = isCheckingNoMoves ? posY + 1 : posY - 1;
            if (isTile(posX, y) && isEmpty(posX, y)) {
                moves.add(new Pair<>(posX, y));

                // Move 2 tiles
                y = isCheckingNoMoves ? y + 1 : y - 1;
                if (!moved && isTile(posX, y) && isEmpty(posX, y)) {
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



    public void activatePromotion(boolean color, int promoXCord, int promoYCord) 
    {
        observer.notify();
    }
}
