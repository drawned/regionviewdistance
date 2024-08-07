package me.drawn.handlers.flag;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import me.drawn.Main;
import me.drawn.utils.DistanceManager;

public class SimulationDistanceHandler extends FlagValueChangeHandler<Integer> {

    @Override
    protected void onInitialValue(LocalPlayer localPlayer, ApplicableRegionSet applicableRegionSet, Integer integer) {
        this.handleValue(localPlayer, integer);
    }

    @Override
    protected boolean onSetValue(LocalPlayer localPlayer, Location location, Location location1, ApplicableRegionSet applicableRegionSet, Integer integer, Integer t1, MoveType moveType) {
        this.handleValue(localPlayer, integer);
        return false;
    }

    @Override
    protected boolean onAbsentValue(LocalPlayer localPlayer, Location location, Location location1, ApplicableRegionSet applicableRegionSet, Integer integer, MoveType moveType) {
        this.handleValue(localPlayer, -1);
        return false;
    }

    public SimulationDistanceHandler(Session session) {
        super(session, Main.viewDistanceFlag);
    }

    private void handleValue(LocalPlayer player, int value) {
        if(value == -1) {
            DistanceManager.resetSimulationDistance(BukkitAdapter.adapt(player));
        } else {
            DistanceManager.changeSimulationDistance(BukkitAdapter.adapt(player), value);
        }
    }

}

