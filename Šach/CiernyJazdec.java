import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class CiernyJazdec extends Cierny
{
    public void act() 
    {
        Sachovnica sachovnica = (Sachovnica)getWorld();
        if (sachovnica.tahy % 2 == 0)
        {
            vyber();
            pohyb();
            zmenaStavu();
            vybytie();
        }
    }
    
    private void pohyb()
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
                
                boolean prazdne = getWorld().getObjectsAt(x,y,Cierny.class).isEmpty();
                
                if (prazdne)
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
