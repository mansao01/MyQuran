package com.example.myqurancompose.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    val isDarkMode: Flow<Boolean> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences", it)
                emit(emptyPreferences())
            } else throw it
        }.map { preferences ->
            preferences[IS_DARK_MODE] ?: true

        }

    suspend fun saveThemePreferences(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }

    private companion object {
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        const val TAG = "ProfilePreferencesRepo"
    }
}