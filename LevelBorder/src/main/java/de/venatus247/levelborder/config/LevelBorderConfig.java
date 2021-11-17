package de.venatus247.levelborder.config;

import de.venatus247.vutils.Logger;
import de.venatus247.vutils.utils.file.YmlConfigFile;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;

public class LevelBorderConfig extends YmlConfigFile {

    private int level;
    private float exp;

    private int currentBorderSize;
    private long borderExpandTime = 1;

    public LevelBorderConfig() throws IOException {
        super(new File("plugins/LevelBorder/config.yml"));
    }

    @Override
    protected void load() {
        super.load();
        currentBorderSize = getInt("border.currentSize");
        borderExpandTime = getLong("border.expandTime");
        level = getInt("level.level");
        exp = getFloat("level.exp");
    }

    @Override
    public boolean loadDefaults(boolean writeToFile) {
        Logger.getInstance().log("Loading default config");
        config.addDefault("border.currentSize", 0);
        config.addDefault("border.expandTime", 1);
        config.addDefault("level.level", 0);
        config.addDefault("level.exp", 0.0f);
        config.addDefault("world.overworld.name", "world");
        config.addDefault("world.nether.name", "world_nether");
        config.addDefault("world.end.name", "world_the_end");
        if (writeToFile) {
            config.options().copyDefaults(true);
            return save();
        }

        return true;
    }

    @Override
    public boolean save() {
        Logger.getInstance().log("Setting size to " + currentBorderSize);
        set("border.currentSize", currentBorderSize);
        set("border.expandTime", borderExpandTime);
        set("level.level", level);
        set("level.exp", exp);
        return super.save();
    }

    public int getLevel() {
        return level;
    }

    public float getExp() {
        return exp;
    }

    public String getOverWorldName() {
        return getString("world.overworld.name");
    }

    public String getNetherWorldName() {
        return getString("world.nether.name");
    }

    public String getEndWorldName() {
        return getString("world.end.name");
    }

    public int getCurrentBorderSize() {
        return currentBorderSize;
    }

    public long getBorderExpandTime() {
        return borderExpandTime;
    }

    public void updateLevelAndExp(int level, float exp) {
        this.level = level;
        this.exp = exp;
        this.save();
    }

    public void setCurrentBorderSize(int currentBorderSize) {
        this.currentBorderSize = currentBorderSize;
        this.save();
    }
}
