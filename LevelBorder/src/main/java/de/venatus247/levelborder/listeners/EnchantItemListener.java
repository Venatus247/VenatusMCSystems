package de.venatus247.levelborder.listeners;

import de.venatus247.levelborder.LevelBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantItemListener implements Listener {

    @EventHandler
    public void onEnchant(EnchantItemEvent event) {
        Player player = event.getEnchanter();
        //LevelBorder.getInstance().updateBorder(player.getLevel(), player.getExp());
    }

}
