import greenfoot.*;

import java.util.HashSet;

import javafx.util.Pair;

public abstract class ChessPiece extends Actor {
    protected boolean isWhite;
    protected boolean moved; // For castling, can't castle if king or rook moved
    protected boolean removed; // For pawn promotion
    protected Chessboard chessboard;

    public ChessPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public void act() {
        chessboard = (Chessboard) getWorld();
        if (!chessboard.gameOver && isWhite == chessboard.isWhiteTurn) {
            move();
            if (!removed) {
                select();
            }
        }
    }

    private void move() {
        if (isSelected() && isClickedAnywhere()) {
            int mouseX = getMouseX();
            int mouseY = getMouseY();
            if (!chessboard.getObjectsAt(mouseX, mouseY, Valid.class).isEmpty()) {
                move(mouseX, mouseY);
                chessboard.gameStart = false;
            }
        }
    }

    private void select() {
        int x = getX();
        int y = getY();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if (!isSelected()) {
                addSelection(x, y);
            } else {
                deleteSelection();
            }
        } else if (isClickedAnywhere() && isSelected()) {
            deleteSelection();
        }
    }

    private void addSelection(int x, int y) {
        chessboard.addObject(new Select(), x, y);
        for (Pair<Integer, Integer> validMove : getValidMoves(false)) {
            chessboard.addObject(new Valid(), validMove.getKey(), validMove.getValue());
        }
    }

    private void deleteSelection() {
        chessboard.clearSelection();
        chessboard.clearValidMoves();
    }

    protected void move(int mouseX, int mouseY) {
        move(mouseX, mouseY, false);
    }

    protected void move(int mouseX, int mouseY, boolean isSpecialMove) {
        moved = true;
        setLocation(mouseX, mouseY);

        // Move history
        String pieceType = getClass().getSimpleName().substring(0, 1);
        if (pieceType.equals("K")) {
            pieceType = getClass().getSimpleName().substring(0, 2);
        }

        // +1 to not start from 0, 8 to have distance measured from bottom as opposed to top
        chessboard.processMove(mouseX + 1, 8 - mouseY + 1, pieceType);

        chessboard.timerActor.startTimer();

        capture(isSpecialMove);
        updateGameState();
        deleteSelection();
    }

    private void capture(boolean isSpecialMove) {
        ChessPiece touchPiece = (ChessPiece) getOneIntersectingObject(ChessPiece.class);
        if (touchPiece != null) {
            chessboard.removeObject(touchPiece);
            Greenfoot.playSound("capture.mp3");
        } else if (!isSpecialMove) {
            Greenfoot.playSound("move.mp3");
        }
    }

    private void updateGameState() {
        chessboard.removeObjects(chessboard.getObjects(Check.class));
        boolean enemyHasNoMoves = enemyHasNoMoves();
        if (isCheck()) {
            King enemyKing = getEnemyKing();
            int enemyKingX = enemyKing.getX();
            int enemyKingY = enemyKing.getY();
            if (enemyHasNoMoves) {
                chessboard.gameOver = true;
                chessboard.addObject(new Checkmate(), enemyKingX, enemyKingY);
                Greenfoot.playSound("checkmate.mp3");
            } else {
                chessboard.addObject(new Check(), enemyKingX, enemyKingY);
                Greenfoot.playSound("check.mp3");
            }
        } else if (enemyHasNoMoves) {
            chessboard.gameOver = true;

            King ownKing = getOwnKing();
            chessboard.addObject(new Checkmate(), ownKing.getX(), ownKing.getY());

            King enemyKing = getEnemyKing();
            chessboard.addObject(new Checkmate(), enemyKing.getX(), enemyKing.getY());

            Greenfoot.playSound("stalemate.mp3");
        }
        chessboard.moveNumber++;
        chessboard.isWhiteTurn = !chessboard.isWhiteTurn;
    }

    private boolean enemyHasNoMoves() {
        HashSet<Pair<Integer, Integer>> enemyValidMoves = new HashSet<>();
        for (ChessPiece piece : chessboard.getObjects(ChessPiece.class)) {
            if (piece.isWhite != isWhite) {
                enemyValidMoves.addAll(piece.getValidMoves(true));
            }
        }
        return enemyValidMoves.isEmpty();
    }

    private boolean isCheck() {
        HashSet<Pair<Integer, Integer>> teamPossibleMoves = new HashSet<>();
        for (ChessPiece piece : chessboard.getObjects(ChessPiece.class)) {
            if (piece.isWhite == isWhite) {
                teamPossibleMoves.addAll(piece.getPossibleMoves(false));
            }
        }
        King enemyKing = getEnemyKing();
        return teamPossibleMoves.contains(new Pair<>(enemyKing.getX(), enemyKing.getY()));
    }

    protected abstract IMoveSet getMoveSet();

    private HashSet<Pair<Integer, Integer>> getPossibleMoves(int curX, int curY, int moveX, int moveY, boolean isCheckingNoMoves) {
        return getMoveSet().getPossibleMoves(curX, curY, moveX, moveY, isCheckingNoMoves);
    }

    private HashSet<Pair<Integer, Integer>> getPossibleMoves(boolean isCheckingNoMoves) {
        return getPossibleMoves(-1, -1, -1, -1, isCheckingNoMoves);
    }

    // Need isCheckingNoMoves because pawns make life difficult
    private HashSet<Pair<Integer, Integer>> getValidMoves(boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> validMoves = new HashSet<>();
        for (Pair<Integer, Integer> possibleMove : getPossibleMoves(isCheckingNoMoves)) {
            if (isValidMove(possibleMove, isCheckingNoMoves)) {
                validMoves.add(possibleMove);
            }
        }
        return validMoves;
    }

    private boolean isValidMove(Pair<Integer, Integer> move, boolean isCheckingNoMoves) {
        int moveX = move.getKey();
        int moveY = move.getValue();

        // Can't capture enemy king
        if (!chessboard.getObjectsAt(moveX, moveY, King.class).isEmpty()) {
            return false;
        }

        // Can't endanger own king
        King ownKing = getOwnKing();
        int kingX = ownKing.getX();
        int kingY = ownKing.getY();
        HashSet<Pair<Integer, Integer>> enemyPossibleMoves = getEnemyPossibleMoves(moveX, moveY, isCheckingNoMoves);

        // This piece is not king, check current king position
        if (this != ownKing) {
            return !enemyPossibleMoves.contains(new Pair<>(kingX, kingY));
        }

        // This piece is king, not castling, check future king position
        if (Math.abs(moveX - kingX) != 2) {
            return !enemyPossibleMoves.contains(new Pair<>(moveX, moveY));
        }

        // Castling, must not be in check
        if (!chessboard.getObjectsAt(kingX, kingY, Check.class).isEmpty()) {
            return false;
        }

        // Castling tile must be safe
        if (enemyPossibleMoves.contains(new Pair<>(moveX, moveY))) {
            return false;
        }

        // Castling path must also be safe
        if (moveX < kingX) {
            // Castle left
            return !enemyPossibleMoves.contains(new Pair<>(moveX + 1, moveY));
        } else {
            // Castle right
            return !enemyPossibleMoves.contains(new Pair<>(moveX - 1, moveY));
        }
    }

    private HashSet<Pair<Integer, Integer>> getEnemyPossibleMoves(int moveX, int moveY, boolean isCheckingNoMoves) {
        HashSet<Pair<Integer, Integer>> enemyMoves = new HashSet<>();
        for (ChessPiece piece : chessboard.getObjects(ChessPiece.class)) {
            // Enemy and not captured (planned)
            if (piece.isWhite != isWhite && (piece.getX() != moveX || piece.getY() != moveY)) {
                enemyMoves.addAll(piece.getPossibleMoves(getX(), getY(), moveX, moveY, isCheckingNoMoves));
            }
        }
        return enemyMoves;
    }

    private King getOwnKing() {
        for (King king : chessboard.getObjects(King.class)) {
            if (king.isWhite == isWhite) {
                return king;
            }
        }
        return null;
    }

    private King getEnemyKing() {
        for (King king : chessboard.getObjects(King.class)) {
            if (king.isWhite != isWhite) {
                return king;
            }
        }
        return null;
    }

    private int getMouseX() {
        return Greenfoot.getMouseInfo().getX();
    }

    private int getMouseY() {
        return Greenfoot.getMouseInfo().getY();
    }

    private boolean isClickedAnywhere() {
        return Greenfoot.mouseClicked(null);
    }

    private boolean isSelected() {
        return !chessboard.getObjectsAt(getX(), getY(), Select.class).isEmpty();
    }

    public boolean getIsWhite() {
        return isWhite;
    }
}
