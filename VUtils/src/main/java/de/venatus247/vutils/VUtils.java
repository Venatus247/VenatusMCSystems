package de.venatus247.vutils;

import de.venatus247.vutils.utils.file.VUtilsConfig;
import de.venatus247.vutils.utils.handlers.InventoryGuiHandler;
import de.venatus247.vutils.utils.handlers.player.PlayerQuitHandler;
import de.venatus247.vutils.utils.handlers.timer.PlayTimerHandler;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormat;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormatter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;

import javax.management.InstanceAlreadyExistsException;

public class VUtils {

    public static final String prefix = "§7[§5VUtils§7] ";

    private static VUtils instance = null;
    public static VUtils getInstance() {
        return instance;
    }

    private final Main main;

    private final ConsoleCommandSender console;

    private final VUtilsConfig configFile;
    private final InventoryGuiHandler inventoryGuiHandler;

    private final PlayTimerHandler timerHandler;

    private final PlayerQuitHandler playerQuitHandler;

    public VUtils(Main main) throws InstanceAlreadyExistsException {
        if(instance != null)
            throw new InstanceAlreadyExistsException("VUtils already instantiated");

        instance = this;

        this.main = main;
        this.console = main.getServer().getConsoleSender();

        configFile = new VUtilsConfig();
        inventoryGuiHandler = new InventoryGuiHandler();

        playerQuitHandler = new PlayerQuitHandler();

        timerHandler = new PlayTimerHandler(TimerStringFormatter.getFromFormat(configFile.getTimerStyle()), configFile.getTimerTime());

        registerHandlersInPluginManager(Bukkit.getServer().getPluginManager());
    }

    private void registerHandlersInPluginManager(PluginManager pluginManager) {
        pluginManager.registerEvents(playerQuitHandler, main);
    }

    public Main getMain() {
        return main;
    }

    public ConsoleCommandSender getConsole() {
        return console;
    }

    public VUtilsConfig getConfigFile() {
        return configFile;
    }

    public InventoryGuiHandler getInventoryGuiHandler() {
        return inventoryGuiHandler;
    }

    public PlayTimerHandler getTimerHandler() {
        return timerHandler;
    }

    public PlayerQuitHandler getPlayerQuitHandler() {
        return playerQuitHandler;
    }

    public void setTimerFormat(TimerStringFormat format) {
        timerHandler.setTimerStringFormatter(TimerStringFormatter.getFromFormat(format));
        configFile.setTimerFormat(format);
    }

}
