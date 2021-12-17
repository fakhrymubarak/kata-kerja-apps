package com.fakhry.katakerjaapps.core.data.source.remote

import com.fakhry.katakerjaapps.core.data.source.remote.network.BookApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteBookDataSource @Inject constructor(private val bookApiService: BookApiService) {
}