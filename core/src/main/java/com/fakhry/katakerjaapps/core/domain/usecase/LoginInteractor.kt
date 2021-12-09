package com.fakhry.loonly.core.domain.usecase

import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import com.fakhry.katakerjaapps.core.domain.usecase.LoginUseCase
import javax.inject.Inject


class LoginInteractor @Inject constructor(private val mIUserRepository: IUserRepository) :
    LoginUseCase {

}