@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myqurancompose.ui.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun DetailScreen(
    number: String,
    surah: String,
    asma: String,
    arti: String,
//    keterangan: String,
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
            arti = arti,
//            keterangan = keterangan,
            surahVerseItem = uiState.surahVerse,
            modifier = modifier
        )

        is DetailUiState.Error -> ErrorScreen()
    }

}

@Composable
fun DetailScreenContent(
    surah: String,
    asma: String,
    arti: String,
//    keterangan: String,
    surahVerseItem: List<ListSurahVerseResponseItem>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = asma,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(text = surah, style = MaterialTheme.typography.titleMedium)
        MoreDetailSection(
            surahVerseItem = surahVerseItem, surah = surah,
            asma = asma,
            arti = arti,
//            keterangan = keterangan
        )


    }

}

@Composable
fun SurahVerseList(
    surahVerseItem: List<ListSurahVerseResponseItem>
) {
    LazyColumn {
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
//    keterangan: String
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
//                Text(text = keterangan, modifier = Modifier.align(Alignment.Start))
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
    //    val sheetState = rememberModalBottomSheetState()
//    var isSheetOpen by rememberSaveable {
//        mutableStateOf(false)
//    }
//    Text(
//        text = "More detail",
//        textDecoration = TextDecoration.Underline,
//        fontStyle = FontStyle.Italic,
//        style = MaterialTheme.typography.bodySmall,
//        modifier = Modifier.clickable {
//            isSheetOpen = true
//        }
//    )
//    if (isSheetOpen) {
//        ModalBottomSheet(
//            sheetState = sheetState,
//            onDismissRequest = { isSheetOpen = false }) {
//            Text(text = "Cek")
//        }
//    }
}

