package me.drawn;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.IntegerFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.session.SessionManager;
import com.sk89q.worldguard.session.handler.Handler;
import me.drawn.handlers.flag.SimulationDistanceHandler;
import me.drawn.handlers.flag.ViewDistanceHandler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public static Logger l;
    public static FileConfiguration config;

    public static IntegerFlag viewDistanceFlag;
    public static IntegerFlag simulationDistanceFlag;

    @Override
    public void onEnable() {

        l = this.getLogger();

        l.info("Enabling plugin...");

        this.saveDefaultConfig();

        config = this.getConfig();

        if(config.getBoolean("update-config-default-values-from-server"))
            updateValues();

        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();

        try {

            IntegerFlag viewDistance = new IntegerFlag("view-distance");
            registry.register(viewDistance);
            viewDistanceFlag = viewDistance;

        } catch (Exception ex) {
            l.warning("Error registering flag view-distance: "+ex.getMessage());
            ex.fillInStackTrace();
        }

        try {

            IntegerFlag simulationDistance = new IntegerFlag("simulation-distance");
            registry.register(simulationDistance);
            simulationDistanceFlag = simulationDistance;

        } catch (Exception ex) {
            l.warning("Error registering flag simulation-distance: "+ex.getMessage());
            ex.fillInStackTrace();
        }

        SessionManager sessionManager = WorldGuard.getInstance().getPlatform().getSessionManager();
        sessionManager.registerHandler(ViewDistanceHandler.FACTORY, null);
        sessionManager.registerHandler(SimulationDistanceHandler.FACTORY, null);

    }

    public void updateValues() {
        config.set("default.view-distance", getServer().getViewDistance());
        config.set("default.simulation-distance", getServer().getSimulationDistance());
        saveConfig();
        reloadConfig();
    }

}
