package com.example.myqurancompose.ui.common

import com.example.myqurancompose.network.response.ListSurahResponse
import com.example.myqurancompose.network.response.ListSurahResponseItem


sealed interface HomeUiState {
    data class Success(val surah: List<ListSurahResponseItem>):HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState

}