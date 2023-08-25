package com.example.myqurancompose.ui.screen.setting

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myqurancompose.MyQuranApplication
import com.example.myqurancompose.data.PrefencesRepository
import com.example.myqurancompose.ui.common.SettingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val preferencesRepository: PrefencesRepository
) : ViewModel() {
    val uiState: StateFlow<SettingUiState.SettingUiState> =
        preferencesRepository.isDarkMode.map { isDarkMode ->
            SettingUiState.SettingUiState(isDarkMode)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = SettingUiState.SettingUiState()
        )

    fun selectedTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            preferencesRepository.saveThemePreferences(isDarkMode)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyQuranApplication
                SettingViewModel(application.preferencesRepository)
            }
        }
    }
}