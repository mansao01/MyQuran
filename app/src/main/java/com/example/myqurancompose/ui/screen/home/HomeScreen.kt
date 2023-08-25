@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myqurancompose.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myqurancompose.R
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.ui.common.HomeUiState
import com.example.myqurancompose.ui.component.ErrorScreen
import com.example.myqurancompose.ui.component.LoadingScreenWithText
import com.example.myqurancompose.ui.component.SurahListItem

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    navigateToSetting: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToDetail: (String, String, String, String) -> Unit
) {
    when (uiState) {
        is HomeUiState.Loading -> LoadingScreenWithText()
        is HomeUiState.Success -> HomeContent(
            surahList = uiState.surah,
            navigateToDetail = navigateToDetail,
            navigate = navigateToSetting,
            scrollBehavior = scrollBehavior,
            modifier = modifier
        )

        is HomeUiState.Error -> ErrorScreen()
    }
}

@Composable
fun HomeContent(
    surahList: List<ListSurahResponseItem>,
    navigateToDetail: (String, String, String, String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { HomeScreenTopBar(scrollBehavior = scrollBehavior, navigate = { navigate() }) }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .padding(it)
        ) {
            SurahList(surahList = surahList, navigateToDetail = navigateToDetail)
        }
    }

}

@Composable
fun SurahList(
    surahList: List<ListSurahResponseItem>,
    navigateToDetail: (String, String, String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier
        .fillMaxWidth()
        .padding(bottom = 4.dp))
    {
        items(surahList, key = { it.nomor }) { data ->
            SurahListItem(
                surahItem = data,
                modifier = Modifier.clickable {
                    navigateToDetail(
                        data.nomor,
                        data.nama,
                        data.asma,
                        data.arti,
//                        data.keterangan
                    )
                })
        }
    }
    Log.d("Data", surahList.toString())

}

@ExperimentalMaterial3Api
@Composable
fun HomeScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.myquran),
                color = MaterialTheme.colorScheme.primary,
            )
        },
        actions = {
            IconButton(onClick = { navigate() }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Setting")
            }
        },
        modifier = modifier
    )
}