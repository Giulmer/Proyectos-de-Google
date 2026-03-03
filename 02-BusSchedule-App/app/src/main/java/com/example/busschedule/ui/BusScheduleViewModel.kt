/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.busschedule.BusScheduleApplication
import com.example.busschedule.data.repository.BusScheduleRepository
import com.example.busschedule.model.BusScheduleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class BusScheduleViewModel(val busScheduleRepository: BusScheduleRepository): ViewModel() {

    // Get example bus schedule
    val busScheduleUiState: StateFlow<uiState> =
        busScheduleRepository.getAllBusSchedule().map { uiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = uiState()
            )


    // Get example bus schedule by stop
//    fun getScheduleFor(stopName: String): Flow<List<BusScheduleEntity>> = flowOf(
//        listOf(
//            BusScheduleEntity(
//                1,
//                "Example Street",
//                0
//            )
//        )
//    )

    fun getScheduleFor(stopName: String): Flow<List<BusScheduleEntity>> {
        return busScheduleRepository.getAllBusSchedule().map {
            list -> list.filter {it.stopName == stopName}
        }
        }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BusScheduleApplication)
                val busScheduleRepository = application.container.busScheduleRepository
                BusScheduleViewModel(busScheduleRepository)
            }
        }
    }
}

data class uiState(val busScheduleList: List<BusScheduleEntity> = listOf())