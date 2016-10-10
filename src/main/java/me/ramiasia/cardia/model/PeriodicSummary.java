package me.ramiasia.cardia.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rami Asia on 10/7/16.
 */

public class PeriodicSummary {

    private long id;

    // Start time of the Reading Summary
    private DateTime startTime;

    // End time of the Reading Summary
    private DateTime endTime;

    // Highest heart rate recorded of the user
    private int highestHeartRate;

    // Lowest heart rate recorded of the user
    private int lowestHeartRate;

    // Average heart rate of this period
    private int averageHeartRate;

    // Mode of the recorded heart rates
    private ArrayList<Integer> modeHeartRate;

    // Total energy expended
    private int energyExpended;

    // Default Constructor
    public PeriodicSummary(){

    }


    public PeriodicSummary(DateTime startTime, DateTime endTime, int highestHeartRate, int lowestHeartRate, int averageHeartRate, ArrayList<Integer> modes){
        this.highestHeartRate = highestHeartRate;
        this.lowestHeartRate = lowestHeartRate;
        this.modeHeartRate = modes;
        this.averageHeartRate = averageHeartRate;
        this.endTime = endTime;
        this.startTime = startTime;
    }

    public PeriodicSummary(int id, DateTime startTime, DateTime endTime, int highestHeartRate, int lowestHeartRate, int averageHeartRate, ArrayList<Integer> modes){
        this.id = id;
        this.highestHeartRate = highestHeartRate;
        this.lowestHeartRate = lowestHeartRate;
        this.modeHeartRate = modes;
        this.averageHeartRate = averageHeartRate;
        this.endTime = endTime;
        this.startTime = startTime;
    }


    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
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

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public void setHighestHeartRate(int highestHeartRate) {
        this.highestHeartRate = highestHeartRate;
    }

    public void setLowestHeartRate(int lowestHeartRate) {
        this.lowestHeartRate = lowestHeartRate;
    }

    public void setAverageHeartRate(int averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public void setModeHeartRate(ArrayList<Integer> modeHeartRate) {
        this.modeHeartRate = modeHeartRate;
    }

    public int getEnergyExpended() {
        return energyExpended;
    }

    public void setEnergyExpended(int energyExpended) {
        this.energyExpended = energyExpended;
    }
}
