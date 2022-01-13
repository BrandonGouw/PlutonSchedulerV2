package net.plutondev.plutonschedulerv2.Commands.Managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CommandManager implements CommandExecutor {
    private final List<SubCommand> subCommandList;

    public CommandManager(final List<SubCommand> subCommandList){
        this.subCommandList = subCommandList;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;

        if(args.length < 1){
            return false;
        }

        Player player = (Player) sender;

        for (SubCommand subCommand : this.subCommandList) {
            if(!args[0].equalsIgnoreCase(subCommand.getName()))
                continue;

            if(!player.hasPermission(subCommand.getPermission()) && subCommand.getPermission() != null)
                break;

            subCommand.executeCommand(player, getArgs(args));
        }

        return true;
    }

    public List<String> getArgs(final String[] args){
        List<String> newArgs = Arrays.asList(args);

        newArgs.remove(0);
        return newArgs;
    }
}
