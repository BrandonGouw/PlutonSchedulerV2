package net.plutondev.plutonschedulerv2;

import net.plutondev.plutonschedulerv2.Commands.Managers.SubCommand;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.ScheduleManager;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.ScheduleUtil;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler.SchedulerFile;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Time.TimeCalculation;
import net.plutondev.plutonschedulerv2.ScheduleMethod.Time.TimeManager;
import net.plutondev.plutonschedulerv2.Utils.Messages;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class PlutonSchedulerV2 extends JavaPlugin {
    public TimeCalculation timeCalculation;
    public TimeManager timeManager;

    public SchedulerFile schedulerFile;
    public ScheduleUtil scheduleUtil;
    public ScheduleManager scheduleManager;

    public Messages messages;

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

        // [+] COMMANDS [+]
        List<SubCommand> subCommandList = new ArrayList<>();

        // [+] OTHER METHODS [+]
        messages = new Messages(this);



    }

    @Override
    public void onDisable() {
        this.scheduleManager.flush();
    }
}
