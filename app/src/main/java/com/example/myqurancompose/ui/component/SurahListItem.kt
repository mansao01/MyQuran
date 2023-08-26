package com.example.myqurancompose.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.myqurancompose.network.response.ListSurahResponseItem

@Composable
fun SurahListItem(
    surahItem: ListSurahResponseItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Column {
            Text(
                text = surahItem.nama,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                text = surahItem.arti,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp),
            )
            Text(
                text = "${surahItem.ayat} Ayat",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .padding(bottom = 4.dp)
            )


        }
    }

}