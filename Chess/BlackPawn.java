import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackPawn extends Black
{
    int krok_dva;

    public void act() 
    {
        Chessboard sachovnica = (Chessboard)getWorld();
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
                boolean prazdneC = getWorld().getObjectsAt(x,y,Black.class).isEmpty();

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
                            Chessboard sachovnica = (Chessboard)getWorld();
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
                        Chessboard sachovnica = (Chessboard)getWorld();
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