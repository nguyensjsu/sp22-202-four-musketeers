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
        ChessBoard chessBoard = (ChessBoard)getWorld();
        if (chessBoard.tahy % 2 == 1)
        {
            select();
            move();
            changeStatus();
            acquire();
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
                        int difference = y - this.getY();
                        
                        if (difference < 0)
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() - i,Piece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() + i,Piece.class).isEmpty())
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
                        int difference = x - this.getX();
                        
                        if (difference < 0)
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,y,Piece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            difference = Math.abs(difference);
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,y,Piece.class).isEmpty())
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