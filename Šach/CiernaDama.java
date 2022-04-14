import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * @author Pavol Biacko
 * @version date: 10.5.2020
 */

public class CiernaDama extends Cierny
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
                
                int vzdX = Math.abs(x - this.getX());
                int vzdY = Math.abs(y - this.getY());
                
                boolean prazdne = getWorld().getObjectsAt(x,y,Cierny.class).isEmpty();
                
                if (prazdne)
                {
                    if (x == this.getX())
                    {
                        int rozdiel = y - this.getY();
                        
                        if (rozdiel < 0)
                        {
                            rozdiel = Math.abs(rozdiel);
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() - i,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            rozdiel = Math.abs(rozdiel);
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(x,this.getY() + i,Figurky.class).isEmpty())
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
                    else if (y == this.getY())
                    {
                        int rozdiel = x - this.getX();
                        
                        if (rozdiel < 0)
                        {
                            rozdiel = Math.abs(rozdiel);
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() - i,y,Figurky.class).isEmpty())
                                {
                                    obsadene = true;
                                    break;
                                }
                            }
                        }
                        else
                        {
                            rozdiel = Math.abs(rozdiel);
                            for (int i = 1;i < rozdiel;i++)
                            {
                                if (!getWorld().getObjectsAt(this.getX() + i,y,Figurky.class).isEmpty())
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
                    else if (vzdX == vzdY)
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
