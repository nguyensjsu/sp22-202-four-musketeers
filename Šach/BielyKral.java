import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BielyKral extends Biely
{
    int pocetPohybov = 0;
    
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
                
                int vzdX = Math.abs(x - this.getX());
                int vzdY = Math.abs(y - this.getY());
                
                boolean prazdne = getWorld().getObjectsAt(x,y,Biely.class).isEmpty();
                
                if (prazdne)
                {
                    if (vzdX <= 1 && vzdY <= 1)
                    {
                        presun(x,y);
                        pocetPohybov++;
                    }
                    else if (pocetPohybov == 0)   //rosada
                    {
                        Sachovnica sachovnica = (Sachovnica)getWorld();
                        if (x == 2 && y == 7)
                        {
                            if (sachovnica.getObjectsAt(1,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(2,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(3,7,Figurky.class).isEmpty())
                            {
                                BielaVeza bv = sachovnica.getObjects(BielaVeza.class).get(0);
                                if (bv.pocetPohybov == 0)
                                {
                                    presun(x,y);
                                    bv.setLocation(3,7);
                                }
                            }
                        }
                        else if (x == 6 && y == 7)
                        {
                            if (sachovnica.getObjectsAt(5,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(6,7,Figurky.class).isEmpty())
                            {
                                BielaVeza bv = sachovnica.getObjects(BielaVeza.class).get(1);
                                if (bv.pocetPohybov == 0)
                                {
                                    presun(x,y);
                                    pocetPohybov++;
                                    bv.setLocation(5,7);
                                    bv.pocetPohybov++;
                                }
                            }
                        }
                    }
                }
                
                ready = false;
            }
        }
    }
}
