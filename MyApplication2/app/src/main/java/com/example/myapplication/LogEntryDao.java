package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogEntryDao {
    @Insert
    void insertAll(LogEntry... logEntries);

    @Query("SELECT * FROM logentry")
    List<LogEntry> getAll();

    @Delete
    void delete(LogEntry logEntry);


}
