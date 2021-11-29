package de.venatus247.vutils.utils.fabric;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemFabric {

    public static ItemStack createItem(Material material, String displayName, int amount) {

        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);

        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack createItem(Material material, String displayName, int amount, boolean unbreakable) {

        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setUnbreakable(unbreakable);

        item.setItemMeta(meta);
        return item;

    }

    public static ItemStack createItem(Material material, String displayName, int amount, List<String> lore) {

        ItemStack item = new ItemStack(material, amount);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);

        item.setItemMeta(meta);
        return item;

    }

    //TODO fix
    /*
    public static ItemStack createFireworkItem(String name, Color color, int power) {

        ItemStack flare = new ItemStack(Material.FIREWORK_ROCKET);
        FireworkMeta meta = (FireworkMeta) flare.getItemMeta();
        meta.setDisplayName(name);

        FireworkEffect.Builder effect = FireworkEffect.builder();

        if(color != null) {
            effect.withColor(color).with(FireworkEffect.Type.BALL).trail(false).flicker(false);
        }

        meta.setPower(power);

        flare.setItemMeta(meta);
        return flare;

    }*/

}
