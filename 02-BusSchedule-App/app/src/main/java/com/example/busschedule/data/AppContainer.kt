package com.example.busschedule.data

import android.content.Context
import com.example.busschedule.data.database.BusScheduleDataBase
import com.example.busschedule.data.repository.BusScheduleRepository
import com.example.busschedule.data.repository.BusScheduleRepositoryImpl

interface AppContainer {
    val busScheduleRepository: BusScheduleRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    override val busScheduleRepository: BusScheduleRepository by lazy {
        BusScheduleRepositoryImpl(
            BusScheduleDataBase.getDataBase(context = context).BusScheduleDao()
        )
    }
}