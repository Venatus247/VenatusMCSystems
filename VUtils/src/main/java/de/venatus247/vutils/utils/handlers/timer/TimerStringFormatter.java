package de.venatus247.vutils.utils.handlers.timer;

public abstract class TimerStringFormatter {
    public abstract String format(PlayTimerTime time, boolean running);

    public static TimerStringFormatter getFromFormat(TimerStringFormat format) {
        return switch (format) {
            case LONG -> new LongTimeStringFormatter();
            case SHORT -> new ShortTimeStringFormatter();
        };
    }

}
