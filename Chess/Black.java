import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Black extends Figurky
{
    protected void vybytie()
    {
        if (this.isTouching(Biely.class))
        {
            Biely biely = getWorld().getObjectsAt(this.getX(),this.getY(),Biely.class).get(0);
            getWorld().removeObject(biely);
        }
    }
}
