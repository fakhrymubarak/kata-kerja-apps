package com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow

import com.google.gson.annotations.SerializedName

data class LinksItem(

	@field:SerializedName("active")
	val active: Boolean,

	@field:SerializedName("label")
	val label: String,

	@field:SerializedName("url")
	val url: Any
)