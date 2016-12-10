package me.ramiasia.cardia.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import me.ramiasia.cardia.model.PeriodicSummary;
import me.ramiasia.cardia.model.ReadingSummary;

/**
 * Created by Rami Asia on 10/10/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database name and version number
    public static final String DATABASE_NAME = "healthManager";
    public static final int DATABASE_VERSION = 1;

    // Table codes
    public static int TABLE_PERIODIC_SUMMARY_CODE = 0;
    public static int TABLE_READING_SUMMARY_CODE = 1;


    // Table names
    private static final String TABLE_PERIODIC_SUMMARY = "periodicSummary";
    private static final String TABLE_SUMMARY_MODE = "summaryMode";
    private static final String TABLE_READING_SUMMARY = "readingSummary";


    /*
    * Column names:
    */

    // PeriodicSummary table columns:
    private static final String KEY_ID = "id";
    private static final String KEY_START_TIME = "startTime";
    private static final String KEY_END_TIME = "endTime";
    private static final String KEY_PERIOD_TYPE = "periodType";
    private static final String KEY_HIGHEST_HEART_RATE = "highestHeartRate";
    private static final String KEY_LOWEST_HEART_RATE = "lowestHeartRate";
    private static final String KEY_AVERAGE_HEART_RATE = "averageHeartRate";
    private static final String KEY_ENERGY_EXPENDED = "energyExpended";
    private static final String KEY_SUMMARY_TYPE = "summaryType";
    private static final String KEY_DAY = "day";
    private static final String KEY_MONTH = "month";
    private static final String KEY_YEAR = "year";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_HOUR = "hour";


    // ReadingSummary table columns:
    private static final String KEY_TIME = "time";
    private static final String KEY_DATAPOINT_COUNT = "dataPointCount";

    // PeriodicSummaryMode table columns:
    private static final String KEY_SUMMARY_ID = "summaryID";
    private static final String KEY_HRM_MODE = "mode";


    // Table creation queries:

    private static final String CREATE_TABLE_READING_SUMMARY = "Create table "
            + TABLE_READING_SUMMARY + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TIME + " DATETIME, "
            + KEY_DATAPOINT_COUNT + " INTEGER, "
            + KEY_HIGHEST_HEART_RATE + " INTEGER, "
            + KEY_LOWEST_HEART_RATE + " INTEGER, "
            + KEY_ENERGY_EXPENDED + " INTEGER"
            + KEY_DAY + " INTEGER"
            + KEY_MONTH + " INTEGER"
            + KEY_YEAR + " INTEGER"
            + KEY_MINUTE + " INTEGER"
            + KEY_HOUR + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_PERIODIC_SUMMARY = "Create table "
            + TABLE_PERIODIC_SUMMARY + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_START_TIME + " DATETIME, "
            + KEY_END_TIME + " DATETIME, "
            + KEY_PERIOD_TYPE + " TEXT, "
            + KEY_HIGHEST_HEART_RATE + " INTEGER, "
            + KEY_LOWEST_HEART_RATE + " INTEGER, "
            + KEY_AVERAGE_HEART_RATE + " INTEGER, "
            + KEY_ENERGY_EXPENDED + " INTEGER"
            + KEY_DAY + " INTEGER"
            + KEY_MONTH + " INTEGER"
            + KEY_YEAR + " INTEGER"
            + KEY_MINUTE + " INTEGER"
            + KEY_HOUR + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_SUMMARY_MODE = "Create table "
            + TABLE_SUMMARY_MODE + "( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_SUMMARY_ID + " INTEGER, "
            + KEY_SUMMARY_TYPE + " TEXT, "
            + KEY_HRM_MODE + " INTEGER" + ")";





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERIODIC_SUMMARY);
        db.execSQL(CREATE_TABLE_READING_SUMMARY);
        db.execSQL(CREATE_TABLE_SUMMARY_MODE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertReadingSummary (ReadingSummary readingSummary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME, readingSummary.getTime().toString());
        values.put(KEY_HIGHEST_HEART_RATE, readingSummary.getHighestHeartRate());
        values.put(KEY_LOWEST_HEART_RATE, readingSummary.getLowestHeartRate());
        values.put(KEY_ENERGY_EXPENDED, readingSummary.getEnergyExpended());
        values.put(KEY_DATAPOINT_COUNT, readingSummary.getDataPointCount());
        values.put(KEY_MINUTE, readingSummary.getTime().getMinuteOfHour());
        values.put(KEY_HOUR, readingSummary.getTime().getHourOfDay());
        values.put(KEY_DAY, readingSummary.getTime().getDayOfMonth());
        values.put(KEY_MONTH, readingSummary.getTime().getMonthOfYear());
        values.put(KEY_YEAR, readingSummary.getTime().getYear());
        return db.insert(TABLE_READING_SUMMARY, null, values);
    }

    public long insertPeriodicSummary(PeriodicSummary periodicSummary) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_START_TIME, periodicSummary.getStartTime().toString());
        values.put(KEY_END_TIME, periodicSummary.getEndTime().toString());
        values.put(KEY_PERIOD_TYPE, periodicSummary.getPeriodType());
        values.put(KEY_HIGHEST_HEART_RATE, periodicSummary.getHighestHeartRate());
        values.put(KEY_LOWEST_HEART_RATE, periodicSummary.getLowestHeartRate());
        values.put(KEY_AVERAGE_HEART_RATE, periodicSummary.getAverageHeartRate());
        values.put(KEY_ENERGY_EXPENDED, periodicSummary.getEnergyExpended());
        values.put(KEY_MINUTE, periodicSummary.getStartTime().getMinuteOfHour());
        values.put(KEY_HOUR, periodicSummary.getStartTime().getHourOfDay());
        values.put(KEY_DAY, periodicSummary.getStartTime().getDayOfMonth());
        values.put(KEY_MONTH, periodicSummary.getStartTime().getMonthOfYear());
        values.put(KEY_YEAR, periodicSummary.getStartTime().getYear());
        return db.insert(TABLE_PERIODIC_SUMMARY, null, values);
    }

    public void insertModes(long summaryID, int summaryType, List<Integer> modes) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values;
        for(Integer mode : modes) {
            values = new ContentValues();
            values.put(KEY_SUMMARY_ID, summaryID);
            values.put(KEY_HRM_MODE, mode);
            if (summaryType == TABLE_PERIODIC_SUMMARY_CODE)
                values.put(KEY_SUMMARY_TYPE, TABLE_PERIODIC_SUMMARY);
            else if (summaryType == TABLE_READING_SUMMARY_CODE)
                values.put(KEY_SUMMARY_TYPE, TABLE_READING_SUMMARY);

            db.insert(TABLE_SUMMARY_MODE, null, values);
        }
    }

    public List<Integer> getModes(long periodicSummaryID) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Integer> modes = new ArrayList<Integer>();

        String query = "Select * from " + TABLE_SUMMARY_MODE + " where " + KEY_SUMMARY_ID + " = " + periodicSummaryID;

        Cursor results = db.rawQuery(query, null);

        if (results.moveToFirst()) {
            do {
                modes.add(results.getInt(3));
            } while (results.moveToNext());
        }

        results.close();

        return modes;
    }

    public List<ReadingSummary> getReadingSummaries(DateTime from, DateTime to) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<ReadingSummary> readingSummaries = new ArrayList<ReadingSummary>();

        String query = "Select * from " + TABLE_READING_SUMMARY + " where  time >= '" + from.toString() + "' and time <= '" + to.toString() + "'";

        Cursor results = db.rawQuery(query, null);

        if (results.moveToFirst()) {
            do {
                ReadingSummary readingSummary = new ReadingSummary();
                readingSummary.setId(results.getLong(results.getColumnIndex(KEY_ID)));
                readingSummary.setDataPointCount(results.getInt(results.getColumnIndex(KEY_DATAPOINT_COUNT)));
                readingSummary.setEnergyExpended(results.getInt(results.getColumnIndex(KEY_ENERGY_EXPENDED)));
                readingSummary.setHighestHeartRate(results.getInt(results.getColumnIndex(KEY_HIGHEST_HEART_RATE)));
                readingSummary.setLowestHeartRate(results.getInt(results.getColumnIndex(KEY_LOWEST_HEART_RATE)));
                readingSummary.setTime(new DateTime(results.getString(results.getColumnIndex(KEY_TIME))));
                readingSummaries.add(readingSummary);
            } while (results.moveToNext());
        }

        results.close();
        return readingSummaries;
    }

    public List<PeriodicSummary> getPeriodicSummaries(int periodType, DateTime from, DateTime to){
        SQLiteDatabase db = this.getReadableDatabase();
        List<PeriodicSummary> periodicSummaries = new ArrayList<PeriodicSummary>();

        String query = "Select * from " + TABLE_PERIODIC_SUMMARY + " where " + KEY_PERIOD_TYPE + " = " + periodType + " and " +
                KEY_START_TIME + " >= '" + from.toString() + "' and " + KEY_END_TIME + " <= '" + to.toString() + "'";

        Cursor results = db.rawQuery(query, null);

        if (results.moveToFirst()) {
            do {
                PeriodicSummary periodicSummary = new PeriodicSummary();
                periodicSummary.setId(results.getInt(results.getColumnIndex(KEY_ID)));
                periodicSummary.setStartTime(new DateTime(results.getString(results.getColumnIndex(KEY_START_TIME))));
                periodicSummary.setEndTime(new DateTime(results.getString(results.getColumnIndex(KEY_END_TIME))));
                periodicSummary.setPeriodType(results.getInt(results.getColumnIndex(KEY_PERIOD_TYPE)));
                periodicSummary.setHighestHeartRate(results.getInt(results.getColumnIndex(KEY_HIGHEST_HEART_RATE)));
                periodicSummary.setLowestHeartRate(results.getInt(results.getColumnIndex(KEY_LOWEST_HEART_RATE)));
                periodicSummary.setAverageHeartRate(results.getInt(results.getColumnIndex(KEY_AVERAGE_HEART_RATE)));
                periodicSummary.setEnergyExpended(results.getInt(results.getColumnIndex(KEY_ENERGY_EXPENDED)));
                periodicSummaries.add(periodicSummary);
            } while (results.moveToNext());
        }

        results.close();

        return periodicSummaries;
    }

}
