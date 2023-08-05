package com.example.myqurancompose

import android.app.Application
import com.example.myqurancompose.data.AppContainer
import com.example.myqurancompose.data.DefaultAppContainer

class MyQuranApplication:Application(){
    lateinit var container:AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}