/**
 * Write a description of class ScrollButtons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import greenfoot.*;
import java.util.*;

public class ScrollButton extends Actor implements IScrollButtonSubject  
{
    private int x;
    private int y;
    private GreenfootImage arrow;
    private String direction;
    
    private ArrayList<IScrollButtonObserver> observers;

    
    /**
     * Constructor for objects of class ScrollButtons
     */
    
    
    public ScrollButton(String arrowImage,String arrowDirection)
    {
        observers = new ArrayList<>();
        arrow = new GreenfootImage(arrowImage);
        setImage(arrow);
        direction = arrowDirection;
    }
    
    public void act() 
    {
        buttonClick();
    } 
    
    public void notifyObservers() {
        for(IScrollButtonObserver obs: observers) 
            obs.buttonEvent(direction);
    }
    
    public void addObserver(IScrollButtonObserver obs) {
        observers.add(obs);
    }
    
    public void removeObserver(IScrollButtonObserver obs) {
        observers.remove(obs);
    }
    
    public void buttonClick() {
        if(Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            notifyObservers();
        }
    }
}
