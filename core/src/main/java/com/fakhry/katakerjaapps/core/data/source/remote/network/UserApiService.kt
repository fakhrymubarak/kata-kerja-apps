package com.fakhry.katakerjaapps.core.data.source.remote.network

import com.fakhry.katakerjaapps.core.data.source.remote.response.login.LoginResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.register.RegisterResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.detail.UserDetailsResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.update.UserUpdateResponse
import retrofit2.http.*

interface UserApiService {
    /* Details User */
    @GET("users/show/{id}")
    suspend fun getUserById(
        @Header("Authorization") authToken: String,
        @Path("id") idUser: Int,
    ): UserDetailsResponse

    /* Update User */
    @PUT("users/update/{id}")
    suspend fun updateUserById(
        @Header("Authorization") authToken: String,
        @Path("id") idUser: Int,
    ): UserUpdateResponse

    /* Login */
    @FormUrlEncoded
    @POST("users/login")
    suspend fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    /* Register */
    @FormUrlEncoded
    @POST("users/register")
    suspend fun postRegister(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("tglLahir") tglLahir: String,
        @Field("telp") telp: String,
        @Field("member_sejak") memberSejak: String,
        @Field("c_password") c_password: String = password,
        @Field("staff_sejak") staffSejak: String = "2000-01-01",
        @Field("id_role") idRole: Int = 4,
    ): RegisterResponse
}