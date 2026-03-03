package com.example.busschedule.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class BusScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "stop_name")
    val stopName: String,

    @ColumnInfo(name = "arrival_time")
    val arrivalTimeInMillis: Int
)