package me.drawn.handlers.flag;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldedit.util.SideEffect;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.IntegerFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.session.MoveType;
import com.sk89q.worldguard.session.Session;
import com.sk89q.worldguard.session.handler.FlagValueChangeHandler;
import com.sk89q.worldguard.session.handler.Handler;
import me.drawn.Main;
import me.drawn.utils.DistanceManager;
import org.bukkit.World;

public class ViewDistanceHandler extends FlagValueChangeHandler<Integer> {

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

    public ViewDistanceHandler(Session session) {
        super(session, Main.viewDistanceFlag);
    }

    private void handleValue(LocalPlayer player, int value) {
        if(value == -1) {
            DistanceManager.resetViewDistance(BukkitAdapter.adapt(player));
        } else {
            DistanceManager.changeViewDistance(BukkitAdapter.adapt(player), value);
        }
    }

}

