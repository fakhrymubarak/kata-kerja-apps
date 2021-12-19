package com.fakhry.katakerjaapps.core.data.source.remote.network

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.SearchedBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist.WishlistBooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApiService {
    /* List Borrowed Book */
    @GET("borrow/show/user/{id}")
    suspend fun getBorrowedBooksById(
        @Path("id") idUser: Int,
    ): BorrowedBooksResponse

    /* Details Book */
    @GET("books/show/{id}")
    suspend fun getBookDetailsById(
        @Path("id") bookId: Int,
    ): BookDetailsResponse

    /* Search Book*/
    @GET("books/search?")
    suspend fun getSearchedBooks(
        @Query("q") query: String,
    ): SearchedBooksResponse

    /* Wishlist Book*/
    @GET("wish/show/user/{id}")
    suspend fun getWishBooksById(
        @Path("id") idUser: Int,
    ): WishlistBooksResponse
}