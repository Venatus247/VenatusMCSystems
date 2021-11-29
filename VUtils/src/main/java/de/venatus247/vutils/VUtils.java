package de.venatus247.vutils;

import de.venatus247.vutils.utils.file.VUtilsConfig;
import de.venatus247.vutils.utils.handlers.player.InventoryGuiHandler;
import de.venatus247.vutils.utils.handlers.player.PlayerQuitHandler;
import de.venatus247.vutils.utils.handlers.timer.PlayTimerHandler;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormat;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormatter;
import de.venatus247.vutils.utils.worldborder.PersistenceWrapper;
import de.venatus247.vutils.utils.worldborder.api.IWorldBorderApi;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;

import javax.management.InstanceAlreadyExistsException;
import java.util.Locale;

public class VUtils {

    public static final String prefix = "§7[§5VUtils§7] ";

    private final String spigotVersion;

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

    private IWorldBorderApi worldBorderApi;

    public VUtils(Main main) throws Exception {
        if(instance != null)
            throw new InstanceAlreadyExistsException("VUtils already instantiated");

        instance = this;

        spigotVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].toLowerCase(Locale.ROOT);

        this.main = main;
        this.console = main.getServer().getConsoleSender();

        configFile = new VUtilsConfig();

        //select border imports based on current minecraft version
        switch (spigotVersion) {
            case "v1_17_r1" -> worldBorderApi = new de.venatus247.vutils.utils.worldborder.v1_17_r1.VBorder();
            default -> {
                getConsole().sendMessage(prefix + " §cUnsupported version of Minecraft!");
                getConsole().sendMessage(prefix + " §cPlase see for supported versions here: §7https://www.spigotmc.org/resources/level-border.97328/");
                disablePlugin();
                throw new Exception("");
            }
        }
        worldBorderApi = new PersistenceWrapper(main, worldBorderApi);
        main.getServer().getServicesManager().register(IWorldBorderApi.class, worldBorderApi, main, ServicePriority.High);

        inventoryGuiHandler = new InventoryGuiHandler();
        playerQuitHandler = new PlayerQuitHandler();

        timerHandler = new PlayTimerHandler(TimerStringFormatter.getFromFormat(configFile.getTimerStyle()), configFile.getTimerTime());

        registerHandlersInPluginManager(Bukkit.getServer().getPluginManager());
    }

    private void registerHandlersInPluginManager(PluginManager pluginManager) {
        pluginManager.registerEvents(playerQuitHandler, main);
        pluginManager.registerEvents(inventoryGuiHandler, main);
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


    private void disablePlugin() {
        getConsole().sendMessage(prefix + "§7Disabling VUtils due to an error!");
        Bukkit.getPluginManager().disablePlugin(main);
    }

    public IWorldBorderApi getWorldBorderApi() {
        return worldBorderApi;
    }
}
