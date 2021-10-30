package de.venatus247.levelborder.config;

import de.venatus247.vutils.Logger;
import de.venatus247.vutils.utils.file.YmlConfigFile;

import java.io.File;
import java.io.IOException;

public class LevelBorderConfig extends YmlConfigFile {

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
    }

    @Override
    public boolean loadDefaults(boolean writeToFile) {
        Logger.getInstance().log("Loading default config");
        config.addDefault("border.currentSize", 0);
        config.addDefault("border.expandTime", 1);
        if (writeToFile) {
            config.options().copyDefaults(true);
            return save();
        }

        return true;
    }

    @Override
    public boolean save() {
        Logger.getInstance().log("Setting size to " + currentBorderSize);
        config.set("border.currentSize", currentBorderSize);
        return super.save();
    }

    public int getCurrentBorderSize() {
        return currentBorderSize;
    }

    public long getBorderExpandTime() {
        return borderExpandTime;
    }

    public void setCurrentBorderSize(int currentBorderSize) {
        this.currentBorderSize = currentBorderSize;
    }
}
