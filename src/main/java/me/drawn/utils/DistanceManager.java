package me.drawn.utils;

import com.sk89q.worldguard.util.profile.resolver.PaperPlayerService;
import me.drawn.Main;
import org.bukkit.entity.Player;

public class DistanceManager {

    public static void changeViewDistance(Player player, int many) {
        player.setViewDistance(many);
        player.setSendViewDistance(many);
    }

    public static void changeSimulationDistance(Player player, int many) {
        player.setSimulationDistance(many);
    }

    public static void resetViewDistance(Player player) {
        player.setViewDistance(Main.config.getInt("default.view-distance"));
        player.setSendViewDistance(Main.config.getInt("default.view-distance"));
    }
    public static void resetSimulationDistance(Player player) {
        player.setSimulationDistance(Main.config.getInt("default.simulation-distance"));
    }

    public static void resetAllDistances(Player player) {
        resetSimulationDistance(player);
        resetViewDistance(player);
    }

}
