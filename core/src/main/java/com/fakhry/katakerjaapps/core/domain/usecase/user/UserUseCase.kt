package com.fakhry.katakerjaapps.core.domain.usecase.user

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Login
import com.fakhry.katakerjaapps.core.domain.model.Register
import com.fakhry.katakerjaapps.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface  UserUseCase {
    fun getUserById(authToken: String, userId: Int): Flow<Resource<User>>
    fun updateUserById(authToken: String, userId: Int): Flow<Resource<User>>
    fun postLogin(email: String, password: String): Flow<Resource<Login>>
    fun postRegister(
        email: String,
        password: String,
        name: String,
        bornDate: String,
        phoneNumber: String,
    ): Flow<Resource<Register>>

    fun saveAuthToken(authToken: String)
    fun getAuthToken(): Flow<String>

    fun saveUserId(userId: Int)
    fun getUserId(): Flow<Int>
    fun clearUserDataStore()
}