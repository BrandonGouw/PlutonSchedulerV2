package net.plutondev.plutonschedulerv2.Commands.SubCommands;

import net.plutondev.plutonschedulerv2.Commands.Managers.SubCommand;
import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import org.bukkit.entity.Player;

import java.util.List;

public class Reload extends SubCommand {
    public Reload(PlutonSchedulerV2 main) {
        super(main);
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getInfo() {
        return "Reloads the config";
    }

    @Override
    public String getPermission() {
        return "plutonscheduler.reload";
    }

    @Override
    public void executeCommand(Player player, List<String> args) {
        main.schedulerFile.configReload();
        main.reloadConfig();
        main.scheduleManager.bukkitScheduler();

        player.sendMessage(main.messages.getReloadMsg());
    }
}
