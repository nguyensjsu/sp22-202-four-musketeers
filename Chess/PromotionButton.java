import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PromotionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PromotionButton extends Actor
{
    /**
     * Act - do whatever the PromotionButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public PromotionButton(String image)
    {
        GreenfootImage iconImage = new GreenfootImage(image);
        iconImage.scale(50,50);

        setImage(iconImage);
    }
    public void act()
    {
        // Add your action code here.
    }
}
