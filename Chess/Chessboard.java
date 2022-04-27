import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.function.*;

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Chessboard extends World
{
    
    private Function<Integer, String> minDec;
    private Function<Integer, String> secDec;

    TimerActor timerActor;

    public int DIM_X = 10, DIM_Y = 9;

    public Chessboard()
    {    
        super(10,9,100);
        startup();
    }

    int move = 1; 
    boolean turn = true; //white turn first (true==white,false==black)
    boolean swapTurn = turn; 

    int turnTime = 30;

    public void act()
    {
        int rawSeconds = turnTime - timerActor.checkTimer();
        
        // swap turns if time is up
        if(rawSeconds < 1) {
            turn = !turn;
            timerActor.startTimer();
        }

        // update timer display
        showText(timerActor.displayTimer(rawSeconds,minDec,secDec),1,0);

        // flip board
        change();
    }

    private void startup()
    {
        int x;
        int y;

        //tile placement
        for (x = 1; x < DIM_X - 2; x += 2)
        {
            for (y = 2; y < 10; y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2)
        {
            for (y = 1; y < 8; y += 2)
            {
                Gray gray = new Gray();
                addObject(gray,x,y);
            }
        }
        for (x = 0; x < DIM_X - 2; x += 2)
        {
            for (y = 2; y < 10; y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }
        for (x = 1; x < DIM_X - 2; x += 2)
        {
            for (y = 1; y < 8; y += 2)
            {
                Beige beige = new Beige();
                addObject(beige,x,y);
            }
        }

        setPaintOrder(ChessPiece.class,Tile.class);

        start();
        
        //initialize timer and lambda functions for decorators
        timerActor = TimerActor.getNewInstance();
        
        minDec = (Integer rawSeconds) -> {
            
            int minutes = rawSeconds / 60;
            String minutesPadding = "";
            if(rawSeconds > 600) {
                minutesPadding = Integer.toString(rawSeconds/10);
                minutes = minutes % 10;
            }
            else {
                minutesPadding = "0";
            }
            String minutesString = "Timer: " + minutesPadding + Integer.toString(minutes) + ":";
            return minutesString;
        };
        
        secDec = (Integer rawSeconds) -> {   
            int seconds = (rawSeconds) % 60;
            
            String secondsPadding = "";
            
            if(seconds < 10) secondsPadding = "0";
            String secondsString = secondsPadding + Integer.toString(seconds);        
            return secondsString;
        };
        
        
    }

    private void start()
    {
        // true == white,false == black

        King wk = new King(true);
        Queen wq = new Queen(true);
        Bishop wb1 = new Bishop(true);
        Bishop wb2 = new Bishop(true);
        Knight wk1 = new Knight(true);
        Knight wk2 = new Knight(true);
        Rook wr1 = new Rook(true);
        Rook wr2 = new Rook(true);
        King bk = new King(false);
        Queen bq = new Queen(false);
        Bishop bb1 = new Bishop(false);
        Bishop bb2 = new Bishop(false);
        Knight bk1 = new Knight(false);
        Knight bk2 = new Knight(false);
        Rook br1 = new Rook(false);
        Rook br2 = new Rook(false);

        //King
        addObject(wk,4,DIM_Y - 1);
        addObject(bk,4,1);

        //Queen
        addObject(wq,3,DIM_Y - 1);
        addObject(bq,3,1);

        //Bishop
        addObject(wb1,2,DIM_Y - 1);
        addObject(wb2,5,DIM_Y - 1);
        addObject(bb1,5,1);
        addObject(bb2,2,1);

        //Knight
        addObject(wk1,1,DIM_Y - 1);
        addObject(wk2,6,DIM_Y - 1);
        addObject(bk1,6,1);
        addObject(bk2,1,1);

        //Rook
        addObject(wr1,0,DIM_Y - 1);
        addObject(wr2,7,DIM_Y - 1);
        addObject(br1,7,1);
        addObject(br2,0,1);

        //Pawn
        for (int i = 0;i < DIM_Y - 1;i++)
        {
            Pawn wp = new Pawn(true);
            addObject(wp,i,DIM_Y-2);
        }
        for (int i = DIM_Y - 2;i >= 0;i--)
        {
            Pawn bp = new Pawn(false);
            addObject(bp,i,2);
        }
    }

    private void change()
    {

        if (swapTurn != turn)
        {
            Greenfoot.delay(30);

            int edgeX = DIM_Y - 2;
            int edgeY = DIM_Y;

            for(King king:getObjects(King.class)) {
                king.setLocation(edgeX-king.getX(),edgeY-king.getY());
            }

            for(Queen q:getObjects(Queen.class)) {
                q.setLocation(edgeX-q.getX(),edgeY-q.getY());
            }

            for(Rook r:getObjects(Rook.class)) {
                r.setLocation(edgeX-r.getX(),edgeY-r.getY());
            }

            for(Pawn p:getObjects(Pawn.class)) {
                p.setLocation(edgeX-p.getX(),edgeY-p.getY());
            }

            for(Bishop b:getObjects(Bishop.class)) {
                b.setLocation(edgeX-b.getX(),edgeY-b.getY());
            }

            for(Knight k:getObjects(Knight.class)) {
                k.setLocation(edgeX-k.getX(),edgeY-k.getY());
            }

            swapTurn = turn;
        }
    }
}
