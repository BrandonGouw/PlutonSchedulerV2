package net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler;

import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Time.TimeManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.time.LocalDateTime;
import java.util.List;

public class ScheduleManager {
    private final PlutonSchedulerV2 main;
    private final ScheduleUtil scheduleUtil;
    private final TimeManager timeManager;

    public ScheduleManager(final PlutonSchedulerV2 main){
        this.main = main;
        this.scheduleUtil = main.scheduleUtil;
        this.timeManager = main.timeManager;
    }

    public void bukkitScheduler(){
        Bukkit.getScheduler().cancelTasks(main);
        int intervalBetweenChecks = this.scheduleUtil.getIntervalBetweenChecks();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, this::commandFor,0, intervalBetweenChecks);
    }

    public void commandFor(){
        List<String> scheduledCommands = this.scheduleUtil.getScheduledCommands();

        for (String scheduledCommand : scheduledCommands) {
            if(this.commandCheck(scheduledCommand))
                continue;


        }


    }

    public boolean commandCheck(String command){
        LocalDateTime timeNow = this.timeManager.getTime();
        LocalDateTime timeCompare = this.timeManager.parseTime(scheduleUtil.getScheduledTime(command));

        return this.timeManager.compareTime(timeNow, timeCompare);
    }

    public void executeCommand(String command){
        ConsoleCommandSender consoleSender = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(consoleSender, command);
    }

}
