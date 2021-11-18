package de.venatus247.levelborder.listeners;

import de.venatus247.levelborder.LevelBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerExpChangeListener implements Listener {

    @EventHandler
    public void onLevelChange(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        LevelBorder.getInstance().updateLevelAndExp(player.getLevel(), player.getExp());
    }

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        LevelBorder.getInstance().updateLevelAndExp(player.getLevel(), player.getExp());
    }

}
