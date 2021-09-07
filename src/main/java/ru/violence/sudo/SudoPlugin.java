package ru.violence.sudo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class SudoPlugin extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("sudo.use")) return true;

        if (args.length < 2) {
            sender.sendMessage("§cUsage: /sudo <player> <input>");
            return true;
        }

        String playerName = args[0];
        Player player = Bukkit.getPlayer(playerName);

        if (player == null) {
            sender.sendMessage("§cCan't find " + playerName + " on the server");
            return true;
        }

        String input = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

        if (input.startsWith("/")) {
            Bukkit.dispatchCommand(player, input.substring(1));
            sender.sendMessage("§aSuccessfully executed \"" + input + "\" by " + player.getName());
        } else {
            player.chat(input);
            sender.sendMessage("§aSuccessfully said \"" + input + "\" by " + player.getName());
        }

        return true;
    }
}
