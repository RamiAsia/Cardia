package me.ramiasia.cardia.controller;

import java.util.ArrayList;
import java.util.Arrays;

import me.ramiasia.cardia.model.DataPoint;

/**
 * Created by Rami Asia on 10/10/16.
 */

public class HealthStatistics {

    private ArrayList<DataPoint> dataPoints;
    ArrayList<Integer> modeHeartRate;
    private int lowestHeartRate;
    private int highestHeartRate;




    public HealthStatistics(){

    }

    public HealthStatistics(ArrayList<DataPoint> dataPoints){
        this.dataPoints = dataPoints;
    }


    // Method to calculate the statistics for the data.
    public void calculateStatistics() {

        // No statistics to calculate if there are no data points, so exit the method
        if (dataPoints == null) return;

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


    // Method to calculate the modes of the data points
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

    public ArrayList<DataPoint> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(ArrayList<DataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }

    public ArrayList<Integer> getModeHeartRate() {
        return modeHeartRate;
    }

    public void setModeHeartRate(ArrayList<Integer> modeHeartRate) {
        this.modeHeartRate = modeHeartRate;
    }

    public int getLowestHeartRate() {
        return lowestHeartRate;
    }

    public void setLowestHeartRate(int lowestHeartRate) {
        this.lowestHeartRate = lowestHeartRate;
    }

    public int getHighestHeartRate() {
        return highestHeartRate;
    }

    public void setHighestHeartRate(int highestHeartRate) {
        this.highestHeartRate = highestHeartRate;
    }
}
