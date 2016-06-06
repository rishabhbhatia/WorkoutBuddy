package com.fitbud.workoutbuddy.models;

/**
 * Created by Rishabh Bhatia on 6/6/2016.
 */
public class Routine {

    private String days;
    private String timings;
    private String routineName;

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getRoutineName() {
        return routineName;
    }

    public void setRoutineName(String routineName) {
        this.routineName = routineName;
    }
}
