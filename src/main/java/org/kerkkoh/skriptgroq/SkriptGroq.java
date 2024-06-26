package org.kerkkoh.skriptgroq;

import org.bstats.bukkit.Metrics;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class SkriptGroq extends JavaPlugin {

    static SkriptGroq instance;
    SkriptAddon addon;

    public static FileConfiguration config;

    public void onEnable() {
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("org.kerkkoh.skriptgroq");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!getDataFolder().exists()) {
            saveDefaultConfig();
        }

        reloadConfig();

        // Register Metrics
        Metrics metrics = new Metrics(this, 21702);

        metrics.addCustomChart(new SimplePie("skript_version",
                () -> Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
        metrics.addCustomChart(new SimplePie("skript-groq_version", () -> this.getDescription().getVersion()));

        getCommand("skriptgroq").setExecutor(new Commands(this));

        Bukkit.getLogger().info("[SkriptGroq] has been enabled!");

    }

    public static SkriptGroq getInstance() {

        return instance;
    }

    public SkriptAddon getAddonInstance() {
        return addon;
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();

        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

}
