package de.venatus247.levelborder.config;

import java.io.File;
import java.io.IOException;

public class LevelBorderConfig extends ConfigManager {

    private int currentBorderSize = -1;
    private long borderExpandTime = 1;

    public LevelBorderConfig() throws IOException {
        super(new File("plugins/LevelBorder/config.yml"));
    }

    @Override
    public void load() {
        super.load();

        currentBorderSize = configuration.getInt("currentBorderSize");
        borderExpandTime = configuration.getLong("borderExpandTime");
    }

    @Override
    public void save() {
        configuration.set("currentBorderSize", currentBorderSize);
        configuration.set("borderExpandTime", borderExpandTime);
        super.save();
    }

    @Override
    public void loadDefaults() {
        super.loadDefaults();
        configuration.addDefault("currentBorderSize", 0);
        configuration.addDefault("borderExpandTime", 1);
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCurrentBorderSize() {
        return currentBorderSize;
    }

    public long getBorderExpandTime() {
        return borderExpandTime;
    }

    public void setBorderExpandTime(long borderExpandTime) {
        this.borderExpandTime = borderExpandTime;
    }

    public void setCurrentBorderSize(int currentBorderSize) {
        this.currentBorderSize = currentBorderSize;
    }

}
