import greenfoot.*;

public class EasyDifficultySelectButton extends DifficultySelectButton {
    
    private GreenfootImage img;
    
    public EasyDifficultySelectButton() {
        
        // display timer text
        img = new GreenfootImage(100,50);
        img.setColor(greenfoot.Color.GREEN);
        Font font = img.getFont().deriveFont(30f);
        img.setFont(font);
        img.drawString("Easy",15,30);
        setImage(img);
    }
    
    @Override
    public void act() {
        Chessboard chessboard = (Chessboard)getWorld();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if(chessboard.gameStart) {
                chessboard.TURN_TIME = 30;
                chessboard.timerActor.startTimer();
            }
        }
    }
}