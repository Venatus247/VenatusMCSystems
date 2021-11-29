package de.venatus247.vutils.utils.inventory;

import org.bukkit.inventory.ItemStack;

public class GuiItem {

    private ItemStack itemStack;
    private GuiItemCallback callback;

    public GuiItem(ItemStack itemStack, GuiItemCallback callback) {
        this.itemStack = itemStack;
        this.callback = callback;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public GuiItemCallback getCallback() {
        return callback;
    }
}
