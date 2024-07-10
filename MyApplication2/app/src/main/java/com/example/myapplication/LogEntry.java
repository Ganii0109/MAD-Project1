package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "logentry")
public class LogEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;


    public String dateTime;


    public double sleepHours;

    public String sleepQuality;


    public double exerciseHours;


    public double weight;

    public LogEntry() {
    }
    public LogEntry(String dateTime, double sleepHours, String sleepQuality, double exerciseHours, double weight) {
        this.dateTime = dateTime;
        this.sleepHours = sleepHours;
        this.sleepQuality = sleepQuality;
        this.exerciseHours = exerciseHours;
        this.weight = weight;
    }





    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(double sleepHours) {
        this.sleepHours = sleepHours;
    }
    public String getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(String sleepQuality) {
        this.sleepQuality = sleepQuality;
    }


    public double getExerciseHours() {
        return exerciseHours;
    }

    public void setExerciseHours(double exerciseHours) {
        this.exerciseHours = exerciseHours;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "id=" + id +
                ", dateTime='" + dateTime + '\'' +
                ", sleepHours=" + sleepHours +
                ", sleepQuality='" + sleepQuality + '\'' +
                ", exerciseHours=" + exerciseHours +
                ", weight=" + weight +
                '}';
    }
}
