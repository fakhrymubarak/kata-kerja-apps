package com.fakhry.katakerjaapps.core.data.source.repository

import com.fakhry.katakerjaapps.core.data.source.remote.RemoteBookDataSource
import com.fakhry.katakerjaapps.core.domain.repository.IBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val remoteBookDataSource: RemoteBookDataSource,
) : IBookRepository {

}