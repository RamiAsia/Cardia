package me.ramiasia.cardia.model;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.ramiasia.cardia.controller.HealthStatistics;

/**
 * Created by Rami Asia on 10/7/16.
 */

public class ReadingSummary implements Comparable<ReadingSummary> {


    private long id;

    // Time of the Reading Summary
    private DateTime time;

    // List of all the data points
    private ArrayList<DataPoint> dataPoints;

    // Count of heart rate samples taken
    private int dataPointCount;

    // Highest heart rate recorded of the user
    private int highestHeartRate;

    // Lowest heart rate recorded of the user
    private int lowestHeartRate;

    // Mode of the recorded heart rates
    private ArrayList<Integer> modeHeartRate;

    // Total energy expended
    private int energyExpended;



    // Constructors

    public ReadingSummary(){

    }

    public ReadingSummary(ArrayList<DataPoint> dataPoints){
        this.dataPoints = dataPoints;
        this.time = new DateTime();
    }

    public ReadingSummary(int id, DateTime time, int dataPointCount, int highestHeartRate, int lowestHeartRate, ArrayList<Integer> modes) {
        this.id = id;
        this.time = time;
        this.dataPointCount = dataPointCount;
        this.highestHeartRate = highestHeartRate;
        this.lowestHeartRate = lowestHeartRate;
        this.modeHeartRate = modes;
    }



    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDataPointCount() {
        return dataPointCount;
    }

    public void setDataPointCount(int dataPointCount) {
        this.dataPointCount = dataPointCount;
    }

    public DateTime getTime() {
        return time;
    }

    public ArrayList<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public int getHighestHeartRate() {
        return highestHeartRate;
    }

    public int getLowestHeartRate() {
        return lowestHeartRate;
    }

    public ArrayList<Integer> getModeHeartRate() {
        return modeHeartRate;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public void setDataPoints(ArrayList<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public void setHighestHeartRate(int highestHeartRate) {
        this.highestHeartRate = highestHeartRate;
    }

    public void setLowestHeartRate(int lowestHeartRate) {
        this.lowestHeartRate = lowestHeartRate;
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


    @Override
    public int compareTo(ReadingSummary o) {
        int comparison = this.time.compareTo(o.time);

        if (comparison < 0) return -1;
        else if (comparison > 0) return 1;
        else return 0;
    }
}
