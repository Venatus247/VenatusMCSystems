package de.venatus247.vutils.utils.handlers.player;

import de.venatus247.vutils.utils.handlers.SpecificEventHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryGuiHandler extends SpecificEventHandler<InventoryClickEvent> implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        onEvent(event);
    }

}
