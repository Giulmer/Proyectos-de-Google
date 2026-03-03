package com.example.busschedule.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.model.BusScheduleEntity

@Database(entities = [BusScheduleEntity::class], version = 1, exportSchema = false)
abstract class BusScheduleDataBase: RoomDatabase() {
    abstract fun BusScheduleDao(): BusScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: BusScheduleDataBase? = null

        fun getDataBase(context: Context): BusScheduleDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context = context, BusScheduleDataBase::class.java, "bus_schedule_database")
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }
}