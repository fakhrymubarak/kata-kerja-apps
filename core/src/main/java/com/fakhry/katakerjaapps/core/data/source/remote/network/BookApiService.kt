package com.fakhry.katakerjaapps.core.data.source.remote.network

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface BookApiService {
    /* Details Book */
    @GET("books/show/{id}")
    suspend fun getBookDetailsById(
        @Path("id") bookId: Int,
    ): BookDetailsResponse

    /* List Borrowed Book */
    @GET("borrow/show/user/{id}")
    suspend fun getBorrowedBooksById(
        @Path("id") idUser: Int,
    ): BorrowedBooksResponse
}