package de.venatus247.levelborder.controllers;

import de.venatus247.vutils.VUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldBorderController {

    public void updateSizeToAll(int size, int borderExpandTime) {
        for(Player players : Bukkit.getOnlinePlayers()) {
            updatePlayerBorder(players, size, borderExpandTime);
        }
    }

    public void updatePlayerBorder(Player player, int size, int borderExpandTime) {
        VUtils.getInstance().getWorldBorderApi().setBorder(player, size, 0); //TODO fix broken expand animation
    }

    public void setPlayerBorder(Player player, int size) {
        VUtils.getInstance().getWorldBorderApi().setBorder(player, size, getWorldBorderCenter(player));
    }

    private Location getWorldBorderCenter(Player player) {
        Location spawnLocation = player.getWorld().getSpawnLocation();
        spawnLocation.setX((double) spawnLocation.getBlockX() + 0.5);
        spawnLocation.setZ((double) spawnLocation.getBlockZ() + 0.5);

        return spawnLocation;
    }

}
