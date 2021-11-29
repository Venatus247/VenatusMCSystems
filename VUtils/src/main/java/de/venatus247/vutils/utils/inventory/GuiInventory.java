package de.venatus247.vutils.utils.inventory;

import de.venatus247.vutils.VUtils;
import de.venatus247.vutils.utils.handlers.IHandlerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Objects;

public abstract class GuiInventory implements IGuiInventory {

    protected String title;
    protected Inventory inventory;
    protected HashMap<Integer, GuiItem> items = new HashMap<>();
    protected int rows;

    private IHandlerListener<InventoryClickEvent> handle;

    public GuiInventory(String title, int rows) {
        this.title = title;
        this.rows = rows;
        registerHandler();
    }

    protected boolean addItem(GuiItem item, int position) {
        if(items.containsKey(position))
            return false;

        items.put(position, item);
        return true;
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    protected Inventory getInventory() {
        if(inventory == null) inventory = build();
        return inventory;
    }

    private Inventory build() {
        loadItems();
        Inventory inventory = Bukkit.createInventory(null, rows*9, title);
        insertItemsInInventory(inventory);
        return inventory;
    }

    private void registerHandler() {
        handle = event -> {
            if(!Objects.equals(event.getClickedInventory(), inventory)) return;
            for(GuiItem item : items.values()) {
                if(!item.getItemStack().equals(event.getCurrentItem())) continue;
                item.getCallback().onClick(this, event);
                break;
            }
        };
        VUtils.getInstance().getInventoryGuiHandler().registerListener(handle);
    }

    private void unregisterHandler() {
        VUtils.getInstance().getInventoryGuiHandler().unregisterListener(handle);
    }

    public void close() {
        unregisterHandler();
    }

    protected void insertItemsInInventory(Inventory inventory) {
        items.forEach((position, item) -> {
            inventory.setItem(position, item.getItemStack());
        });
    }

    protected abstract void loadItems();

}
