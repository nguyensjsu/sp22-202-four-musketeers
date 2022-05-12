import greenfoot.*;

import java.util.List;

public class Pawn extends ChessPiece {
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
            } else if (mouseY == 1) {
                promotionObserver obs = promotionObserver.getInstance();
                obs.openPromotion(this, mouseX, mouseY);
            } else {
                super.move(mouseX, mouseY);
            }
        }
    }

    public void removePawn() {
        removed = true;
        chessboard.removeObject(this);
    }

    @Override
    protected MoveSet getMoveSet() {
        return new PawnMoveSet(this);
    }

    protected boolean isEnPassant(List<Pawn> pawn) {
        return !pawn.isEmpty() && pawn.get(0).isWhite != isWhite && pawn.get(0).moveTwoTilesMoveNumber == chessboard.moveNumber - 1;
    }

    public void activatePromotion(boolean color, int promoXCord, int promoYCord) {
        observer.notify();
    }
}
