import greenfoot.*;
 
public class TimerToggleButton extends Button
{
    public TimerToggleButton() {
        setImage("timerOn.png");
    }
    
    @Override
    public void act() {
        Chessboard chessboard = (Chessboard)getWorld();
        if (Greenfoot.mouseClicked(this) && Greenfoot.getMouseInfo().getButton() == 1) {
            if(chessboard.gameStart) {
                chessboard.isTimerOn = !chessboard.isTimerOn;
                
                if(chessboard.isTimerOn) setImage("timerOn.png");
                else setImage("timerOff.png");
            }
        }
    }

}
