package net.plutondev.plutonschedulerv2.ScheduleMethod.Time;

import org.bukkit.Bukkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class TimeManager {
    private final TimeCalculation timeCalculation;

    public TimeManager(final TimeCalculation timeCalculation) {
        this.timeCalculation = timeCalculation;
    }

    /**
     * This method will return true if the timeNow has passed timeCompare
     * It will return false otherwise.
     *
     * @param timeNow     the current time and the time that is going to be compared.
     * @param timeCompare the time that the timeNow is going to be compared to.
     * @return Will return true if timeNow > timeCompare
     */
    public boolean compareTime(final LocalDateTime timeNow, final LocalDateTime timeCompare) {
        int timeNowValue = timeCalculation.calcValue(timeNow);
        int timeCompareValue = timeCalculation.calcValue(timeCompare);

        return timeNowValue > timeCompareValue;
    }

    /**
     * This method will return the time of the server.
     * This method is affected by where the server is hosted.
     *
     * @return Will return the current time of the server
     */
    public LocalDateTime getTime() {
        return this.formatTime(LocalDateTime.now());

    }

    /**
     * This method will format the time to the desired time format.
     *
     * @param time the time which will be formatted.
     * @return Time which has been formatted.
     */
    public LocalDateTime formatTime(final LocalDateTime time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(this.getTimeFormat());

        return LocalDateTime.parse(timeFormatter.format(time), timeFormatter);
    }

    /**
     * This method will format the time to the string
     *
     * @param time the time which will be formatted
     * @return A string of the time that has been formatted
     */
    public String formatTimeString(final LocalDateTime time) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(this.getTimeFormat());

        return timeFormatter.format(time);
    }

    /**
     * This method is used to parse string into time.
     *
     * @param timeString The string that is going to be parsed into localDateTime
     * @return The method returns the parsed time
     */
    public LocalDateTime parseTime(final String timeString) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(this.getTimeFormat());

        try {
            return LocalDateTime.parse(timeString, timeFormatter);
        } catch (Exception exception) {
            Bukkit.getLogger().log(Level.WARNING, "Could not parse timeString");
        }

        return null;
    }

    /**
     * This method will get the time format for the localDateTime parse
     *
     * @return Returns the time format for localDateTime
     */
    public String getTimeFormat() {
        return "dd-MM-yyyy HH:mm:ss";
    }
}