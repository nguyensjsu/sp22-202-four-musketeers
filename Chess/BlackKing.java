import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BlackKing extends Black
{
    int pocetPohybov = 0;
    
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
                
                int vzdX = Math.abs(x - this.getX());
                int vzdY = Math.abs(y - this.getY());
                
                boolean prazdne = getWorld().getObjectsAt(x,y,Black.class).isEmpty();
                
                if (prazdne)
                {
                    if (vzdX <= 1 && vzdY <= 1)
                    {
                        presun(x,y);
                        pocetPohybov++;
                    }
                    else if (pocetPohybov == 0)   //rosada
                    {
                        Chessboard sachovnica = (Chessboard)getWorld();
                        if (x == 1 && y == 7)
                        {
                            if (sachovnica.getObjectsAt(1,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(2,7,Figurky.class).isEmpty())
                            {
                                CiernaVeza cv = sachovnica.getObjects(CiernaVeza.class).get(0);
                                if (cv.pocetPohybov == 0)
                                {
                                    presun(x,y);
                                    cv.setLocation(2,7);
                                }
                            }
                        }
                        else if (x == 5 && y == 7)
                        {
                            if (sachovnica.getObjectsAt(4,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(5,7,Figurky.class).isEmpty() && sachovnica.getObjectsAt(6,7,Figurky.class).isEmpty())
                            {
                                CiernaVeza cv = sachovnica.getObjects(CiernaVeza.class).get(1);
                                if (cv.pocetPohybov == 0)
                                {
                                    presun(x,y);
                                    pocetPohybov++;
                                    cv.setLocation(4,7);
                                    cv.pocetPohybov++;
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
