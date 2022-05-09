import java.util.ArrayList;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class rookPromotionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class rookPromotionButton extends PromotionButton
{
    public rookPromotionButton()
    {        
        super(new ArrayList<>(Arrays.asList("grayedRookButton.png", "whiteRookButton.png", "blackRookButton.png")));
    }
    
    @Override
    public ChessPiece getPiece() 
    {
        return new Rook(this.getColor());
    }
    
    public void act()
    {
        System.out.println("acted");
    }
}
