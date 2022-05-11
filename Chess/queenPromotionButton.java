import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class queenPromotionButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class queenPromotionButton extends PromotionButton
{
    public queenPromotionButton()
    {
        super(new ArrayList<>(Arrays.asList("grayedQueenButton.png", "whiteQueenButton.png", "blackQueenButton.png")));
    }
    
    @Override
    public ChessPiece getPiece() 
    {
        return new Queen(this.getColor());
    }
}
