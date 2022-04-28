import greenfoot.*;

public class King extends ChessPiece {
    public King(Boolean color) {
        super(color);
        setImage(color ? "white_king.png" : "black_king.png");
    }

    @Override
    protected void move() {
        if (!ready || !isClickedAnywhere()) {
            return;
        }

        int mouseX = getMouseX();
        int mouseY = getMouseY();

        int absDiffX = Math.abs(mouseX - getX());
        int absDiffY = Math.abs(mouseY - getY());

        boolean empty = isTileEmpty(mouseX, mouseY);

        //if tile is empty or other color
        if (empty || getPieceColor(mouseX, mouseY) != color) {
            if (absDiffX <= 1 && absDiffY <= 1) {
                move(mouseX, mouseY);
            } else if (!moved) {
                //castling
                if (color) {
                    whiteKing(chessboard, mouseX, mouseY);
                } else {
                    blackKing(chessboard, mouseX, mouseY);
                }
            }
        }

        ready = false;
    }

    void blackKing(Chessboard chessboard, int x, int y) {
        int dimY = chessboard.DIM_Y;
        if (x == 1 && y == dimY - 1) {
            if (isTileEmpty(1, dimY - 1) && isTileEmpty(2, dimY - 1)) {
                //Rook blackRook = chessboard.getObjects(Rook.class).get(2); // 2 is 3rd rook which is black
                Rook blackRook = null;
                for (Rook rook : chessboard.getObjects(Rook.class)) {
                    if (!rook.color) {
                        blackRook = rook;
                        break;
                    }
                }
                if (!blackRook.moved) {
                    move(x, y);
                    blackRook.setLocation(2, dimY - 1);
                }
            }
        } else if (x == 5 && y == dimY - 1) {
            if (isTileEmpty(4, dimY - 1) && isTileEmpty(5, dimY - 1) && isTileEmpty(6, dimY - 1)) {
                //Rook blackRook = chessboard.getObjects(Rook.class).get(3); // 3 is 4rd rook which is black
                Rook blackRook = null;
                int second = 0;
                for (Rook rook : chessboard.getObjects(Rook.class)) {
                    if (!rook.color) {
                        second++;
                        if (second == 2) {
                            blackRook = rook;
                        }
                    }
                }
                if (!blackRook.moved) {
                    move(x, y);
                    blackRook.setLocation(4, dimY - 1);
                }
            }
        }
    }

    void whiteKing(Chessboard chessboard, int x, int y) {
        int dimY = chessboard.DIM_Y;
        if (x == 2 && y == dimY - 1) {
            if (isTileEmpty(1, dimY - 1) && isTileEmpty(2, dimY - 1) && isTileEmpty(3, dimY - 1)) {
                Rook whiteRook = null;
                for (Rook rook : chessboard.getObjects(Rook.class)) {
                    if (rook.color) {
                        whiteRook = rook;
                        break;
                    }
                }

                //Rook whiteRook = chessboard.getObjects(Rook.class).get(0); // 0 is 1st rook which is white
                if (!whiteRook.moved) {
                    move(x, y);
                    whiteRook.setLocation(3, dimY - 1);
                }
            }
        } else if (x == 6 && y == dimY - 1) {
            if (isTileEmpty(5, dimY - 1) && isTileEmpty(6, dimY - 1)) {
                //Rook whiteRook = chessboard.getObjects(Rook.class).get(1); // 1 is 2nd rook which is white
                Rook whiteRook = null;
                int second = 0;
                for (Rook rook : chessboard.getObjects(Rook.class)) {
                    if (rook.color) {
                        second++;
                        if (second == 2) {
                            whiteRook = rook;
                        }
                    }
                }
                if (!whiteRook.moved) {
                    move(x, y);
                    whiteRook.setLocation(5, dimY - 1);
                }
            }
        }
    }

}
