package com.example.myqurancompose

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myqurancompose.data.AppContainer
import com.example.myqurancompose.data.DefaultAppContainer
import com.example.myqurancompose.data.PrefencesRepository

private const val THEME_PREFERENCES_NAME = "theme_preferences_name"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_PREFERENCES_NAME
)
class MyQuranApplication:Application(){
    lateinit var container:AppContainer
    lateinit var preferencesRepository: PrefencesRepository

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
        preferencesRepository = PrefencesRepository(dataStore)

    }
}