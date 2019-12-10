package pkg223jfinal;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer extends Timer {

    //Time is in milliseconds
    private int timeRemaining; //Time left on the timer
    private int initialTimeRemaining; //The initial amount of time the timer had
    private int delay; //Delay before timer begins
    private int period; //The amount of time between each execution of the TimerTask

    public GameTimer(int score) {
        super();

        //Instantiate timeRemaining, based on our score
        //timeRemaining decreases as score increases
        if (score > 30) {
            timeRemaining = 500;
        } else if (score > 20) {
            timeRemaining = 750;
        } else if (score > 10) {
            timeRemaining = 1000;
        } else {
            timeRemaining = 1500;
        }

        initialTimeRemaining = timeRemaining;
        delay = 0;
        period = 1;
    }

    //Decrements timeRemaining
    public int countdown() {
        return --timeRemaining;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getInitialTimeRemaining() {
        return initialTimeRemaining;
    }

    public int getDelay() {
        return delay;
    }

    public int getPeriod() {
        return period;
    }
}
