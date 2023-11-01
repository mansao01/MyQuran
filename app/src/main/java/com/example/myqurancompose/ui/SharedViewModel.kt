package com.example.myqurancompose.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myqurancompose.network.response.ListSurahResponseItem

class SharedViewModel : ViewModel() {

    var quranItem by mutableStateOf<ListSurahResponseItem?>(null)
        private set

    fun addItem(newItem: ListSurahResponseItem) {
        quranItem = newItem
    }

}