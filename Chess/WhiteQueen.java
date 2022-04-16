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

    // Queen could only move to occupied rows/diagonals (?)
    // Cannot capture at long distances
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
                    if (x == this.getX())
                    {
                        
                        int mult = (absDiffY < 0) ? -1 : 1;
                        
                        for (int i = 1; i < absDiffY;i++)
                        {
                            if (!getWorld().getObjectsAt(x,this.getY() + (mult * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                        
                    }
                    else if (y == this.getY())
                    {
                         
                        int mult = (absDiffX < 0) ? -1 : 1;
                        
                        for (int i = 1; i < absDiffX;i++)
                        {
                            if (!getWorld().getObjectsAt(x,this.getY() + (mult * i),ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                    }
                    else if (absDiffX == absDiffY)
                    {
                        int differenceX = x - this.getX();
                        int differenceY = y - this.getY();
                        int difference = Math.abs(differenceX);
                        
                        int mult = 1;
                        
                        if ((differenceX < 0 && differenceY < 0) ||
                            (differenceX < 0 && differenceY > 0)) 
                        {
                            mult = -1;
                        }
                        // ((differenceX > 0 && differenceY < 0) ||
                        // (differenceX > 0 && differenceY > 0))
                            
                        for (int i = 1; i < difference;i++)
                        {
                            if (!getWorld().getObjectsAt(this.getX() + (mult * i),this.getY() - i,ChessPiece.class).isEmpty())
                            {
                                occupied = true;
                                break;
                            }
                        }
                    }
                }

                        
                if (!occupied) move(x,y);
                
                ready = false;
                occupied = false;
            }
        }
    }
}
