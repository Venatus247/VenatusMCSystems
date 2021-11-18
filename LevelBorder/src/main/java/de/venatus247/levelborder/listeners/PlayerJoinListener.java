package de.venatus247.levelborder.listeners;

import de.venatus247.levelborder.LevelBorder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setLevel(LevelBorder.getInstance().getLevel());
        player.setExp(LevelBorder.getInstance().getExp());

        if(!player.hasPlayedBefore()) {
            player.teleport(player.getWorld().getSpawnLocation());
        }

        Bukkit.getScheduler().runTaskLater(LevelBorder.getInstance(), () -> {
            LevelBorder.getInstance().setPlayerBorder(player);
        }, 20);
    }

}
