package com.example.myqurancompose.data

import com.example.myqurancompose.network.api.ApiService
import com.example.myqurancompose.network.response.ListSurahResponse
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.network.response.ListSurahVerseResponse

interface MyQuranRepository {
    suspend fun getSurahList(): ListSurahResponse

    suspend fun getSurahVerseList(number: String): ListSurahVerseResponse
}

class NetworkMyQuranRepository(private val apiService: ApiService) : MyQuranRepository {
    override suspend fun getSurahList(): ListSurahResponse {
        return apiService.getSurahList()
    }

    override suspend fun getSurahVerseList(number: String): ListSurahVerseResponse {
        return apiService.getSurahVerseList(number)
    }

}