package com.example.myqurancompose.network.api

import com.example.myqurancompose.network.response.ListSurahResponse
import com.example.myqurancompose.network.response.ListSurahResponseItem
import com.example.myqurancompose.network.response.ListSurahVerseResponse
import com.example.myqurancompose.network.response.ListSurahVerseResponseItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("data")
    suspend fun getSurahList():List<ListSurahResponseItem>

    @GET("surat/{number}")
    suspend fun getSurahVerseList(
        @Path("number") number:String
    ):List<ListSurahVerseResponseItem>
}