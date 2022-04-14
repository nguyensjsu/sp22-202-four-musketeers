import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackKnight extends Black
{
    public void act() 
    {
        ChessBoard chessboard = (ChessBoard)getWorld();
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
                
                boolean square1 = (x == this.getX() - 2 && y == this.getY() - 1);
                boolean square2 = (x == this.getX() - 2 && y == this.getY() + 1);
                boolean square3 = (x == this.getX() - 1 && y == this.getY() - 2);
                boolean square4 = (x == this.getX() - 1 && y == this.getY() + 2);
                boolean square5 = (x == this.getX() + 1 && y == this.getY() - 2);
                boolean square6 = (x == this.getX() + 1 && y == this.getY() + 2);
                boolean square7 = (x == this.getX() + 2 && y == this.getY() - 1);
                boolean square8 = (x == this.getX() + 2 && y == this.getY() + 1);
                
                boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                
                if (empty)
                {
                    if (square1 || square2 || square3 || square4 || square5 || square6 || square7 || square8)
                    {
                        move(x,y);
                    }
                }
                
                ready = false;
            }
        }
    }   
}
