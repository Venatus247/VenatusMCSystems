package de.venatus247.vutils;

public class Logger {

    private static Logger instance;
    public static Logger getInstance() {
        if(instance != null) return instance;
        instance = new Logger();
        return instance;
    }

    public void log(String s) {
        VUtils.getInstance().getConsole().sendMessage(s);
    }

    public void error(String s) {
        VUtils.getInstance().getConsole().sendMessage("§c" + s);
    }

}
