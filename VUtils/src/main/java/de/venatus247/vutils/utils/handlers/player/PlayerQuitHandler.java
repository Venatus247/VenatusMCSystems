package de.venatus247.vutils.utils.handlers.player;

import de.venatus247.vutils.utils.handlers.SpecificEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitHandler extends SpecificEventHandler<PlayerQuitEvent> implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        onEvent(event);
    }

}
