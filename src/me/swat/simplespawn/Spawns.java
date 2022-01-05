package me.swat.simplespawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import java.io.File;

public class Spawns implements CommandExecutor {

    SimpleSpawnMain main;

    public Spawns(SimpleSpawnMain main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            Location playerLocation = player.getLocation();
            FileConfiguration config = main.getConfig();
            if (command.getName().equalsIgnoreCase("setspawn")) {
                if (sender.hasPermission("setspawn.permission")) {
                    config.set("world.world", player.getLocation().getWorld().getName());
                    config.set("world.x", playerLocation.getX());
                    config.set("world.y", playerLocation.getY());
                    config.set("world.z", playerLocation.getZ());
                    config.set("world.yaw", playerLocation.getYaw());
                    config.set("world.pitch", playerLocation.getPitch());
                    main.saveConfig();
                    sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[SimpleSpawn] Точка спавна установлена.");
                } else {
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[SimpleSpawn] У вас нет прав.");
                }
            }
            if (command.getName().equalsIgnoreCase("spawn")) {
                if (sender.hasPermission("spawn.permission")) {
                    File configFile = new File(main.getDataFolder(), "config.yml");
                    if (configFile.exists()) {
                        Location teleportLocation = new Location(main.getServer().getWorld(config.getString("world.world")),
                                config.getDouble("world.x"),
                                config.getDouble("world.y"),
                                config.getDouble("world.z"),
                                (float) config.getDouble("world.yaw"),
                                (float) config.getDouble("world.pitch"));
                        player.teleport(teleportLocation);
                    } else {
                        sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "[SimpleSpawn] Точка спавна еще не установлена.");
                    }
                }
            }
        }
        return  true;
    }
}
