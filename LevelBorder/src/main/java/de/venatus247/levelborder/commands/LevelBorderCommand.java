package de.venatus247.levelborder.commands;

import de.venatus247.levelborder.LevelBorder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelBorderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0) {
            //TODO help
            return true;
        }

        if(args[0].equalsIgnoreCase("set")) {
            if(!sender.hasPermission("levelborder.border.set")) {
                //todo no permission message
                return true;
            }

            if(!(sender instanceof Player player)) {
                sendSenderNeedToBePlayerMessage(sender);
                return true;
            }

            Location location = new Location(player.getWorld(), player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());

            LevelBorder.getInstance().setWorldSpawnLocation(player.getWorld(), location);
            sender.sendMessage(LevelBorder.prefix + "§aDie Border-Location wurde an deine Position gesetzt.");
            return true;
        }
        if(args[0].equalsIgnoreCase("refresh")) {
            if(!(sender instanceof Player player)) {
                sendSenderNeedToBePlayerMessage(sender);
                return true;
            }

            LevelBorder.getInstance().setPlayerBorder(player);
            player.sendMessage(LevelBorder.prefix + "§aDeine Border wurde aktualisiert.");
            return true;
        }

        return true;
    }


    private void sendSenderNeedToBePlayerMessage(CommandSender sender) {
        sender.sendMessage(LevelBorder.prefix + "§cNur Spieler können die Location ändern!");
    }

}
