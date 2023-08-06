package com.example.myqurancompose.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.ui.common.HomeUiState
import com.example.myqurancompose.ui.component.ErrorScreen
import com.example.myqurancompose.ui.component.LoadingScreenWithText
import com.example.myqurancompose.ui.component.SurahListItem

@Composable
fun HomeScreen(
    uiState:HomeUiState,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreenWithText()
        is HomeUiState.Success -> SurahList(surahList = uiState.surah)

        is HomeUiState.Error -> ErrorScreen()
    }
}

@Composable
fun SurahList(
    surahList:List<ListSurahResponseItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()){
        items(surahList){ data ->
            SurahListItem(surahItem = data)
        }
    }

}