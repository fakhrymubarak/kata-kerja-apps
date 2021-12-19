package com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistBooksData(

    @field:SerializedName("updated_at")
    val updatedAt: String,

    @field:SerializedName("user_id")
    val userId: Int,

    @field:SerializedName("created_at")
    val createdAt: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("book_id")
    val bookId: Int
)