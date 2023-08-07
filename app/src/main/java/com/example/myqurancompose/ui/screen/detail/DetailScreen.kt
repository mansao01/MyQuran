package com.example.myqurancompose.ui.screen.detail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem
import com.example.myqurancompose.ui.common.DetailUiState
import com.example.myqurancompose.ui.component.ErrorScreen
import com.example.myqurancompose.ui.component.LoadingScreenWithText
import com.example.myqurancompose.ui.component.SurahVerseListItem

@Composable
fun DetailScreen(
    number: String,
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    LaunchedEffect(Unit){

        viewModel.getSurahVerseList(number)
    }
    when(uiState){
        is DetailUiState.Loading -> LoadingScreenWithText()
        is DetailUiState.Success -> DetailScreenContent(surahVerseItem = uiState.surahVerse)
        is DetailUiState.Error -> ErrorScreen()
    }

}

@Composable
fun DetailScreenContent(
    surahVerseItem: List<ListSurahVerseResponseItem>
) {

    SurahVerseList(surahVerseItem = surahVerseItem)

}

@Composable
fun SurahVerseList(
    surahVerseItem: List<ListSurahVerseResponseItem>
) {
    LazyColumn{
        items(surahVerseItem){data ->
            SurahVerseListItem(surahVerseListItem = data)
        }
    }
}