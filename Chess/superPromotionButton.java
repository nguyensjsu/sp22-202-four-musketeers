import java.util.ArrayList;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class superPromotionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class superPromotionButton extends PromotionButton
{
    public superPromotionButton()
    {        
        super(new ArrayList<>(Arrays.asList("grayedSuperButton.png", "whiteSuperButton.png", "blackSuperButton.png")));
    }
    
    @Override
    public ChessPiece getPiece() 
    {
        return new Super(this.getColor());
    }
}
