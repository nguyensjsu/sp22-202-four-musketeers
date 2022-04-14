import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackPawn extends Black
{
    int stepTwo;

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

                boolean emptyF = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                boolean emptyC = getWorld().getObjectsAt(x,y,Black.class).isEmpty();

                if (emptyF)
                {
                    if (this.getY() == 6)
                    {
                        if (x == this.getX() && y == this.getY() - 1)
                        {
                            move(x,y);
                        }
                        else if (x == this.getX() && y == this.getY() - 2)
                        {
                            Chessboard chessboard = (Chessboard)getWorld();
                            stepTwo = chessboard.move;
                            move(x,y);
                        }
                    }
                    else if (x == this.getX() && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                    else if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        Chessboard chessboard = (Chessboard)getWorld();
                        if (chessboard.getObjectsAt(x,y + 1,WhitePawn.class).size() != 0)
                        {
                            WhitePawn wPawn = chessboard.getObjectsAt(x,y + 1,WhitePawn.class).get(0);
                            if (wPawn.stepTwo == chessboard.move - 1)
                            {
                                move(x,y);
                                chessboard.removeObject(wPawn);
                            }
                        }
                    }
                }
                else if (emptyC)
                {
                    if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                }

                ready = false;
            }
        }
    }
}