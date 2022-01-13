package net.plutondev.plutonschedulerv2;

import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.ScheduleManager;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.ScheduleUtil;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.SchedulerFile;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Time.TimeCalculation;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Time.TimeManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlutonSchedulerV2 extends JavaPlugin {
    public TimeCalculation timeCalculation;
    public TimeManager timeManager;

    public SchedulerFile schedulerFile;
    public ScheduleUtil scheduleUtil;
    public ScheduleManager scheduleManager;

    @Override
    public void onEnable() {
        // [+] TIME METHODS [+]
        this.timeCalculation = new TimeCalculation();
        this.timeManager = new TimeManager(this.timeCalculation);

        // [+] SCHEDULE METHODS [+]
        this.schedulerFile = new SchedulerFile(this);
        this.scheduleUtil = new ScheduleUtil(this.schedulerFile);
        this.scheduleManager = new ScheduleManager(this);

        this.scheduleManager.bukkitScheduler();

    }

    @Override
    public void onDisable() {
        this.scheduleManager.flush();
    }
}
