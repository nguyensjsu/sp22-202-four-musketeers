import greenfoot.*;
import java.awt.Color;

import java.util.function.*;

public class TimerActor extends Actor {
    
    private SimpleTimer timer;
    private GreenfootImage img;
    
    private Function<Integer, String> minDec;
    private Function<Integer, String> secDec;

    public TimerActor(Function<Integer, String> minutesDec,
                      Function<Integer, String> secondsDec,
                      int turnTime) {
        timer = new SimpleTimer();
        
        minDec = minutesDec;
        secDec = secondsDec;
        
        startTimer();
        displayTimer(turnTime);
    }

    public void startTimer() {
        timer.mark();
    }

    public int checkTimer() {
        return timer.millisElapsed() / 1000;
    }

    public void displayTimer(int rawSeconds) {
        String newTime = minDec.apply(rawSeconds) + secDec.apply(rawSeconds);
        
        // display timer text
        img = new GreenfootImage(100,50);
        
        // changing color of text based on time left
        if(rawSeconds >= 10) img.setColor(greenfoot.Color.GREEN);
        else img.setColor(greenfoot.Color.RED);
        
        Font font = img.getFont().deriveFont(30f);
        img.setFont(font);
        img.drawString(newTime,15,30);
        setImage(img);
    }

    public void act() { }
}
