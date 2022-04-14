import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteQueen extends White
{
    public void act() 
    {
        ChessBoard chessBoard = (ChessBoard)getWorld();
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

                int absX = Math.abs(x - this.getX());
                int absY = Math.abs(y - this.getY());

                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();

                if (empty)
                {
                    if (x == this.getX())
                    {
                        int diff = y - this.getY();

                        if (diff < 0)
                        {
                            diff = Math.abs(diff);
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() - i,Figurky.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            diff = Math.abs(diff);
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() + i,Figurky.class).isEmpty())
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
                        int diff = x - this.getX();

                        if (diff < 0)
                        {
                            diff = Math.abs(diff);
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,y,Figurky.class).isEmpty())
                                {
                                    occupied = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            diff = Math.abs(diff);
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,y,Figurky.class).isEmpty())
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
                    else if (absX == absY)
                    {
                        int diffX = x - this.getX();
                        int diffY = y - this.getY();
                        int diff = Math.abs(diffX);

                        if (diffX < 0 && diffY < 0)
                        {
                            for (int i = 1;i < diff;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,Figurky.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,Figurky.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,Figurky.class).isEmpty())
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
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() + i,Figurky.class).isEmpty())
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
