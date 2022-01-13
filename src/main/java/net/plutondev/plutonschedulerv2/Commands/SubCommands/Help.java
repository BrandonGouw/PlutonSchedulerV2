package net.plutondev.plutonschedulerv2.Commands.SubCommands;

import net.plutondev.plutonschedulerv2.Commands.Managers.SubCommand;
import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import org.bukkit.entity.Player;

import java.util.List;

public class Help extends SubCommand {
    public Help(PlutonSchedulerV2 main) {
        super(main);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getInfo() {
        return "Opens the help menu";
    }

    @Override
    public String getPermission() {
        return "none";
    }

    @Override
    public void executeCommand(Player player, List<String> args) {
        List<String> helpMessage = main.messages.getHelpMessage();

        for (String help : helpMessage)
            player.sendMessage(help);
    }
}
