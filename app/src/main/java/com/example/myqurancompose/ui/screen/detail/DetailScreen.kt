@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myqurancompose.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem
import com.example.myqurancompose.ui.common.DetailUiState
import com.example.myqurancompose.ui.component.ErrorScreen
import com.example.myqurancompose.ui.component.LoadingScreenWithText
import com.example.myqurancompose.ui.component.SurahVerseListItem
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    number: String,
    surah: String,
    asma: String,
    arti: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToHome: () -> Unit,
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    LaunchedEffect(Unit) {

        detailViewModel.getSurahVerseList(number)
    }
    when (uiState) {
        is DetailUiState.Loading -> LoadingScreenWithText()
        is DetailUiState.Success -> DetailScreenContent(
            surah = surah,
            asma = asma,
            arti = arti,
            scrollBehavior = scrollBehavior,
            surahVerseItem = uiState.surahVerse,
            navigateToHome = navigateToHome,
            modifier = modifier
        )

        is DetailUiState.Error -> ErrorScreen(refresh = {
            detailViewModel.getSurahVerseList(number)
        })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    surah: String,
    asma: String,
    arti: String,
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToHome: () -> Unit,
    surahVerseItem: List<ListSurahVerseResponseItem>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DetailScreenTopBar(
                scrollBehavior = scrollBehavior,
                surah = surah,
                navigateToHome = navigateToHome,
                asma = asma
            )
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                MoreDetailSection(
                    surahVerseItem = surahVerseItem, surah = surah,
                    asma = asma,
                    arti = arti,
                )


            }
        }
    }

}

@Composable
fun SurahVerseList(
    surahVerseItem: List<ListSurahVerseResponseItem>
) {
    LazyColumn(
        modifier = Modifier
            .padding(bottom = 4.dp)
    ) {
        items(surahVerseItem, key = { it.id }) { data ->
            SurahVerseListItem(surahVerseListItem = data)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreDetailSection(
    surahVerseItem: List<ListSurahVerseResponseItem>,
    surah: String,
    asma: String,
    arti: String,
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = asma)
                Text(text = surah)
                Text(text = arti)
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "More detail",
                textDecoration = TextDecoration.Underline,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.clickable {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            )
            SurahVerseList(surahVerseItem = surahVerseItem)
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    surah: String,
    asma: String,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Column {
                Text(
                    text = surah,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = asma,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.clickable { navigateToHome() })
        },
        modifier = modifier
    )
}

