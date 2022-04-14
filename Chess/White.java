import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class White extends ChessPiece
{
    protected void capture()
    {
        if (this.isTouching(Black.class))
        {
            Black black = getWorld().getObjectsAt(this.getX(),this.getY(),Black.class).get(0);
            getWorld().removeObject(black);
        }
    }
}
