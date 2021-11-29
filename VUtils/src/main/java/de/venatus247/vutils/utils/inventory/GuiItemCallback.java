package de.venatus247.vutils.utils.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;

public interface GuiItemCallback {

    void onClick(GuiInventory inventory, InventoryClickEvent event);

}
