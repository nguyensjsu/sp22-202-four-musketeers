import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;


//this class is the parent class to all of the promotion buttons
public class PromotionButton extends Actor
{

    //0 = gray, 1 = white, 2 = black
    ArrayList<String> imgLocation;

    public PromotionButton()
    {
        imgLocation = new ArrayList<>();
    }

    public PromotionButton(ArrayList<String> image)
    {
        imgLocation = image;
        this.setIcon(imgLocation.get(0));
    }

    public void act()
    {
        // Add your action code here.
    }

    private void setIcon(String image)
    {
        GreenfootImage iconImage = new GreenfootImage(image);
        iconImage.scale(50,50);

        setImage(iconImage);
    }


}
