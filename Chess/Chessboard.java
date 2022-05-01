import greenfoot.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class Chessboard extends World implements IChessMoveSubject {
    public static final int DIM_X = 10;
    public static final int DIM_Y = 9;
    public static final int TURN_TIME = 5;

    private final ArrayList<IChessMoveObserver> observers = new ArrayList<>();
    private Function<Integer, String> minDec;
    private Function<Integer, String> secDec;
    public TimerActor timerActor;

    public int moveNumber = 1;
    public boolean isWhiteTurn = true;
    private boolean swapTurn = isWhiteTurn;

    public Chessboard() {
        super(DIM_X + 1, DIM_Y, 100);
        init();
    }

    @Override
    public void act() {
        int rawSeconds = TURN_TIME - timerActor.checkTimer();

        // Swap turns if time is up
        if (rawSeconds == 0) {
            processMove(0,0,"-");
            moveNumber++;
            
            isWhiteTurn = !isWhiteTurn;
            timerActor.startTimer();

            // TODO: If time runs out and you're in check, game over
        }

        // Update timer display
        showText(timerActor.displayTimer(rawSeconds, minDec, secDec), 1, 0);

        flipBoard();
    }

    private void init() {
        addTiles();
        addPieces();
        addMoveHistory();

        setPaintOrder(ChessPiece.class, Tile.class, Label.class, MoveHistory.class);

        // Initialize timer and lambda functions for decorators
        timerActor = TimerActor.getNewInstance();

        minDec = (Integer rawSeconds) -> {
            int minutes = rawSeconds / 60;
            String minutesPadding = "";
            if (rawSeconds > 600) {
                minutesPadding = Integer.toString(rawSeconds / 10);
                minutes = minutes % 10;
            } else {
                minutesPadding = "0";
            }
            return "Timer: " + minutesPadding + minutes + ":";
        };

        secDec = (Integer rawSeconds) -> {
            int seconds = (rawSeconds) % 60;
            String secondsPadding = "";
            if (seconds < 10) {
                secondsPadding = "0";
            }
            return secondsPadding + seconds;
        };
    }

    private void addTiles() {
        int x;
        int y;
        for (x = 1; x < DIM_X - 2; x += 2) {
            for (y = 2; y < 10; y += 2) {
                Gray gray = new Gray();
                addObject(gray, x, y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2) {
            for (y = 1; y < 8; y += 2) {
                Gray gray = new Gray();
                addObject(gray, x, y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2) {
            for (y = 2; y < 10; y += 2) {
                Beige beige = new Beige();
                addObject(beige, x, y);
            }
        }
        for (x = 1; x < DIM_X - 2; x += 2) {
            for (y = 1; y < 8; y += 2) {
                Beige beige = new Beige();
                addObject(beige, x, y);
            }
        }
    }

    private void addPieces() {
        // White
        King wk = new King(true);
        Queen wq = new Queen(true);
        Bishop wb1 = new Bishop(true);
        Bishop wb2 = new Bishop(true);
        Knight wk1 = new Knight(true);
        Knight wk2 = new Knight(true);
        Rook wr1 = new Rook(true);
        Rook wr2 = new Rook(true);

        // Black
        King bk = new King(false);
        Queen bq = new Queen(false);
        Bishop bb1 = new Bishop(false);
        Bishop bb2 = new Bishop(false);
        Knight bk1 = new Knight(false);
        Knight bk2 = new Knight(false);
        Rook br1 = new Rook(false);
        Rook br2 = new Rook(false);

        // King
        addObject(wk, 4, DIM_Y - 1);
        addObject(bk, 4, 1);

        // Queen
        addObject(wq, 3, DIM_Y - 1);
        addObject(bq, 3, 1);

        // Bishop
        addObject(wb1, 2, DIM_Y - 1);
        addObject(wb2, 5, DIM_Y - 1);
        addObject(bb1, 5, 1);
        addObject(bb2, 2, 1);

        // Knight
        addObject(wk1, 1, DIM_Y - 1);
        addObject(wk2, 6, DIM_Y - 1);
        addObject(bk1, 6, 1);
        addObject(bk2, 1, 1);

        // Rook
        addObject(wr1, 0, DIM_Y - 1);
        addObject(wr2, 7, DIM_Y - 1);
        addObject(br1, 7, 1);
        addObject(br2, 0, 1);

        // Pawn
        for (int i = 0; i < DIM_Y - 1; i++) {
            Pawn wp = new Pawn(true);
            addObject(wp, i, DIM_Y - 2);
        }
        for (int i = DIM_Y - 2; i >= 0; i--) {
            Pawn bp = new Pawn(false);
            addObject(bp, i, 2);
        }
    }

    private void addMoveHistory() {
        MoveHistory mh = new MoveHistory();
        addObserver(mh);
        addObject(mh, getWidth() - 2, getHeight() / 2);
    }

    private void flipBoard() {
        if (swapTurn == isWhiteTurn) {
            return;
        }

        Greenfoot.delay(30);

        clearSelection();
        clearValidMoves();

        int edgeX = DIM_Y - 2;
        int edgeY = DIM_Y;

        for (King king : getObjects(King.class)) {
            king.setLocation(edgeX - king.getX(), edgeY - king.getY());
        }

        for (Queen q : getObjects(Queen.class)) {
            q.setLocation(edgeX - q.getX(), edgeY - q.getY());
        }

        for (Rook r : getObjects(Rook.class)) {
            r.setLocation(edgeX - r.getX(), edgeY - r.getY());
        }

        for (Pawn p : getObjects(Pawn.class)) {
            p.setLocation(edgeX - p.getX(), edgeY - p.getY());
        }

        for (Bishop b : getObjects(Bishop.class)) {
            b.setLocation(edgeX - b.getX(), edgeY - b.getY());
        }

        for (Knight k : getObjects(Knight.class)) {
            k.setLocation(edgeX - k.getX(), edgeY - k.getY());
        }

        for (Check check : getObjects(Check.class)) {
            check.setLocation(edgeX - check.getX(), edgeY - check.getY());
        }

        swapTurn = isWhiteTurn;
    }

    public void clearSelection() {
        removeObjects(getObjects(Select.class));
    }

    public void clearValidMoves() {
        removeObjects(getObjects(Valid.class));
    }

    @Override
    public void notifyObservers(int turn, String movement) {
        for (IChessMoveObserver obs : observers) {
            obs.update(turn, movement);
        }
    }

    @Override
    public void addObserver(IChessMoveObserver obs) {
        observers.add(obs);
    }

    @Override
    public void deleteObserver(IChessMoveObserver obs) {
        observers.remove(obs);
    }

    // Used to turn x and y coords into a string that labels can display
    @Override
    public void processMove(int x, int y, String pieceType) {
        String out = pieceType.equals("-") ? " - " : pieceType + "[" + x + "," + y + "]";
        notifyObservers(moveNumber, out);
    }
}
