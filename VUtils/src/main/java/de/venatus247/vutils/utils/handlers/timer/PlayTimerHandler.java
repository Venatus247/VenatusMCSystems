package de.venatus247.vutils.utils.handlers.timer;

import de.venatus247.vutils.VUtils;
import de.venatus247.vutils.utils.fabric.ActionBarMessageFabric;
import org.bukkit.Bukkit;

public class PlayTimerHandler {

    private TimerStringFormatter timerStringFormatter;

    private boolean running = false;

    private long time;
    private long timestampNewestStart;

    private int timerTaskId = -1;

    public PlayTimerHandler(TimerStringFormatter formatter) {
        timerStringFormatter = formatter;
        time = 0;
        startTask();
        registerHandlers();
    }

    public PlayTimerHandler(TimerStringFormatter formatter, long time) {
        timerStringFormatter = formatter;
        this.time = time;
        startTask();
        registerHandlers();
    }

    private void registerHandlers() {
        VUtils.getInstance().getPlayerQuitHandler().registerListener(event -> {
            if(Bukkit.getOnlinePlayers().size() == 1) {
                pause();
            }
        });
    }

    private void startTask() {
        if(timerTaskId != -1) stopTask();
        timerTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(VUtils.getInstance().getMain(), () -> {
            String timerString = getTimerString();
            Bukkit.getOnlinePlayers().forEach(player -> ActionBarMessageFabric.sendMessage(player, timerString));
        },0,20);
    }

    private void stopTask() {
        if(timerTaskId == -1) return;
        Bukkit.getScheduler().cancelTask(timerTaskId);
        timerTaskId = -1;
    }

    public void start() {
        if(running) return;
        timestampNewestStart = System.currentTimeMillis()/1000;
        running = true;
    }

    public void pause() {
        if(!running) return;
        time += System.currentTimeMillis()/1000 - timestampNewestStart;
        timestampNewestStart = 0;
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    private String getTimerString() {
        if(running) {
            long tempTime = time + (System.currentTimeMillis()/1000 - timestampNewestStart);
            return timerStringFormatter.format(new PlayTimerTime(tempTime), true);
        }

        return timerStringFormatter.format(new PlayTimerTime(time), false);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setTimerStringFormatter(TimerStringFormatter timerStringFormatter) {
        this.timerStringFormatter = timerStringFormatter;
        stopTask();
        startTask();
    }
}
