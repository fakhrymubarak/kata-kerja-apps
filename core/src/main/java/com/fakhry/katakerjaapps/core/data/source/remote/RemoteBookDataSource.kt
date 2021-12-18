package com.fakhry.katakerjaapps.core.data.source.remote

import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiResponse
import com.fakhry.katakerjaapps.core.data.source.remote.network.BookApiService
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.SearchedBookData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteBookDataSource @Inject constructor(private val bookApiService: BookApiService) {
    fun getBorrowedBooksById(userId: Int): Flow<ApiResponse<List<BorrowedBooksData>>> =
        flow {
            try {
                val response = bookApiService.getBorrowedBooksById(userId)
                if (response.success) {
                    emit(ApiResponse.Success(response.pagingData.listBorrowedBooks))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    fun getBookDetailsById(bookId: Int): Flow<ApiResponse<BookDetailsData>> =
        flow {
            try {
                val response = bookApiService.getBookDetailsById(bookId)
                if (response.success) {
                    emit(ApiResponse.Success(response.bookDetailsData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    fun getSearchedBooks(query: String): Flow<ApiResponse<List<SearchedBookData>>> =
        flow {
            try {
                val response = bookApiService.getSearchedBooks(query)
                if (response.success) {
                    emit(ApiResponse.Success(response.pagingData.listSearchedBookData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

}