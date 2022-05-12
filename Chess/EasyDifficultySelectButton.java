import greenfoot.*;

public class EasyDifficultySelectButton extends DifficultySelectButton {
    
    private GreenfootImage img;
    
    public EasyDifficultySelectButton() {
        
        // display timer text
        img = new GreenfootImage(WIDTH, LENGTH);
        img.setColor(greenfoot.Color.GREEN);
        Font font = img.getFont().deriveFont(fontSize);
        img.setFont(font);
        img.drawString("Easy",FONT_WIDTH,FONT_LENGTH);
        setImage(img);
    }
    
    @Override
    public void act() {
        Chessboard chessboard = (Chessboard)getWorld();
        
        if(chessboard.dsm.getCurDifficulty() == "EasyDifficultyState") {
            img.setTransparency(255);
            setImage(img);
        }
        else {
            img.setTransparency(64);
            setImage(img);
        }
        
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if(chessboard.gameStart) {
                chessboard.TURN_TIME = 30;
                chessboard.timerActor.startTimer();
                chessboard.dsm.setEasyDifficulty();
            }
        }
    }
}