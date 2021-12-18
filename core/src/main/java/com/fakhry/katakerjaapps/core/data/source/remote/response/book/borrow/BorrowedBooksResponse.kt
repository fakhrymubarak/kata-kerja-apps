package com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow

import com.google.gson.annotations.SerializedName

data class BorrowedBooksResponse(

    @field:SerializedName("data")
	val pagingData: PagingData,

    @field:SerializedName("success")
	val success: Boolean,

    @field:SerializedName("message")
	val message: String
)