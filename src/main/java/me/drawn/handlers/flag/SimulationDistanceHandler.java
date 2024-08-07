package me.drawn.handlers.flag;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import me.drawn.Main;
import me.drawn.utils.DistanceManager;

public class SimulationDistanceHandler extends FlagValueChangeHandler<Integer> {

    public static final SimulationDistanceHandler.Factory FACTORY = new SimulationDistanceHandler.Factory();
    public static class Factory extends Handler.Factory<SimulationDistanceHandler> {
        @Override
        public SimulationDistanceHandler create(Session session) {
            // create an instance of a handler for the particular session
            // if you need to pass certain variables based on, for example, the player
            // whose session this is, do it here
            return new SimulationDistanceHandler(session);
        }
    }

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
        super(session, Main.simulationDistanceFlag);
    }

    private void handleValue(LocalPlayer player, int value) {
        if(value == -1) {
            DistanceManager.resetSimulationDistance(BukkitAdapter.adapt(player));
        } else {
            DistanceManager.changeSimulationDistance(BukkitAdapter.adapt(player), value);
        }
    }

}

