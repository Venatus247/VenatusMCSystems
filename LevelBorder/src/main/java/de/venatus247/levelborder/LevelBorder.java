package de.venatus247.levelborder;

import de.venatus247.levelborder.commands.LevelBorderCommand;
import de.venatus247.levelborder.config.LevelBorderConfig;
import de.venatus247.levelborder.listeners.EnchantItemListener;
import de.venatus247.levelborder.listeners.PlayerExpChangeListener;
import de.venatus247.levelborder.listeners.PlayerJoinListener;
import de.venatus247.vutils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelBorder extends JavaPlugin {

    private boolean vUtilsLoaded = false;
    private static final String vUtilsPluginName = "VUtils";
    private static final float vUtilsRequiredVersion = 1.01f;
    private static final String vUtilsPluginUrl = "https://www.spigotmc.org/resources/vutils.97327/";

    public static final String prefix = "§7[§6LevelBorder§7] ";

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
    public void onLoad() {
        Plugin vUtilsPlugin = getServer().getPluginManager().getPlugin(vUtilsPluginName);

        if(vUtilsPlugin == null) {
            getServer().getConsoleSender().sendMessage(prefix + "§cCould not find plugin '" + vUtilsPluginName + "'!");
            getServer().getConsoleSender().sendMessage(prefix + "§cYou can download VUtils here: §7" + vUtilsPluginUrl);
            return;
        } else {
            if(Float.parseFloat(vUtilsPlugin.getDescription().getVersion()) < vUtilsRequiredVersion) {
                getServer().getConsoleSender().sendMessage(prefix + "§cOutdated version of '" + vUtilsPluginName + "' found!");
                getServer().getConsoleSender().sendMessage(prefix + "§cYou can download the latest version of VUtils here: §7" + vUtilsPluginUrl);
                return;
            }
        }
        vUtilsLoaded = true;
    }

    @Override
    public void onEnable() {
        instance = this;

        if(!vUtilsLoaded) {
            disablePlugin();
            return;
        }

        try {
            borderConfig = new LevelBorderConfig();
        } catch (IOException e) {
            e.printStackTrace();
            disablePlugin();
            return;
        }

        borderSize = borderConfig.getCurrentBorderSize();
        borderExpandTime = borderConfig.getBorderExpandTime();

        prepareBorders();
        registerEvents(getServer().getPluginManager());
        registerCommands();
    }

    @Override
    public void onDisable() {
        if(borderConfig != null)
            borderConfig.save();
    }

    private void disablePlugin() {
        System.err.println("Disabling LevelBorder due to an error!");
        Bukkit.getPluginManager().disablePlugin(this);
    }

    private void registerCommands() {
        getCommand("levelborder").setExecutor(new LevelBorderCommand());
    }

    private void registerEvents(PluginManager pluginManager) {
        listeners.add(new PlayerExpChangeListener());
        listeners.add(new EnchantItemListener());
        listeners.add(new PlayerJoinListener());

        for(Listener listener : listeners) {
            pluginManager.registerEvents(listener, this);
            System.out.println("Registered " + listener.getClass().getSimpleName());
        }
    }

    private void printWorldNotFoundErrorAndDisable(String worldName) {
        Logger.getInstance().log(prefix + "§cWorld '" + borderConfig.getOverWorldName() + "' could not be found");
        disablePlugin();
    }

    private void prepareBorders() {
        World world = Bukkit.getWorld(borderConfig.getOverWorldName());
        if(world == null) {
            printWorldNotFoundErrorAndDisable(borderConfig.getOverWorldName());
            return;
        }
        worldBorder = world.getWorldBorder();
        prepareBorder(world, worldBorder);

        World worldNether = Bukkit.getWorld(borderConfig.getNetherWorldName());
        if(worldNether == null) {
            printWorldNotFoundErrorAndDisable(borderConfig.getNetherWorldName());
            return;
        }
        netherBorder = worldNether.getWorldBorder();
        prepareBorder(worldNether, netherBorder);

        World worldEnd = Bukkit.getWorld(borderConfig.getEndWorldName());
        if(worldEnd == null) {
            printWorldNotFoundErrorAndDisable(borderConfig.getEndWorldName());
            return;
        }
        endBorder = worldEnd.getWorldBorder();
        prepareBorder(worldEnd, endBorder);
    }

    public void setWorldSpawnLocation(World world, Location location) {
        WorldBorder border = world.getWorldBorder();
        world.setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ(), 0.0f);
        border.setSize(borderSize);
        border.setCenter(location);
    }

    private void prepareBorder(World world, WorldBorder border) {
        Location spawnLocation = world.getSpawnLocation();
        setWorldSpawnLocation(world, spawnLocation);
    }

    public void updateBorder(int level, float exp) {
        if(level != borderConfig.getLevel()) {
            borderSize = level + 1;

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

    public int getLevel() {
        return borderConfig.getLevel();
    }
    public float getExp() {
        return borderConfig.getExp();
    }

}
