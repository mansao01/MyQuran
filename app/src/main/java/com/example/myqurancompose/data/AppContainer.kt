package com.example.myqurancompose.data

import com.example.myqurancompose.network.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val myQuranRepository: MyQuranRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://api.npoint.io/99c279bb173a6e28359c/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitApiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    override val myQuranRepository: MyQuranRepository by lazy {
        NetworkMyQuranRepository(retrofitApiService)
    }
}