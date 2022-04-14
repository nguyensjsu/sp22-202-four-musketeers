import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhitePawn extends White
{
    int stepTwo;
    
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
                
                boolean emptyF = getWorld().getObjectsAt(x,y,ChessPiece.class).isEmpty();
                boolean emptyB = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
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
                            Chessboard chessBoard = (Chessboard)getWorld();
                            stepTwo = chessBoard.move;
                            move(x,y);
                        }
                    }
                    else if (x == this.getX() && y == this.getY() - 1)
                    {
                        move(x,y);
                    }
                    else if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        Chessboard chessBoard = (Chessboard)getWorld();
                        if (chessBoard.getObjectsAt(x,y + 1,BlackPawn.class).size() != 0)
                        {
                            BlackPawn blackPawn = chessBoard.getObjectsAt(x,y + 1,BlackPawn.class).get(0);
                            if (blackPawn.stepTwo == chessBoard.move - 1)
                            {
                                move(x,y);
                                chessBoard.removeObject(blackPawn);
                            }
                        }
                    }
                }
                else if (emptyB)
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