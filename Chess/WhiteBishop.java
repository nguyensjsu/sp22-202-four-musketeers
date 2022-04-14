import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteBishop extends White
{
    public void act() 
    {
        Chessboard chessBoard = (Chessboard)getWorld();
        if (chessBoard.move % 2 == 1)
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
                    if (absDiffX == absDiffY)
                    {
                        int diffX = x - this.getX();
                        int diffY = y - this.getY();
                        int diff = Math.abs(diffX);
                        
                        if (diffX < 0 && diffY < 0)
                        {
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (diffX < 0 && diffY > 0)
                        {
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (diffX > 0 && diffY < 0)
                        {
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (diffX > 0 && diffY > 0)
                        {
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() + i,ChessPiece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        if (!occupied)
                        {
                            move(x,y);
                        }
                    }
                }
                
                ready = false;
                occupied = false;
            }
        }
    }
}
