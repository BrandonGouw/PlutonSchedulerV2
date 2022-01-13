package net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ScheduleUtil {
    private final SchedulerFile file;

    public ScheduleUtil(final SchedulerFile schedulerFile){
        this.file = schedulerFile;
    }

    /**
     * This method will get all the scheduled commands from the config
     *
     * @return This method will return a list of the scheduled commands
     */
    public List<String> getScheduledCommands(){
        Set<String> scheduledSet = Objects.requireNonNull(file.getConfig().getConfigurationSection("scheduler"))
                .getKeys(false);

        return new ArrayList<>(scheduledSet);
    }

    //Gets the command
    public String getCommand(final String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".command");
    }

    //Gets the scheduledTime
    public String getScheduledTime(final String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".scheduled-time");
    }

    //Sets the scheduled time
    public void setScheduleTime(final String scheduledCommand, LocalDateTime time) {
        this.file.getConfig().set("scheduler." + scheduledCommand + ".scheduled-time", time);
        this.file.saveConfig();
    }

    //Checks if the command repeats
    public boolean isRepeat(final String scheduledCommand){
        return this.file.getConfig().getBoolean("scheduler." + scheduledCommand + ".repeat.enabled");
    }

    //Gets when the command is last ran
    public String getLastRun(final String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".repeat.last-run");
    }

    //Sets the last run
    public void setLastRun(final String scheduledCommand, LocalDateTime time) {
        this.file.getConfig().set("scheduler." + scheduledCommand + ".repeat.last-run", time);
        this.file.saveConfig();
    }

    //Gets the interval on the command
    public int getInterval(final String scheduledCommand){
        return this.file.getConfig().getInt("scheduler." + scheduledCommand + ".repeat.interval");
    }

    //Gets the interval between checks
    public int getIntervalBetweenChecks(){
        return this.file.getConfig().getInt("settings.interval-between-checks");
    }
}