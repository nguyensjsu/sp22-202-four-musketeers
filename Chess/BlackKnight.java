import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackKnight extends Black
{
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
                
                boolean tile1 = (x == this.getX() - 2 && y == this.getY() - 1);
                boolean tile2 = (x == this.getX() - 2 && y == this.getY() + 1);
                boolean tile3 = (x == this.getX() - 1 && y == this.getY() - 2);
                boolean tile4 = (x == this.getX() - 1 && y == this.getY() + 2);
                boolean tile5 = (x == this.getX() + 1 && y == this.getY() - 2);
                boolean tile6 = (x == this.getX() + 1 && y == this.getY() + 2);
                boolean tile7 = (x == this.getX() + 2 && y == this.getY() - 1);
                boolean tile8 = (x == this.getX() + 2 && y == this.getY() + 1);
                
                boolean empty = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                
                if (empty)
                {
                    if (tile1 || tile2 || tile3 || tile4 || tile5 || tile6 || tile7 || tile8)
                    {
                        move(x,y);
                    }
                }
                
                ready = false;
            }
        }
    }   
}
