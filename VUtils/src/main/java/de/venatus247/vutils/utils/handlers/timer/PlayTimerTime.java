package de.venatus247.vutils.utils.handlers.timer;

public class PlayTimerTime {

    private int days;
    private int hours;
    private int minutes;
    private int seconds;

    public PlayTimerTime(long time) {
        while (time >= 86400) { //24*60*60 (seconds a day has)
            days++;
            time -= 86400;
        }
        while (time >= 3600) { //60*60 (seconds an hour has)
            hours++;
            time -= 3600;
        }
        while (time >= 60) {
            minutes++;
            time -= 60;
        }
        seconds = (int)time;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
