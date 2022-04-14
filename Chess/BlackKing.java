import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackKing extends Black
{
    int moveCount = 0;
    
    public void act() 
    {
        Chessboard chessboard = (Chessboard)getWorld();
        if (chessboard.move % 2 == 0)
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
                
                boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                
                if (empty)
                {
                    if (absDiffX <= 1 && absDiffY <= 1)
                    {
                        move(x,y);
                        moveCount++;
                    }
                    else if (moveCount == 0)   //rosada
                    {
                        Chessboard chessboard = (Chessboard)getWorld();
                        if (x == 1 && y == 7)
                        {
                            if (chessboard.getObjectsAt(1,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(2,7,ChessPiece.class).isEmpty())
                            {
                                BlackRook br = chessboard.getObjects(BlackRook.class).get(0);
                                if (br.moveCount == 0)
                                {
                                    move(x,y);
                                    br.setLocation(2,7);
                                }
                            }
                        }
                        else if (x == 5 && y == 7)
                        {
                            if (chessboard.getObjectsAt(4,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(5,7,ChessPiece.class).isEmpty() && chessboard.getObjectsAt(6,7,ChessPiece.class).isEmpty())
                            {
                                BlackRook br = chessboard.getObjects(BlackRook.class).get(1);
                                if (br.moveCount == 0)
                                {
                                    move(x,y);
                                    moveCount++;
                                    br.setLocation(4,7);
                                    br.moveCount++;
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
