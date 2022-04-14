import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Black extends ChessPiece
{
    protected void capture()
    {
        if (this.isTouching(White.class))
        {
            White white = getWorld().getObjectsAt(this.getX(),this.getY(),White.class).get(0);
            getWorld().removeObject(white);
        }
    }
}
