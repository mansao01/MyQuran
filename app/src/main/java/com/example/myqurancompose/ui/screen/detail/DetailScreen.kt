@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myqurancompose.ui.screen.detail

import android.graphics.Typeface
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myqurancompose.helper.AudioHelper
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem
import com.example.myqurancompose.ui.SharedViewModel
import com.example.myqurancompose.ui.common.DetailUiState
import com.example.myqurancompose.ui.component.DetailLoadingWithShimmer
import com.example.myqurancompose.ui.component.ErrorScreen
import com.example.myqurancompose.ui.component.SurahVerseListItem
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToHome: () -> Unit,
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory),
    sharedViewModel: SharedViewModel
) {
    val quran = sharedViewModel.quranItem

    Log.d("Quran Detail", quran.toString())
    LaunchedEffect(Unit) {
        quran?.let { detailViewModel.getSurahVerseList(it.nomor) }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            quran?.let {
                DetailScreenTopBar(
                    scrollBehavior = scrollBehavior,
                    quran = it,
                    navigateToHome = navigateToHome
                )
            }
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when (uiState) {
                is DetailUiState.Loading -> DetailLoadingWithShimmer()
                is DetailUiState.Success -> DetailScreenContent(
                    quran = quran!!,
                    surahVerseItem = uiState.surahVerse,
                )

                is DetailUiState.Error -> ErrorScreen(refresh = {
                    quran?.let { quranData -> detailViewModel.getSurahVerseList(quranData.nomor) }
                })
            }
        }
    }


}

@Composable
fun DetailScreenContent(
    quran: ListSurahResponseItem,
    surahVerseItem: List<ListSurahVerseResponseItem>,
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()


    MoreDetailSection(
        quran = quran,
        scaffoldState = scaffoldState
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreDetailSection(
    quran: ListSurahResponseItem,
    scaffoldState: BottomSheetScaffoldState,
    content: @Composable () -> Unit,
) {
    var audioState by remember { mutableStateOf(false) }
    val spanned = HtmlCompat.fromHtml(quran.keterangan, HtmlCompat.FROM_HTML_MODE_COMPACT)
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = quran.nama)
                Text(text = quran.asma)
                Text(text = quran.arti)
                IconButton(onClick = {
                    if (audioState) {
                        AudioHelper.pauseStream()
                        audioState = false
                    } else {
                        AudioHelper.playStream(quran.audio)
                        audioState = true
                    }
                }) {
                    if (audioState) {
                        Icon(imageVector = Icons.Default.PauseCircle, contentDescription = "play")
                    } else {
                        Icon(imageVector = Icons.Default.PlayCircle, contentDescription = "pause")
                    }

                }

                Text(text = spanned.toAnnotatedString(), textAlign = TextAlign.Justify)
                Spacer(modifier = Modifier.height(8.dp))
            }
            DisposableEffect(quran.audio) {
                onDispose {
                    audioState = false
                    AudioHelper.releasePlayer()
                }
            }
        }
    ) {
        content()
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

fun Spanned.toAnnotatedString(): AnnotatedString = buildAnnotatedString {
    val spanned = this@toAnnotatedString
    append(spanned.toString())
    getSpans(0, spanned.length, Any::class.java).forEach { span ->
        val start = getSpanStart(span)
        val end = getSpanEnd(span)
        when (span) {
            is StyleSpan -> when (span.style) {
                Typeface.BOLD -> addStyle(SpanStyle(fontWeight = FontWeight.Bold), start, end)
                Typeface.ITALIC -> addStyle(SpanStyle(fontStyle = FontStyle.Italic), start, end)
                Typeface.BOLD_ITALIC -> addStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    ), start, end
                )
            }

            is UnderlineSpan -> addStyle(
                SpanStyle(textDecoration = TextDecoration.Underline),
                start,
                end
            )

            is ForegroundColorSpan -> addStyle(
                SpanStyle(color = Color(span.foregroundColor)),
                start,
                end
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DetailScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    quran: ListSurahResponseItem,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Column {
                Text(
                    text = quran.nama,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = quran.arti,
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

