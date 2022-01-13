package net.plutondev.plutonschedulerv2.ScheduleMethod.Time;

import java.time.LocalDateTime;

public class TimeCalculation {

    /**
     * The use of this method is for calculating the time and turning them into seconds.
     *
     * @param time The time to calculate the value
     * @return This method will return the int of all the value combined, value is assigned to seconds
     */
    public int calcValue(final LocalDateTime time){
        int year = this.getYear(time) * 365 * 24 * 60 * 60;
        int dayOfYear = this.getDayOfYear(time) * 24 * 60 * 60;
        int hour = this.getHour(time) * 60 * 60;
        int minute = this.getMinute(time) * 60;
        int second = this.getSecond(time);

        return (year + dayOfYear + hour + minute + second);
    }

    // Gets the year
    public int getYear(final LocalDateTime time){
        return time.getYear();
    }

    //Gets day of year
    public int getDayOfYear(final LocalDateTime time){
        return time.getDayOfYear();
    }

    //Gets the hour
    public int getHour(final LocalDateTime time){
        return time.getHour();
    }

    //Gets the minute
    public int getMinute(final LocalDateTime time){
        return time.getMinute();
    }

    //Gets the second
    public int getSecond(final LocalDateTime time){
        return time.getSecond();
    }
}