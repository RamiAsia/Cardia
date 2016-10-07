package me.ramiasia.cardia.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rami Asia on 10/7/16.
 */

public class PeriodicSummary {

    // Start time of the Reading Summary
    private Date startTime;

    // End time of the Reading Summary
    private Date endTime;

    // Highest heart rate recorded of the user
    private int highestHeartRate;

    // Lowest heart rate recorded of the user
    private int lowestHeartRate;

    // Average heart rate of this period
    private int averageHeartRate;

    // Mode of the recorded heart rates
    private ArrayList<Integer> modeHeartRate;


    public PeriodicSummary(Date startTime, Date endTime, int highestHeartRate, int lowestHeartRate, int averageHeartRate, ArrayList<Integer> modes){
        this.highestHeartRate = highestHeartRate;
        this.lowestHeartRate = lowestHeartRate;
        this.modeHeartRate = modes;
        this.averageHeartRate = averageHeartRate;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public int getHighestHeartRate() {
        return highestHeartRate;
    }

    public int getLowestHeartRate() {
        return lowestHeartRate;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public ArrayList<Integer> getModeHeartRate() {
        return modeHeartRate;
    }
}
