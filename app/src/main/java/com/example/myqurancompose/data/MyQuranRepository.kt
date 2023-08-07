package com.example.myqurancompose.data

import com.example.myqurancompose.network.api.ApiService
import com.example.myqurancompose.network.response.ListSurahResponse
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.network.response.ListSurahVerseResponse
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem

interface MyQuranRepository {
    suspend fun getSurahList(): List<ListSurahResponseItem>

    suspend fun getSurahVerseList(number: String): List<ListSurahVerseResponseItem>
}

class NetworkMyQuranRepository(private val apiService: ApiService) : MyQuranRepository {
    override suspend fun getSurahList(): List<ListSurahResponseItem> {
        return apiService.getSurahList()
    }

    override suspend fun getSurahVerseList(number: String): List<ListSurahVerseResponseItem> {
        return apiService.getSurahVerseList(number)
    }

}