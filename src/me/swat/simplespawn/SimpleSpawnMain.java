package me.swat.simplespawn;

import org.bukkit.plugin.java.JavaPlugin;

public class SimpleSpawnMain extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("spawn").setExecutor(new Spawns(this));
        getCommand("setspawn").setExecutor(new Spawns(this));
    }
}
