package com.example.myqurancompose.ui.common

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