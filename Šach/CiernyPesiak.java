import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class CiernyPesiak extends Cierny
{
    int krok_dva;

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

                boolean prazdneF = getWorld().getObjectsAt(x,y,Figurky.class).isEmpty();
                boolean prazdneC = getWorld().getObjectsAt(x,y,Cierny.class).isEmpty();

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
                        if (sachovnica.getObjectsAt(x,y + 1,BielyPesiak.class).size() != 0)
                        {
                            BielyPesiak bPesiak = sachovnica.getObjectsAt(x,y + 1,BielyPesiak.class).get(0);
                            if (bPesiak.krok_dva == sachovnica.tahy - 1)
                            {
                                presun(x,y);
                                sachovnica.removeObject(bPesiak);
                            }
                        }
                    }
                }
                else if (prazdneC)
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