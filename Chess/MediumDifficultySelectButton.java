import greenfoot.*;

import java.awt.Color;

public class MediumDifficultySelectButton extends DifficultySelectButton {
    
    private GreenfootImage img;
    
    public MediumDifficultySelectButton() {
        
        // display timer text
        img = new GreenfootImage(100,50);
        img.setColor(greenfoot.Color.ORANGE);
        Font font = img.getFont().deriveFont(30f);
        img.setFont(font);
        img.drawString("Med",15,30);
        setImage(img);
    }
    
    @Override
    public void act() {
        Chessboard chessboard = (Chessboard)getWorld();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if(chessboard.gameStart) {
                chessboard.TURN_TIME = 15;
                chessboard.timerActor.startTimer();
            }
        }
    }
}