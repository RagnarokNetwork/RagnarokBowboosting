package net.ragnaroknetwork.ragnarokbowboosting;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Command implements CommandExecutor {
    Plugin plugin = RagnarokBowBoosting.getPlugin(RagnarokBowBoosting.class);

    @Override
    public boolean onCommand (CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("ragnarokbowboosting.admin")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cYou do not have permission to execute this command!"));
                return true;
            }
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRagnarokBowBoosting Help\n&c&l ► &c/rbb a <value>: &7Change the arrow velocity.\n&c&l ► &c/rbb h <value>: &7Change the horizontal player velocity on a non boosting shot.\n&c&l ► &c/rbb v <value>: &7Change the vertical player velocity on a non boosting shot.\n&c&l ► &c/rbb hf <value>: &7Change the horizontal player velocity on first shot.\n&c&l ► &c/rbb vf <value>: &7Change the vertical player velocity on first shot.\n&c&l ► &c/rbb hc <value>: &7Change the horizontal player velocity on chained shot.\n&c&l ► &c/rbb vc <value>: &7Change the vertical player velocity on chained shot.\n&c&l ► &c/rbb reload: &7Reload the config file.\n&c&l ► &c/rbb values: &7Get all modifier values."));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&l► &aSuccessfully reloaded &2RagnarokBowBoosting&a!"));
            return true;
        }

        else if (args[0].equalsIgnoreCase("a")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Arrow velocity multiplier: &c" + plugin.getConfig().getString("arrowMultiplier")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("arrowMultiplier", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The arrow velocity multiplier was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("h")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Horizontal player velocity on a non boosting shot multiplier: &c " + plugin.getConfig().getString("horizontalMultiplier")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("horizontalMultiplier", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The horizontal player velocity on a non boosting shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("v")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Vertical player velocity on a non boosting shot multiplier: &c " + plugin.getConfig().getString("verticalMultiplier")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("verticalMultiplier", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The vertical player velocity on a non boosting shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("hf")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Horizontal player velocity on first shot multiplier: &c " + plugin.getConfig().getString("playerMultiplierHorizontal")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("playerMultiplierHorizontal", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The horizontal player velocity on first shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("vf")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Vertical player velocity on first shot multiplier: &c " + plugin.getConfig().getString("playerMultiplierVertical")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("playerMultiplierVertical", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The vertical player velocity on first shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("hc")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Horizontal player velocity on chained shot multiplier: &c " + plugin.getConfig().getString("playerMultiplierHorizontalChain")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("playerMultiplierHorizontalChain", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The horizontal player velocity on chained shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("vc")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Vertical player velocity on chained shot multiplier: &c " + plugin.getConfig().getString("playerMultiplierVerticalChain")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("playerMultiplierVerticalChain", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The vertical player velocity on chained shot was set to &c" + args[1]));
            return true;
        }

        else if (args[0].equalsIgnoreCase("values")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lValues\n&c&l ► &7a &c" + plugin.getConfig().getString("arrowMultiplier") + "\n&c&l ► &7h &c" + plugin.getConfig().getString("playerMultiplierHorizontal") + "\n&c&l ► &7v &c" + plugin.getConfig().getString("playerMultiplierVertical") + "\n&c&l ► &7hc &c" + plugin.getConfig().getString("playerMultiplierHorizontalChain") + "\n&c&l ► &7vc &c" + plugin.getConfig().getString("playerMultiplierVerticalChain")));
            return true;
        }

        else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRagnarokBowBoosting Help\n&c&l ► &c/rbb a <value>: &7Change the arrow velocity.\n&c&l ► &c/rbb h <value>: &7Change the horizontal player velocity on a non boosting shot.\n&c&l ► &c/rbb v <value>: &7Change the vertical player velocity on a non boosting shot.\n&c&l ► &c/rbb hf <value>: &7Change the horizontal player velocity on first shot.\n&c&l ► &c/rbb vf <value>: &7Change the vertical player velocity on first shot.\n&c&l ► &c/rbb hc <value>: &7Change the horizontal player velocity on chained shot.\n&c&l ► &c/rbb vc <value>: &7Change the vertical player velocity on chained shot.\n&c&l ► &c/rbb reload: &7Reload the config file.\n&c&l ► &c/rbb values: &7Get all modifier values."));
            return true;
        }

        return true;
    }
}
