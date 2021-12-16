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
    override fun getUserById(userId: Int): Flow<Resource<User>> =
        mIUserRepository.getUserById(userId)

    override fun updateUserById(userId: Int): Flow<Resource<User>> =
        mIUserRepository.updateUserById(userId)

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

}