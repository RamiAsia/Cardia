package me.ramiasia.cardia.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Rami Asia on 10/7/16.
 */

public class ReadingSummary implements Comparable<ReadingSummary> {


    private long _id;

    // Time of the Reading Summary
    private Date time;

    // List of all the data points
    private List<DataPoint> dataPoints;

    // Highest heart rate recorded of the user
    private int highestHeartRate;

    // Lowest heart rate recorded of the user
    private int lowestHeartRate;

    // Mode of the recorded heart rates
    private ArrayList<Integer> modeHeartRate;

    public ReadingSummary(List<DataPoint> dataPoints){
        this.dataPoints = dataPoints;
        this.time = new Date();

        calculateStatistics();
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public Date getTime() {
        return time;
    }

    public List<DataPoint> getDataPoints() {
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

    private void calculateStatistics() {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, mode = 0;

        int[] heartRates = new int[dataPoints.size()];
        int index = 0;
        for (DataPoint dataPoint: this.dataPoints) {
            int heartRate = dataPoint.getHeartRate();
            if(heartRate < min) min = dataPoint.getHeartRate();
            if(heartRate > max) max = dataPoint.getHeartRate();
            heartRates[index++] = heartRate;
        }

        this.lowestHeartRate = min;
        this.highestHeartRate = max;
        this.modeHeartRate = calculateModes(heartRates);
    }

    private ArrayList<Integer> calculateModes(int[] heartRates){
        ArrayList<Integer> modes = new ArrayList<Integer>();
        Arrays.sort(heartRates);

        int oldMax = 0, currentMax = 0, currentValue = -1, oldValue = 0;

        for (Integer heartRate : heartRates) {
            if(heartRate == currentValue) {
                currentMax++;
            } else {
                if(currentMax == oldMax) {
                    modes.add(currentValue);
                } else if (currentMax > oldMax) {
                    modes.clear();
                    modes.add(currentValue);
                    oldMax = currentMax;
                }
                oldValue = currentValue;
                currentValue = heartRate;
                currentMax = 1;
            }
        }

        if(oldValue != currentValue) {
            if(currentMax == oldMax) {
                modes.add(currentValue);
            } else if (currentMax > oldMax) {
                modes.clear();
                modes.add(currentValue);
            }
        }

        return modes;
    }





    @Override
    public int compareTo(ReadingSummary o) {
        int comparison = this.time.compareTo(o.time);

        if (comparison < 0) return -1;
        else if (comparison > 0) return 1;
        else return 0;
    }
}
