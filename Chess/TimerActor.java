import greenfoot.*;

public class TimerActor extends Actor
{

    private SimpleTimer timer;
    private static TimerActor timerActor;
    
    // Greenfoot image class —— display with drawString
    
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
        return timer.millisElapsed();
    }
        
    public void act() {
        
    }
}
