package com.fakhry.katakerjaapps.core.data.source.remote

import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteBookDataSource @Inject constructor(private val apiService: ApiService) {
}