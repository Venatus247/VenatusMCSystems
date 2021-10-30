package de.venatus247.levelborder;

import de.venatus247.levelborder.config.LevelBorderConfig;
import de.venatus247.levelborder.listeners.EnchantItemListener;
import de.venatus247.levelborder.listeners.PlayerExpChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelBorder extends JavaPlugin {

    private static LevelBorder instance;

    private LevelBorderConfig borderConfig;

    public static LevelBorder getInstance() {
        return instance;
    }

    private final List<Listener> listeners = new ArrayList<>();

    private int borderSize = 0;
    private long borderExpandTime = 1;

    private WorldBorder worldBorder;
    private WorldBorder netherBorder;
    private WorldBorder endBorder;

    @Override
    public void onEnable() {
        instance = this;
        try {
            borderConfig = new LevelBorderConfig();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Disabling LevelBorder due to an error!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        borderSize = borderConfig.getCurrentBorderSize();
        borderExpandTime = borderConfig.getBorderExpandTime();

        prepareBorders();
        registerEvents(getServer().getPluginManager());
    }

    @Override
    public void onDisable() {

    }

    private void registerEvents(PluginManager pluginManager) {
        listeners.add(new PlayerExpChangeListener());
        listeners.add(new EnchantItemListener());

        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
            System.out.println("Registered " + listener.getClass().getSimpleName());
        }
    }

    private void prepareBorders() {
        World world = Bukkit.getWorld("world");
        worldBorder = world.getWorldBorder();
        prepareBorder(world, worldBorder);

        World worldNether = Bukkit.getWorld("world_nether");
        netherBorder = worldNether.getWorldBorder();
        prepareBorder(worldNether, netherBorder);

        World worldEnd = Bukkit.getWorld("world_the_end");
        endBorder = worldEnd.getWorldBorder();
        prepareBorder(worldEnd, endBorder);
    }

    private void prepareBorder(World world, WorldBorder border) {
        world.setSpawnLocation(0, 0, 0, 0.0f);
        border.setSize(borderSize);
    }

    public void updateBorder(int level, float exp) {

        if(level != borderSize) {
            borderSize = level;

            worldBorder.setSize(borderSize, borderExpandTime);
            netherBorder.setSize(borderSize, borderExpandTime);
            endBorder.setSize(borderSize, borderExpandTime);

            borderConfig.setCurrentBorderSize(borderSize);
            borderConfig.save();
        }

        for(Player players : Bukkit.getOnlinePlayers()) {
            players.setExp(exp);
            players.setLevel(level);
        }
    }

}
