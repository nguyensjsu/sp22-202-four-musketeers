import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class WhiteKnight extends White
{
    public void act() 
    {
        ChessBoard chessBoard = (ChessBoard)getWorld();
        if (chessBoard.move % 2 == 1)
        {
            select();
            pohyb();
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
                
                boolean policko1 = (x == this.getX() - 2 && y == this.getY() - 1);
                boolean policko2 = (x == this.getX() - 2 && y == this.getY() + 1);
                boolean policko3 = (x == this.getX() - 1 && y == this.getY() - 2);
                boolean policko4 = (x == this.getX() - 1 && y == this.getY() + 2);
                boolean policko5 = (x == this.getX() + 1 && y == this.getY() - 2);
                boolean policko6 = (x == this.getX() + 1 && y == this.getY() + 2);
                boolean policko7 = (x == this.getX() + 2 && y == this.getY() - 1);
                boolean policko8 = (x == this.getX() + 2 && y == this.getY() + 1);
                
                boolean empty = getWorld().getObjectsAt(x,y,White.class).isEmpty();
                
                if (empty)
                {
                    if (policko1 || policko2 || policko3 || policko4 || policko5 || policko6 || policko7 || policko8)
                    {
                        presun(x,y);
                    }
                }
                
                ready = false;
            }
        }
    }   
}
