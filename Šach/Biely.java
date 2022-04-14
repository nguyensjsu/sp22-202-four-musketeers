import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Biely extends Figurky
{
    protected void vybytie()
    {
        if (this.isTouching(Cierny.class))
        {
            Cierny cierny = getWorld().getObjectsAt(this.getX(),this.getY(),Cierny.class).get(0);
            getWorld().removeObject(cierny);
        }
    }
}
