package com.example.myqurancompose.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myqurancompose.network.response.ListSurahResponse
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem


sealed interface HomeUiState {
    data class Success(val surah: List<ListSurahResponseItem>):HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState

}

sealed interface DetailUiState{
    data class Success(val surahVerse: List<ListSurahVerseResponseItem>):DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

sealed interface SettingUiState {
    data class SettingUiState(
        val isDarkMode: Boolean = false,
        val title: String = if (isDarkMode) "Dark Mode" else "Light Mode",
        val icon: ImageVector =
            if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode
    )

}