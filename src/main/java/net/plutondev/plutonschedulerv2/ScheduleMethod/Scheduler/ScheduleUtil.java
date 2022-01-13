package net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ScheduleUtil {
    private final SchedulerFile file;

    public ScheduleUtil(final SchedulerFile schedulerFile){
        this.file = schedulerFile;
    }

    public List<String> getScheduledCommands(){
        Set<String> scheduledSet = Objects.requireNonNull(file.getConfig().getConfigurationSection("scheduler"))
                .getKeys(false);

        return new ArrayList<>(scheduledSet);
    }

    public String getCommand(String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".command");
    }

    public String getScheduledTime(String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".scheduled-time");
    }

    public boolean isRepeat(String scheduledCommand){
        return this.file.getConfig().getBoolean("scheduler." + scheduledCommand + ".repeat.enabled");
    }

    public String getLastRun(String scheduledCommand){
        return this.file.getConfig().getString("scheduler." + scheduledCommand + ".repeat.last-run");
    }

    public int getInterval(String scheduledCommand){
        return this.file.getConfig().getInt("scheduler." + scheduledCommand + ".repeat.interval");
    }

    public int getIntervalBetweenChecks(){
        return this.file.getConfig().getInt("settings.interval-between-checks");
    }
}
