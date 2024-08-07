package me.drawn.utils;

import me.drawn.Main;
import org.bukkit.entity.Player;

public class DistanceManager {
    public static final int defaultServerViewDistance = Main.config.getInt("view-distance.server-default", 10);
    public static final int defaultServerSimulationDistance = Main.config.getInt("simulation-distance.server-default", 5);

    public static final String viewDistanceNoValue = Main.config.getString("view-distance.when-no-value", "reset");
    public static final String simulationDistanceNoValue = Main.config.getString("simulation-distance.when-no-value", "reset");

    public static void changeViewDistance(Player player, int many) {
        player.setViewDistance(many);
        player.setSendViewDistance(many);
    }

    public static void changeSimulationDistance(Player player, int many) {
        player.setSimulationDistance(many);
    }

    public static void resetViewDistance(Player player) {
        player.setViewDistance(defaultServerViewDistance);
        player.setSendViewDistance(defaultServerViewDistance);
    }
    public static void resetSimulationDistance(Player player) {
        player.setSimulationDistance(defaultServerSimulationDistance);
    }

    public static void resetAllDistances(Player player) {
        resetSimulationDistance(player);
        resetViewDistance(player);
    }

    // needs a rewrite later
    public static void noViewDistanceValue(Player player) {
        if (viewDistanceNoValue.equals("nothing"))
            return;
        if (viewDistanceNoValue.equals("balance"))
            changeViewDistance(player, player.getClientViewDistance());
        // always reset
        resetViewDistance(player);
    }

    public static void noSimulationDistanceValue(Player player) {
        if (simulationDistanceNoValue.equals("nothing"))
            return;
        if (simulationDistanceNoValue.equals("balance"))
            changeSimulationDistance(player, player.getClientViewDistance());
        // always reset
        resetSimulationDistance(player);
    }

}
