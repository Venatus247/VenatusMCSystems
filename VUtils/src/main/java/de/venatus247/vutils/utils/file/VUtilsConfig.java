package de.venatus247.vutils.utils.file;

import de.venatus247.vutils.VUtils;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormat;

import java.io.File;

public class VUtilsConfig extends YmlConfigFile {

    public VUtilsConfig() {
        super(new File("plugins/VUtils", "config.yml"));
    }

    @Override
    public boolean loadDefaults(boolean writeToFile) {
        config.addDefault("timer.time", (long)0);
        config.addDefault("timer.enabled", true);
        config.addDefault("timer.visible", true);
        config.addDefault("timer.format", TimerStringFormat.SHORT.toString());

        if (writeToFile) {
            config.options().copyDefaults(true);
            return save();
        }

        return true;
    }

    public long getTimerTime() {
        return getLong("timer.time");
    }
    public TimerStringFormat getTimerStyle() {
        return TimerStringFormat.valueOf(getString("timer.format"));
    }

    public void setTimerEnabled(boolean enabled) {
        set("timer.enabled", enabled);
    }

    public void setTimerVisible(boolean visible) {
        set("timer.visible", visible);
    }

    public void setTimerTime(long time) {
        set("timer.time", time);
    }

    public void setTimerFormat(TimerStringFormat format) {
        set("timer.format", format.toString());
    }

}
