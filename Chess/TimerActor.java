import greenfoot.*;

import java.util.function.*;

public class TimerActor extends Actor {
    private final SimpleTimer timer;
    private static TimerActor timerActor;

    private TimerActor() {
        timer = new SimpleTimer();
        startTimer();
    }

    public synchronized static TimerActor getInstance() {
        if (timerActor == null) {
            timerActor = getNewInstance();
        }
        return timerActor;
    }

    public synchronized static TimerActor getNewInstance() {
        return new TimerActor();
    }

    public void startTimer() {
        timer.mark();
    }

    public int checkTimer() {
        return timer.millisElapsed() / 1000;
    }

    public String displayTimer(int rawSeconds,
                               Function<Integer, String> minutesDec,
                               Function<Integer, String> secondsDec) {

        return minutesDec.apply(rawSeconds) + secondsDec.apply(rawSeconds);
    }

    public void act() {
    }
}
