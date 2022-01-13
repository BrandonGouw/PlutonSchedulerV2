package net.plutondev.plutonschedulerv2.Commands.Managers;

import org.bukkit.entity.Player;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getInfo();

    public abstract String getPermission();

    public abstract void executeCommand(Player player, String args);
}
