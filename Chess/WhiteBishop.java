import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteBishop extends White
{
    public void act() 
    {
        ChessBoard chessBoard = (ChessBoard)getWorld();
        if (chessBoard.move % 2 == 1)
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
                
                int absDifferenceX = Math.abs(x - this.getX());
                int absDifferenceY = Math.abs(y - this.getY());
                
                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
                if (empty)
                {
                    if (absDifferenceX == absDifferenceY)
                    {
                        int differenceX = x - this.getX();
                        int differenceY = y - this.getY();
                        int difference = Math.abs(differenceX);
                        
                        if (differenceX < 0 && differenceY < 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,Piece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX < 0 && differenceY > 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,Piece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX > 0 && differenceY < 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,Piece.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else if (differenceX > 0 && differenceY > 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() + i,Piece.class).isEmpty())
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
