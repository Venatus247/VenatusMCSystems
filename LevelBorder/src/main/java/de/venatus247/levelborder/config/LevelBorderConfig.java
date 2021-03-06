package de.venatus247.levelborder.config;

import de.venatus247.vutils.Logger;
import de.venatus247.vutils.utils.file.YmlConfigFile;

import java.io.File;
import java.io.IOException;

public class LevelBorderConfig extends YmlConfigFile {

    private int level;
    private float exp;

    private int currentBorderSize;
    private int borderExpandTime;
    private int borderLevelMultiplier;

    private boolean netherEntered;
    private boolean endEntered;

    public LevelBorderConfig() throws IOException {
        super(new File("plugins/LevelBorder/config.yml"));
    }

    @Override
    protected void load() {
        super.load();
        currentBorderSize = getInt("border.currentSize");
        borderExpandTime = getInt("border.expandTime");
        borderLevelMultiplier = getInt("border.multiplier");
        level = getInt("level.level");
        exp = getFloat("level.exp");
    }

    @Override
    public boolean loadDefaults(boolean writeToFile) {
        Logger.getInstance().log("Loading default config");
        config.addDefault("border.currentSize", 1);
        config.addDefault("border.expandTime", 1);
        config.addDefault("border.multiplier", 2);
        config.addDefault("level.level", 0);
        config.addDefault("level.exp", 0.0f);
        config.addDefault("world.overworld.name", "world");
        config.addDefault("world.nether.name", "world_nether");
        config.addDefault("world.nether.entered", false);
        config.addDefault("world.end.name", "world_the_end");
        config.addDefault("world.end.entered", false);
        if (writeToFile) {
            config.options().copyDefaults(true);
            write();
        }

        return true;
    }

    @Override
    public boolean save() {
        set("border.currentSize", currentBorderSize);
        set("border.expandTime", borderExpandTime);
        set("border.multiplier", borderLevelMultiplier);
        set("level.level", level);
        set("level.exp", exp);
        set("world.nether.entered", netherEntered);
        set("world.end.entered", endEntered);
        return super.save();
    }

    public void updateLevelAndExp(int level, float exp) {
        this.level = level;
        this.exp = exp;
        this.save();
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

    public int getBorderExpandTime() {
        return borderExpandTime;
    }

    public boolean isNetherEntered() {
        return netherEntered;
    }

    public boolean isEndEntered() {
        return endEntered;
    }

    public void setNetherEntered(boolean netherEntered) {
        this.netherEntered = netherEntered;
        save();
    }

    public void setEndEntered(boolean endEntered) {
        this.endEntered = endEntered;
        save();
    }

    public void setCurrentBorderSize(int currentBorderSize) {
        this.currentBorderSize = currentBorderSize;
        save();
    }

    public int getBorderLevelMultiplier() {
        return borderLevelMultiplier;
    }

    public void setBorderLevelMultiplier(int borderLevelMultiplier) {
        this.borderLevelMultiplier = borderLevelMultiplier;
    }
}
