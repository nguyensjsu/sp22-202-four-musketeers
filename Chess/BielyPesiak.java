import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BielyPesiak extends Biely
{
    int krok_dva;
    
    public void act() 
    {
        Sachovnica sachovnica = (Sachovnica)getWorld();
        if (sachovnica.tahy % 2 == 1)
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
                
                boolean prazdneF = getWorld().getObjectsAt(x,y,Figurky.class).isEmpty();
                boolean prazdneB = getWorld().getObjectsAt(x,y,Biely.class).isEmpty();
                
                if (prazdneF)
                {
                    if (this.getY() == 6)
                    {
                        if (x == this.getX() && y == this.getY() - 1)
                        {
                            presun(x,y);
                        }
                        else if (x == this.getX() && y == this.getY() - 2)
                        {
                            Sachovnica sachovnica = (Sachovnica)getWorld();
                            krok_dva = sachovnica.tahy;
                            presun(x,y);
                        }
                    }
                    else if (x == this.getX() && y == this.getY() - 1)
                    {
                        presun(x,y);
                    }
                    else if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        Sachovnica sachovnica = (Sachovnica)getWorld();
                        if (sachovnica.getObjectsAt(x,y + 1,CiernyPesiak.class).size() != 0)
                        {
                            CiernyPesiak cPesiak = sachovnica.getObjectsAt(x,y + 1,CiernyPesiak.class).get(0);
                            if (cPesiak.krok_dva == sachovnica.tahy - 1)
                            {
                                presun(x,y);
                                sachovnica.removeObject(cPesiak);
                            }
                        }
                    }
                }
                else if (prazdneB)
                {
                    if ((x == this.getX() - 1 || x == this.getX() + 1) && y == this.getY() - 1)
                    {
                        presun(x,y);
                    }
                }
                
                ready = false;
            }
        }
    }
}