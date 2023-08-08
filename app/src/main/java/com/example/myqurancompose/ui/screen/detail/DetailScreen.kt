package com.example.myqurancompose.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
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
    surah: String,
    asma: String,
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    LaunchedEffect(Unit) {

        viewModel.getSurahVerseList(number)
    }
    when (uiState) {
        is DetailUiState.Loading -> LoadingScreenWithText()
        is DetailUiState.Success -> DetailScreenContent(
            surah = surah,
            asma = asma,
            surahVerseItem = uiState.surahVerse
        )

        is DetailUiState.Error -> ErrorScreen()
    }

}

@Composable
fun DetailScreenContent(
    surah: String,
    asma: String,
    surahVerseItem: List<ListSurahVerseResponseItem>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Text(text = surah, style = MaterialTheme.typography.titleLarge)
        Text(text = asma, style = MaterialTheme.typography.titleMedium)

        SurahVerseList(surahVerseItem = surahVerseItem)
    }

}

@Composable
fun SurahVerseList(
    surahVerseItem: List<ListSurahVerseResponseItem>
) {
    LazyColumn {
        items(surahVerseItem) { data ->
            SurahVerseListItem(surahVerseListItem = data)
        }
    }
}