package de.venatus247.vutils.commands;

import de.venatus247.vutils.Main;
import de.venatus247.vutils.VUtils;
import de.venatus247.vutils.utils.handlers.timer.TimerStringFormat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TimerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            //TODO help
            return true;
        }

        if(args[0].equalsIgnoreCase("toggle")) {
            if(!sender.hasPermission("vutils.timer.toggle"))
                return true;

            if(VUtils.getInstance().getTimerHandler().isRunning()) {
                VUtils.getInstance().getTimerHandler().pause();
            } else {
                VUtils.getInstance().getTimerHandler().start();
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("start") || args[0].equalsIgnoreCase("resume")) {
            if(!sender.hasPermission("vutils.timer.toggle"))
                return true;

            if(!VUtils.getInstance().getTimerHandler().isRunning()) {
                VUtils.getInstance().getTimerHandler().start();
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("stop") || args[0].equalsIgnoreCase("pause")) {
            if(!sender.hasPermission("vutils.timer.toggle"))
                return true;

            if(VUtils.getInstance().getTimerHandler().isRunning()) {
                VUtils.getInstance().getTimerHandler().pause();
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("format")) {
            if(!sender.hasPermission("vutils.timer.theme"))
                return true;

            if(args.length == 1) {
                sendTimerThemeHelpMessage(sender);
                return true;
            }

            if(args.length == 2) {
                try {
                    TimerStringFormat format = TimerStringFormat.valueOf(args[1].toUpperCase());
                    VUtils.getInstance().setTimerFormat(format);
                    Main.sendPrefixedMessage(sender,"§7Timer-Format wurde zu §a" + format.name().toLowerCase() + " §7geändert.");
                } catch (IllegalArgumentException e) {
                    sendTimerThemeHelpMessage(sender);
                    return true;
                }

            }

        }
        return true;

    }

    private void sendTimerThemeHelpMessage(CommandSender sender) {
        StringBuilder builder = new StringBuilder();
        builder.append("/timer format <");
        for(TimerStringFormat style : TimerStringFormat.values())
            builder.append(style.toString().toLowerCase()).append("/");
        builder.deleteCharAt(builder.length()-1);
        builder.append(">");

        Main.sendCommandUsageMessage(sender, builder.toString());
    }

}
