package models;

public class CountDownTimer {
    private int minutes ;               //Minutes
    private int seconds ;               //Seconds
    private int hundthsecs ;            //Hundredth of a second
    private boolean isTimerRunning;     //is the Timer running ?

    public CountDownTimer(int minutes, int seconds, int hundthsecs){
        this.minutes = minutes;
        this.seconds = seconds;
        this.hundthsecs = hundthsecs;
        isTimerRunning = false;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getHundthsecs() {
        return hundthsecs;
    }

    public void setHundthsecs(int hundthsecs) {
        this.hundthsecs = hundthsecs;
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public void setTimerRunning(boolean timerRunning) {
        isTimerRunning = timerRunning;
    }

    //Timer make a countdown
    public boolean countDown(){
        if (minutes != 0){
            if (seconds != 0) {
                if (hundthsecs != 0) hundthsecs--;
                else {seconds--; hundthsecs = 59;}
            } else {
                if (hundthsecs != 0) hundthsecs--;
                else {minutes--; seconds = 59; hundthsecs = 59;}
            }
        } else {
            if (seconds != 0) {
                if (hundthsecs != 0) hundthsecs--;
                else {seconds--; hundthsecs = 59;}
            } else {
                if (hundthsecs != 0) hundthsecs--;
                else return false;
            }
        }
        return true;
    }
}
