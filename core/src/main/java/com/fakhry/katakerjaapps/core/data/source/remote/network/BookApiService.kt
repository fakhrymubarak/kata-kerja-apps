package com.fakhry.katakerjaapps.core.data.source.remote.network

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.ListBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist.show.WishlistBooksResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist.store.StoreWishlistResponse
import retrofit2.http.*

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
    ): ListBooksResponse

    /* Search Book*/
    @GET("books/category/{cat}")
    suspend fun getBooksByCat(
        @Path("cat") category: String,
    ): ListBooksResponse

    /* Wishlist Book*/
    @GET("wish/show/user/{id}")
    suspend fun getWishBooksById(
        @Path("id") idUser: Int,
    ): WishlistBooksResponse

    @FormUrlEncoded
    @POST("wish/store")
    suspend fun insertWishlist(
        @Header("Authorization") authToken: String,
        @Field("user_id") userId: Int,
        @Field("book_id") bookId: Int,
    ): StoreWishlistResponse
}