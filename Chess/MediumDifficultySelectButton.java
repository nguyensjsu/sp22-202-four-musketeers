import greenfoot.*;

import java.awt.Color;

public class MediumDifficultySelectButton extends DifficultySelectButton {
    
    private GreenfootImage img;
    
    public MediumDifficultySelectButton() {
        
        // display timer text
        img = new GreenfootImage(WIDTH, LENGTH);
        img.setColor(greenfoot.Color.ORANGE);
        Font font = img.getFont().deriveFont(fontSize);
        img.setFont(font);
        img.drawString("Med",FONT_WIDTH,FONT_LENGTH);
        setImage(img);
    }
    
    @Override
    public void act() {
        Chessboard chessboard = (Chessboard)getWorld();
        
        if(chessboard.dsm.getCurDifficulty() == "MediumDifficultyState") {
            img.setTransparency(255);
            setImage(img);
        }
        else {
            img.setTransparency(64);
            setImage(img);
        }
        
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if(chessboard.gameStart) {
                chessboard.TURN_TIME = 15;
                chessboard.timerActor.startTimer();
                chessboard.dsm.setMediumDifficulty();
            }
        }
    }
}