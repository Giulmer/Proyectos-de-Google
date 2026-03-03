package com.example.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmphibiansUiState{
    data class Success(val amphibianList: List<Amphibian>): AmphibiansUiState
    object Error: AmphibiansUiState
    object Loading: AmphibiansUiState
}

class AmphibiansViewModel(val amphibiansRepository: AmphibiansRepository): ViewModel() {
    private val _uiState = MutableStateFlow<AmphibiansUiState>(value = AmphibiansUiState.Loading)
    val uiState: StateFlow<AmphibiansUiState> = _uiState.asStateFlow()


    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            try {
                _uiState.value = AmphibiansUiState.Success(
                    amphibianList = amphibiansRepository.getAmphibians()
                )
            } catch (e: IOException) {
                _uiState.value = AmphibiansUiState.Error
            } catch (e: HttpException) {
                _uiState.value = AmphibiansUiState.Error

            }
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }

    }


}