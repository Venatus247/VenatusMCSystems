package de.venatus247.levelborder.listeners;

import de.venatus247.levelborder.LevelBorder;
import de.venatus247.vutils.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class PlayerChangedWorldListener implements Listener {

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if(event.getPlayer().getWorld().getName().equals(LevelBorder.getInstance().getBorderConfig().getNetherWorldName())
                && !LevelBorder.getInstance().getBorderConfig().isNetherEntered()) {
            LevelBorder.getInstance().setWorldSpawnLocation(player.getWorld(), player.getLocation());
            LevelBorder.getInstance().getBorderConfig().setNetherEntered(true);
            Logger.getInstance().log("Setting Nether border center");
        } else if(event.getPlayer().getWorld().getName().equals(LevelBorder.getInstance().getBorderConfig().getEndWorldName())
                && !LevelBorder.getInstance().getBorderConfig().isEndEntered()) {
            LevelBorder.getInstance().setWorldSpawnLocation(player.getWorld(), player.getLocation());
            LevelBorder.getInstance().getBorderConfig().setEndEntered(true);
        }
        LevelBorder.getInstance().setPlayerBorder(player);
    }

}
