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

    // This method creates a bukkit scheduler which assists with pluton scheduler
    public void bukkitScheduler(){
        Bukkit.getScheduler().cancelTasks(main);
        int intervalBetweenChecks = this.scheduleUtil.getIntervalBetweenChecks();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(main, this::commandFor,0, intervalBetweenChecks);
    }

    // This method gets all the scheduled commands and checks them individually and then executes the command
    public void commandFor(){
        List<String> scheduledCommands = this.scheduleUtil.getScheduledCommands();

        for (String scheduledCommand : scheduledCommands) {
            if(this.commandCheck(scheduledCommand))
                continue;

            executeCommand(scheduledCommand);
        }

    }

    // This method checks the command if it's the time to execute it.
    public boolean commandCheck(String command){
        LocalDateTime timeNow = this.timeManager.getTime();
        LocalDateTime timeCompare = this.timeManager.parseTime(scheduleUtil.getScheduledTime(command));

        if(timeCompare == null)
            return false;

        return this.timeManager.compareTime(timeNow, timeCompare);
    }

    /**
     * This method handles the command execution & setting the last run and the next schedule time if the repeat is enabled.
     *
     * @param command The command that is scheduled to run
     */
    public void executeCommand(String command){
        ConsoleCommandSender consoleSender = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(consoleSender, this.scheduleUtil.getCommand(command));

        LocalDateTime timeNow = this.timeManager.getTime();

        if(!this.scheduleUtil.isRepeat(command)) {
            this.scheduleUtil.setScheduleTime(command, null);
            return;
        }

        int interval = this.scheduleUtil.getInterval(command);

        LocalDateTime scheduleTime = this.main.timeCalculation.addSeconds(timeNow, interval);

        this.scheduleUtil.setLastRun(command, timeNow);
        this.scheduleUtil.setScheduleTime(command, scheduleTime);
    }

    // Removes the scheduler.
    public void flush(){
        Bukkit.getScheduler().cancelTasks(main);
    }

}