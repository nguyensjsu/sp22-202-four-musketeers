import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class Figurky extends Actor
{
    boolean vybrata = false;
    boolean ready = false;
    boolean obsadene = false;

    protected void vyber()
    {
        int x = this.getX();
        int y = this.getY();

        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1)
        {
            if (getWorld().getObjects(Vyber.class).size() == 0)
            {
                pridanieVyberu(x,y);
            }
            else if (getWorld().getObjectsAt(x,y,Vyber.class).size() == 1)
            {
                vymazanieVyberu();
            }
            else
            {
                pridanieVyberu(x,y);
            }
        }
        else if (Greenfoot.mouseClicked(null) && vybrata)
        {
            vymazanieVyberu();
        }
    }

    protected void zmenaStavu()
    {
        if (vybrata)
        {
            ready = true;
        }
    }

    private void pridanieVyberu(int x,int y)
    {
        Vyber vyber = new Vyber();
        getWorld().addObject(vyber,x,y);
        vybrata = true;
    }

    private void vymazanieVyberu()
    {
        Vyber vyber = getWorld().getObjects(Vyber.class).get(0);
        getWorld().removeObject(vyber);
        vybrata = false;
    }

    protected void presun(int x,int y)
    {
        this.setLocation(x,y);
        Sachovnica sachovnica = (Sachovnica)getWorld();
        sachovnica.tahy++;
    }
}
