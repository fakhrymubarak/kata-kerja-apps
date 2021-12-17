package com.fakhry.katakerjaapps.core.data.source.remote

import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiResponse
import com.fakhry.katakerjaapps.core.data.source.remote.network.UserApiService
import com.fakhry.katakerjaapps.core.data.source.remote.response.login.LoginData
import com.fakhry.katakerjaapps.core.data.source.remote.response.register.RegisterData
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.detail.UserDetailsData
import com.fakhry.katakerjaapps.core.data.source.remote.response.user.update.UserUpdateData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteUserDataSource @Inject constructor(private val userApiService: UserApiService) {
    fun getUserById(userId: Int): Flow<ApiResponse<UserDetailsData>> =
        flow {
            try {
                val response = userApiService.getUserById(userId)
                if (response.success) {
                    emit(ApiResponse.Success(response.userDetailsData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    fun updateUserById(userId: Int): Flow<ApiResponse<UserUpdateData>> =
        flow {
            try {
                val response = userApiService.updateUserById(userId)
                if (response.success) {
                    emit(ApiResponse.Success(response.userUpdateData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    fun postLogin(email: String, password: String): Flow<ApiResponse<LoginData>> =
        flow {
            try {
                val response = userApiService.postLogin(email, password)
                if (response.success) {
                    emit(ApiResponse.Success(response.loginData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    fun postRegister(
        email: String,
        password: String,
        name: String,
        bornDate: String,
        phoneNumber: String,
    ): Flow<ApiResponse<RegisterData>> =
        flow {
            try {
                val response = userApiService.postRegister(
                    email,
                    password,
                    name,
                    bornDate,
                    phoneNumber,
                    currentDate
                )
                if (response.success) {
                    emit(ApiResponse.Success(response.registerData))
                } else {
                    emit(ApiResponse.Error(response.message))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Timber.e(e)
            }
        }.flowOn(Dispatchers.IO)

    private val currentDate =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
}