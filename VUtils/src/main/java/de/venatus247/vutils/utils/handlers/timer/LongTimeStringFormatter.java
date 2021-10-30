package de.venatus247.vutils.utils.handlers.timer;

public class LongTimeStringFormatter extends TimerStringFormatter {
    @Override
    public String format(PlayTimerTime time, boolean running) {
        StringBuilder builder = new StringBuilder();

        builder.append("§e");

        if(time.getDays() > 0)
            builder.append(time.getDays())
                    .append(time.getDays() == 1 ? " Tag " : " Tage ");
        if(time.getHours() > 0)
            builder.append(time.getHours())
                    .append(time.getHours() == 1 ? " Stunde " : " Stunden ");

        if(time.getMinutes() > 0)
            builder.append(time.getMinutes())
                    .append(time.getMinutes() == 1 ? " Minute " : " Minuten ");

        builder.append(time.getSeconds())
                .append(time.getSeconds() == 1 ? " Sekunde" : " Sekunden");

        if(!running)
            builder.append("§r §7(§bpausiert§7)");

        return builder.toString();
    }
}
