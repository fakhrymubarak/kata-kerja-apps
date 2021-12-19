package com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist

import com.google.gson.annotations.SerializedName

data class WishlistBooksResponse(

    @field:SerializedName("data")
    val data: List<WishlistBooksData>,

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String
)