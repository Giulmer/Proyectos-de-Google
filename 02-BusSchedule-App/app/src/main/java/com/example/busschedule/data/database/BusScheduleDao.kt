package com.example.busschedule.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.busschedule.model.BusScheduleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(busSchedule: BusScheduleEntity)

    @Update
    suspend fun update(busSchedule: BusScheduleEntity)

    @Delete
    suspend fun delete(busSchedule: BusScheduleEntity)

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getBusSchedule(stopName : String): Flow<BusScheduleEntity>

    @Query("SELECT * FROM schedule ORDER BY arrival_time ASC")
    fun getAllBusSchedules(): Flow<List<BusScheduleEntity>>

}