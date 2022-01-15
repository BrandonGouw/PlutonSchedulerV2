package net.plutondev.plutonschedulerv2.Commands.Managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {
    private final List<SubCommand> subCommandList;

    public CommandManager(final List<SubCommand> subCommandList) {
        this.subCommandList = subCommandList;
    }

    // This method handles the sub command manager.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;

        if (args.length < 1) {
            subCommandList.get(1).executeCommand(player, this.getArgs(args));
            return false;
        }

        //Runs a for loop to get check the sub command that is executed by the player.
        for (SubCommand subCommand : this.subCommandList) {
            if (!args[0].equalsIgnoreCase(subCommand.getName()))
                continue;

            if (!player.hasPermission(subCommand.getPermission()) && !subCommand.getPermission().equals("none"))
                break;

            subCommand.executeCommand(player, this.getArgs(args));
        }

        return true;
    }

    public List<String> getArgs(final String[] args) {
        return Arrays.asList(args);
    }
}
