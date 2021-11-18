package de.venatus247.vutils.utils.worldborder.api;

import org.bukkit.entity.Player;

public interface PersistentWorldBorderApi extends IWorldBorderApi {
    WorldBorderData getWorldBorderData(Player player);
}
