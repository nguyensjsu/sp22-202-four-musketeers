import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class BielyStrelec extends Biely
{
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
                    if (vzdX == vzdY)
                    {
                        int rozdielX = x - this.getX();
                        int rozdielY = y - this.getY();
                        int rozdiel = Math.abs(rozdielX);
                        
                        if (rozdielX < 0 && rozdielY < 0)
                        {
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() - i,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        else if (rozdielX < 0 && rozdielY > 0)
                        {
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,this.getY() + i,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        else if (rozdielX > 0 && rozdielY < 0)
                        {
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() - i,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        else if (rozdielX > 0 && rozdielY > 0)
                        {
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,this.getY() + i,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        if (!obsadene)
                        {
                            presun(x,y);
                        }
                    }
                }
                
                ready = false;
                obsadene = false;
            }
        }
    }
}
