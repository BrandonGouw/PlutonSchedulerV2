package net.plutondev.plutonschedulerv2.Utils;

import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import org.bukkit.ChatColor;

import java.util.List;

public class Messages {
    private final PlutonSchedulerV2 main;

    public Messages(final PlutonSchedulerV2 main){
        this.main = main;
    }

    public List<String> getHelpMessage(){
        List<String> helpList = this.main.getConfig().getStringList("messages.help");

        for (int i = 0; i < helpList.size(); i++) {
            helpList.set(i, getColor(helpList.get(i)));
        }

        return helpList;
    }

    public String getReloadMsg(){
        return this.getColor(this.main.getConfig().getString("messages.reload-message"));
    }

    public String getColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
