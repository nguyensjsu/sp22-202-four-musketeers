import greenfoot.*;

public class TimerActor extends Actor
{

    private SimpleTimer timer;
    private static TimerActor timerActor;
    
    private TimerActor() {
        timer = new SimpleTimer();
        startTimer();
    }
    
    public synchronized static TimerActor getInstance() {
        if(timerActor == null) return getNewInstance();
        
        return timerActor;
    }
    
    public synchronized static TimerActor getNewInstance() {
        TimerActor timerActor = new TimerActor();
        return timerActor;
    }
    
    public void startTimer() {
        timer.mark();
    }
    
    public int checkTimer() {
        return timer.millisElapsed() / 1000;
    }
    
    public String displayTimer(int turnTime) {
        int rawSeconds = turnTime - checkTimer();
        int seconds = (rawSeconds) % 60;
        int minutes = rawSeconds / 60;
        
        String minutesPadding = "0";
        String secondsPadding = "";
        
        if(seconds < 10) secondsPadding = "0";
        
        String minutesString = minutesPadding + Integer.toString(minutes);
        String secondsString = secondsPadding + Integer.toString(seconds);
        
        String remainingTime = "Timer: " + minutesString + ":" + secondsString;
        
        return remainingTime;
    }
        
    public void act() {}
}
