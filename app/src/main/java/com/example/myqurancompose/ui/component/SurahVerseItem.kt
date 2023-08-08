package com.example.myqurancompose.ui.component

import android.icu.text.LocaleDisplayNames.UiListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem

@Composable
fun SurahVerseListItem(
    surahVerseListItem: ListSurahVerseResponseItem,
    modifier: Modifier = Modifier
) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .padding(top = 8.dp)) {
        Column(
            modifier = modifier
                .padding(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = surahVerseListItem.nomor,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .background(MaterialTheme.colorScheme.background, shape = CircleShape),
//                    .drawBehind {
//                        drawCircle(
//                            color = Color.Cyan,
//                            radius = this.size.minDimension
//                        )
//                    },
                    textAlign = TextAlign.Start
                )
                Text(text = surahVerseListItem.ar, textAlign = TextAlign.End, )
            }
            Text(
                text = surahVerseListItem.id,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }

}

@Preview
@Composable
fun SurahVerseItemPrev() {

}