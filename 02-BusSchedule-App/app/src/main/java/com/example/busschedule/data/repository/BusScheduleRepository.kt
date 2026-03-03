package com.example.busschedule.data.repository

import com.example.busschedule.data.database.BusScheduleDao
import com.example.busschedule.model.BusScheduleEntity
import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
    suspend fun insert(busSchedule: BusScheduleEntity)
    suspend fun update(busSchedule: BusScheduleEntity)
    suspend fun delete(busSchedule: BusScheduleEntity)

    fun getBusSchedule(stopName: String): Flow<BusScheduleEntity>
    fun getAllBusSchedule(): Flow<List<BusScheduleEntity>>
}

class BusScheduleRepositoryImpl(private val busScheduleDao: BusScheduleDao): BusScheduleRepository {
    override suspend fun insert(busSchedule: BusScheduleEntity) {
        busScheduleDao.insert(busSchedule)
    }
    override suspend fun update(busSchedule: BusScheduleEntity) = busScheduleDao.update(busSchedule)
    override suspend fun delete(busSchedule: BusScheduleEntity) = busScheduleDao.delete(busSchedule)

        override fun getBusSchedule(stopName: String): Flow<BusScheduleEntity> = busScheduleDao.getBusSchedule(stopName)
            override fun getAllBusSchedule() : Flow<List<BusScheduleEntity>> = busScheduleDao.getAllBusSchedules()


}