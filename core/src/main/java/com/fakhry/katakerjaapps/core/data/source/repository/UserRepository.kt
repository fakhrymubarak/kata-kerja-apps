package com.fakhry.katakerjaapps.core.data.source.repository

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.data.source.remote.RemoteUserDataSource
import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiResponse
import com.fakhry.katakerjaapps.core.domain.model.Login
import com.fakhry.katakerjaapps.core.domain.model.Register
import com.fakhry.katakerjaapps.core.domain.model.User
import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import com.fakhry.katakerjaapps.core.helper.UserDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val remoteUserDataSource: RemoteUserDataSource,
) : IUserRepository {
    override fun getUserById(userId: Int): Flow<Resource<User>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteUserDataSource.getUserById(userId).first()) {
                is ApiResponse.Success -> {
                    val data = UserDataMapper.User.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun updateUserById(userId: Int): Flow<Resource<User>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse =
                remoteUserDataSource.updateUserById(userId).first()) {
                is ApiResponse.Success -> {
                    val data = UserDataMapper.User.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun postLogin(email: String, password: String): Flow<Resource<Login>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse =
                remoteUserDataSource.postLogin(email, password).first()) {
                is ApiResponse.Success -> {
                    val data = UserDataMapper.Login.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun postRegister(
        email: String,
        password: String,
        name: String,
        bornDate: String,
        phoneNumber: String
    ): Flow<Resource<Register>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse =
                remoteUserDataSource.postRegister(
                    email,
                    password,
                    name,
                    bornDate,
                    phoneNumber
                )
                    .first()) {
                is ApiResponse.Success -> {
                    val data = UserDataMapper.Register.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }
}