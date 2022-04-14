import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteRook extends White
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
                
                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
                if (empty)
                {
                    if (x == this.getX())
                    {
                        int diffY = y - this.getY();
                        
                        if (diffY < 0)
                        {
                            diffY = Math.abs(diffY);
                            for (int i = 1;i < diffY;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            diffY = Math.abs(diffY);
                            for (int i = 1;i < diffY;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                            moveCount++;
                        }
                    }
                    else if (y == this.getY())
                    {
                        int diffY = x - this.getX();
                        
                        if (diffY < 0)
                        {
                            diffY = Math.abs(diffY);
                            for (int i = 1;i < diffY;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,y,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            diffY = Math.abs(diffY);
                            for (int i = 1;i < diffY;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,y,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                            moveCount++;
                        }
                    }
                }
                
                ready = false;
                occupied = false;
            }
        }
    }
}
