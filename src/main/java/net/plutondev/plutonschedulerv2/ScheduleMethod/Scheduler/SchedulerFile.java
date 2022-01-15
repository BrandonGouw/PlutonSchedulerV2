package net.plutondev.plutonschedulerv2.ScheduleMethod.Scheduler;

import net.plutondev.plutonschedulerv2.PlutonSchedulerV2;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class SchedulerFile {
    private final PlutonSchedulerV2 plugin;
    private FileConfiguration dataConfig = null;
    private java.io.File configFile = null;

    public SchedulerFile(PlutonSchedulerV2 plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void configReload() {
        if (this.configFile == null)
            this.configFile = new java.io.File(this.plugin.getDataFolder(), "scheduler.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(configFile);

        InputStream defaultStream = this.plugin.getResource("scheduler.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);

        }
    }

    public FileConfiguration getConfig() {
        if (this.dataConfig == null)
            configReload();
        return this.dataConfig;
    }

    public void saveConfig() {
        if (this.dataConfig == null || this.configFile == null)
            return;
        try {
            this.getConfig().save(configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "The scheduler file couldn't be saved");
        }
    }

    public void saveDefaultConfig() {
        if (this.dataConfig == null)
            this.configFile = new java.io.File(this.plugin.getDataFolder(), "scheduler.yml");
        if (!this.configFile.exists())
            plugin.saveResource("scheduler.yml", false);
    }
}

