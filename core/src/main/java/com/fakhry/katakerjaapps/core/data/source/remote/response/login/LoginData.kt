package com.fakhry.katakerjaapps.core.data.source.remote.response.login

import com.google.gson.annotations.SerializedName

data class LoginData(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id_role")
	val idRole: Int,

	@field:SerializedName("token")
	val token: String
)