package com.fakhry.katakerjaapps.core.data.source.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KatakerjaDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        val AUTH_TOKEN = preferencesKey<String>("auth_token")
        val USER_ID = preferencesKey<Int>("user_id")
    }

    fun getAuthToken(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[AUTH_TOKEN] ?: ""
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[AUTH_TOKEN] = authToken
        }
    }

    fun getUserId(): Flow<Int> =
        dataStore.data.map { preferences ->
            preferences[USER_ID] ?: 0
        }

    suspend fun saveUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = userId
        }
    }
}