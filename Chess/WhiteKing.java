import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteKing extends White
{
    int moveCount = 0;
    
    public void act() 
    {
        Chessboard chessboard = (Chessboard)getWorld();
        if (chessboard.move % 2 == 1)
        {
            select();
            move();
            changeStatus();
            capture();
        }
    }
    
    private void move()
    {
        if (ready)
        {
            if (Greenfoot.mouseClicked(null))
            {
                int x = Greenfoot.getMouseInfo().getX();
                int y = Greenfoot.getMouseInfo().getY();
                
                int absDiffX = Math.abs(x - this.getX());
                int absDiffY = Math.abs(y - this.getY());
                
                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
                if (empty)
                {
                    if (absDiffX <= 1 && absDiffY <= 1)
                    {
                        move(x,y);
                        moveCount++;
                    }
                    else if (moveCount == 0)   //castling
                    {
                        Chessboard chessboard = (Chessboard)getWorld();
                        if (x == 2 && y == 7)
                        {
                            if (chessboard.getObjectsAt(1,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(2,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(3,7,ChessPiece.class).isEmpty())
                            {
                                WhiteRook whiteRook = chessboard.getObjects(WhiteRook.class).get(0);
                                if (whiteRook.moveCount == 0)
                                {
                                    move(x,y);
                                    whiteRook.setLocation(3,7);
                                }
                            }
                        }
                        else if (x == 6 && y == 7)
                        {
                            if (chessboard.getObjectsAt(5,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(6,7,ChessPiece.class).isEmpty())
                            {
                                WhiteRook whiteRook = chessboard.getObjects(WhiteRook.class).get(1);
                                if (whiteRook.moveCount == 0)
                                {
                                    move(x,y);
                                    moveCount++;
                                    whiteRook.setLocation(5,7);
                                    whiteRook.moveCount++;
                                }
                            }
                        }
                    }
                }
                
                ready = false;
            }
        }
    }
}
