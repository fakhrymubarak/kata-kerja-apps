package com.fakhry.katakerjaapps.core.domain.usecase.user

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Login
import com.fakhry.katakerjaapps.core.domain.model.Register
import com.fakhry.katakerjaapps.core.domain.model.User
import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val mIUserRepository: IUserRepository) :
    UserUseCase {
    override fun getUserById(authToken: String, userId: Int): Flow<Resource<User>> =
        mIUserRepository.getUserById(authToken, userId)

    override fun updateUserById(authToken: String, userId: Int): Flow<Resource<User>> =
        mIUserRepository.updateUserById(authToken, userId)

    override fun postLogin(email: String, password: String): Flow<Resource<Login>> =
        mIUserRepository.postLogin(email, password)

    override fun postRegister(
        email: String,
        password: String,
        name: String,
        bornDate: String,
        phoneNumber: String
    ): Flow<Resource<Register>> =
        postRegister(email, password, name, bornDate, phoneNumber)

    override fun saveAuthToken(authToken: String) {
        mIUserRepository.saveAuthToken(authToken)
    }

    override fun getAuthToken(): Flow<String> = mIUserRepository.getAuthToken()

    override fun saveUserId(userId: Int) {
        mIUserRepository.saveUserId(userId)
    }

    override fun getUserId(): Flow<Int> = mIUserRepository.getUserId()

}