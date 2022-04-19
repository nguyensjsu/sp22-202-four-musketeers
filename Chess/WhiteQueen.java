import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteQueen extends White
{
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
                
                int diffX = x - this.getX();
                int diffY = y - this.getY();

                int absDiffX = Math.abs(x - this.getX());
                int absDiffY = Math.abs(y - this.getY());

                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();

                if (empty)
                {
                    if (x == this.getX())
                    {
                        int mult = (diffY < 0) ? -1 : 1;
                        
                        for (int i = 1;i < absDiffY;i++)
                        {
                            if (!getWorld().getObjectsAt(x,this.getY() + (mult * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        if (!occupied) move(x,y);
                        
                    }
                    else if (y == this.getY())
                    {
                        int mult = (diffX < 0) ? -1 : 1;
                        
                        for (int i = 1; i < absDiffX;i++)
                        {
                            if (!getWorld().getObjectsAt(this.getX() + (mult * i),y,ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        if (!occupied) move(x,y);
                    }
                    else if (absDiffX == absDiffY)
                    {
                        int multX = 1;
                        int multY = 1;
                        
                        if (diffX < 0) multX = -1;
                        if (diffY < 0) multY = -1;
                            
                        for (int i = 1; i < absDiffX;i++)
                        {
                            if (!getWorld().getObjectsAt(this.getX() + (multX * i),this.getY() + (multY * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                    }
                    if (!occupied) move(x,y);
                }
                
                ready = false;
                occupied = false;
            }
        }
    }
}
