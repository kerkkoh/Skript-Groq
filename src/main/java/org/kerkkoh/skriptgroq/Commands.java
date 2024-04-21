package org.kerkkoh.skriptgroq;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.ChatColor.*;

public class Commands implements CommandExecutor {

    private final SkriptGroq plugin;

    public Commands(SkriptGroq plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0)
            args = new String[] { "help" };
        if (command.getName().equalsIgnoreCase("skriptgroq")) {
            if (sender.hasPermission("skriptgroq.reload")) {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(LIGHT_PURPLE + "------------------ " + DARK_PURPLE + "Skript-Groq" + LIGHT_PURPLE
                            + " ------------------");
                    sender.sendMessage(" ");
                    sender.sendMessage(LIGHT_PURPLE + "/skriptgroq help" + WHITE + " Show this help message.");
                    sender.sendMessage(
                            LIGHT_PURPLE + "/skriptgroq reload" + WHITE + " Reload the config and the OpenAI key..");
                } else if (args[0].equalsIgnoreCase("reload")) {
                    SkriptGroq.getInstance().reloadConfig();
                    sender.sendMessage(LIGHT_PURPLE + "[" + DARK_PURPLE + "SkriptGroq" + LIGHT_PURPLE + "]" + GREEN
                            + " Config reloaded.");
                    return false;
                } else {
                    sender.sendMessage(LIGHT_PURPLE + "[" + DARK_PURPLE + "SkriptGroq" + LIGHT_PURPLE + "]" + RED
                            + " Invalid command. /skriptgroq help");
                }
                return false;
            } else {
                sender.sendMessage(LIGHT_PURPLE + "[" + DARK_PURPLE + "SkriptGroq" + LIGHT_PURPLE + "]" + RED
                        + " You don't have the permission " + DARK_RED + "SkriptGroq.reload" + RED + ".");
            }
            return false;
        }
        return false;
    }
}
