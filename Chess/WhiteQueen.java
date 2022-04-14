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

                int absDiffX = Math.abs(x - this.getX());
                int absDiffY = Math.abs(y - this.getY());

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
                                if (!getWorld().getObjectsAt(x,this.getY() - i,ChessPiece.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() - i,y,ChessPiece.class).isEmpty())
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
                        }
                    }
                    else if (absDiffX == absDiffY)
                    {
                        int differenceX = x - this.getX();
                        int differenceY = y - this.getY();
                        int difference = Math.abs(differenceX);

                        if (differenceX < 0 && differenceY < 0)
                        {
                            for (int i = 1;i < difference;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,ChessPiece.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,ChessPiece.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,ChessPiece.class).isEmpty())
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
