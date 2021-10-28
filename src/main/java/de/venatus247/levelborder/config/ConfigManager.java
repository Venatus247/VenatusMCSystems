package de.venatus247.levelborder.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class ConfigManager {

    protected File file;
    protected YamlConfiguration configuration;

    public ConfigManager(File file) throws IOException {
        this.file = file;
        if(!createFile())
            throw new IOException("Could not create config file!");
    }

    public void load() {
        configuration = YamlConfiguration.loadConfiguration(file);
        loadDefaults();
    }

    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDefaults() {
    }

    private boolean createFile() {
        if(!file.getParentFile().exists()) {
            System.out.println("Creating directories");
            file.getParentFile().mkdirs();
        }
        if(!file.exists()) {
            try {
                System.out.println("Creating config file");
                file.createNewFile();
                loadDefaults();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

}
