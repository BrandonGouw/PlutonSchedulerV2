package net.plutondev.plutonschedulerv2.Commands.Managers;

import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {
    public final PlutonSchedulerV2 main;

    public SubCommand(final PlutonSchedulerV2 main){
        this.main = main;
    }

    public abstract String getName();

    public abstract String getInfo();

    public abstract String getPermission();

    public abstract void executeCommand(Player player, List<String> args);
}
