package com.fakhry.katakerjaapps.core.data.source.local

import com.fakhry.katakerjaapps.core.data.source.local.datastore.KatakerjaDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalUserDataSource @Inject constructor(private val katakerjaDataStore: KatakerjaDataStore) {
    fun getAuthToken(): Flow<String> = katakerjaDataStore.getAuthToken()

    suspend fun saveAuthToken(authToken: String) {
        katakerjaDataStore.saveAuthToken(authToken)
    }
    fun getUserId(): Flow<Int> = katakerjaDataStore.getUserId()

    suspend fun saveUserId(userId: Int) {
        katakerjaDataStore.saveUserId(userId)
    }
}