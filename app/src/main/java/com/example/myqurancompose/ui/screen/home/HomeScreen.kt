package com.example.myqurancompose.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
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
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    navigateToDetail: (String, String, String) -> Unit
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreenWithText()
        is HomeUiState.Success -> SurahList(surahList = uiState.surah, navigateToDetail)

        is HomeUiState.Error -> ErrorScreen()
    }
}

@Composable
fun SurahList(
    surahList: List<ListSurahResponseItem>,
    navigateToDetail: (String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(surahList) { data ->
            SurahListItem(
                surahItem = data,
                modifier = Modifier.clickable {
                    navigateToDetail(
                        data.nomor,
                        data.nama,
                        data.asma
                    )
                })
        }
    }
    Log.d("Data", surahList.toString())

}