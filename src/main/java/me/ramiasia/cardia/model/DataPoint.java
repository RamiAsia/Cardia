package me.ramiasia.cardia.model;

import java.util.Date;

/**
 * Created by Rami Asia on 10/7/16.
 */

public class DataPoint implements Comparable<DataPoint> {


    private long id;

    // Heart Rate is measured in beats per minute (BPM)
    private int heartRate;

    // Energy Expended is measured in Joules
    private int energyExpended;

    // RR interval measuring heart-beat variability with resolution of 1/1024 of a second
    private int rRInterval;

    // Time of the recording
    private Date time;

    public DataPoint(){
        this.heartRate = -1;
        this.energyExpended = -1;
        this.rRInterval = -1;
        this.time = new Date();
    }

    public DataPoint(int heartRate, int energyExpended, int rRInterval) {
        this.heartRate = heartRate;
        this.energyExpended = energyExpended;
        this.rRInterval = rRInterval;
        this.time = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getEnergyExpended() {
        return energyExpended;
    }

    public int getrRInterval() {
        return rRInterval;
    }

    public Date getTime() {
        return time;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setEnergyExpended(int energyExpended) {
        this.energyExpended = energyExpended;
    }

    public void setrRInterval(int rRInterval) {
        this.rRInterval = rRInterval;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    @Override
    public int compareTo(DataPoint o) {
        int comparison = this.time.compareTo(o.time);

        if (comparison < 0) return -1;
        else if (comparison > 0) return 1;
        else return 0;
    }
}
