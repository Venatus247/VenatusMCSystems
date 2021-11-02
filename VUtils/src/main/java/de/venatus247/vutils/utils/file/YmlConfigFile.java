package de.venatus247.vutils.utils.file;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class YmlConfigFile {

    protected File file;
    protected FileConfiguration config;

    public YmlConfigFile(File file) {
        this.file = file;

        if(!fileExists()) {
            try {
                createFile();
                config = YamlConfiguration.loadConfiguration(file);
                loadDefaults(true);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        load();
    }

    public abstract boolean loadDefaults(boolean writeToFile);

    protected void load() {
        config = YamlConfiguration.loadConfiguration(file);
        loadDefaults(false);
    }

    private boolean fileExists() {
        return file.exists() && file.isFile();
    }

    protected boolean createFile() throws IOException {
        if(fileExists()) return true;
        if(file.getParentFile().mkdirs())
            return file.createNewFile();

        return false;

    }

    public boolean save() {
        try {
            config.save(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String path) {
        return config.get(path);
    }

    public boolean getBoolean(String path) {
        return config.getBoolean(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public long getLong(String path) {
        return config.getLong(path);
    }

    public float getFloat(String path) {
        return (float) config.getDouble(path);
    }

    public double getDouble(String path) {
        return config.getDouble(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public List<?> getList(String path) {
        return config.getList(path);
    }

    public List<?> getList(String path, List<?> reference) {
        return config.getList(path, reference);
    }

    public ItemStack getItemStack(String path) {
        return config.getItemStack(path);
    }

    public Color getColor(String path) {
        return config.getColor(path);
    }

    public Location getLocation(String path) {
        return config.getLocation(path);
    }

    public void set(String path, Object val) {
        config.set(path, val);
    }
}
