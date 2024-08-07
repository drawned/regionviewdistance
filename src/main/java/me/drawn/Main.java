package me.drawn;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.IntegerFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import me.drawn.utils.RegionScheduler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Logger l;
    public static FileConfiguration config;

    public static IntegerFlag viewDistanceFlag;
    public static IntegerFlag simulationDistanceFlag;

    @Override
    public void onLoad() {
        l = this.getLogger();

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        try {

            IntegerFlag viewDistance = new IntegerFlag("view-distance");
            registry.register(viewDistance);
            viewDistanceFlag = viewDistance;

        } catch (Exception ex) {
            l.warning("Error registering wg flag view-distance: "+ex.getMessage());
            ex.fillInStackTrace();
        }

        try {

            IntegerFlag simulationDistance = new IntegerFlag("simulation-distance");
            registry.register(simulationDistance);
            simulationDistanceFlag = simulationDistance;

        } catch (Exception ex) {
            l.warning("Error registering wg flag simulation-distance: "+ex.getMessage());
            ex.fillInStackTrace();
        }

        // Converted to bukkit schedulers.
        /*SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(ViewDistanceHandler.FACTORY, null);
        sessionManager.registerHandler(SimulationDistanceHandler.FACTORY, null);*/
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {

        l.info("Loading plugin...");

        if(!this.getServer().getPluginManager().isPluginEnabled("WorldGuard")) {
            l.warning("This plugin REQUIRES WorldGuard to be running! Please make sure you have WorldGuard installed in this server.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        l.info("Loading config file...");
        this.saveDefaultConfig();

        config = this.getConfig();

        if(config.getBoolean("update-config-default-values-from-server")) {
            l.info("Updating default values...");
            updateValues();
        }

        Bukkit.getScheduler().runTaskTimer(this, new RegionScheduler(), 20, 40);
    }

    public void updateValues() {
        config.set("view-distance.server-default", getServer().getViewDistance());
        config.set("simulation-distance.server-default", getServer().getSimulationDistance());
        saveConfig();
        reloadConfig();
    }

}
