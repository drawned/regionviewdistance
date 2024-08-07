package me.drawn.utils;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.drawn.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RegionScheduler implements Runnable {

    final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();

    @Override
    public void run() {

        for(Player b : Bukkit.getOnlinePlayers()) {
            LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(b);

            Integer viewDistance = container.createQuery().queryValue(localPlayer.getLocation(), localPlayer, Main.viewDistanceFlag);
            if(viewDistance != null)
                DistanceManager.changeViewDistance(b, viewDistance);
            else
                DistanceManager.noViewDistanceValue(b);

            Integer simulationDistance = container.createQuery().queryValue(localPlayer.getLocation(), localPlayer, Main.simulationDistanceFlag);
            if(simulationDistance != null)
                DistanceManager.changeSimulationDistance(b, simulationDistance);
            else
                DistanceManager.noSimulationDistanceValue(b);

        }

    }

}
