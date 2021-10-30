package de.venatus247.vutils.utils.handlers.timer;

public class ShortTimeStringFormatter extends TimerStringFormatter {
    @Override
    public String format(PlayTimerTime time, boolean running) {
        StringBuilder builder = new StringBuilder();

        builder.append("§e§l");

        if(time.getDays() > 0)
            builder.append(time.getDays())
                    .append("d ");
        if(time.getHours() > 0)
            builder.append(time.getHours())
                    .append("h ");

        if(time.getMinutes() > 0)
            builder.append(time.getMinutes())
                    .append("m ");

        builder.append(time.getSeconds())
                .append("s");

        if(!running)
            builder.append("§r §7(§bpausiert§7)");

        return builder.toString();
    }
}
