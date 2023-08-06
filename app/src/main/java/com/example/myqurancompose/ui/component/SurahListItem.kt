package com.example.myqurancompose.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myqurancompose.network.response.ListSurahResponseItem

@Composable
fun SurahListItem(
    surahItem:ListSurahResponseItem
) {
    Text(text = surahItem.nama)
    
}