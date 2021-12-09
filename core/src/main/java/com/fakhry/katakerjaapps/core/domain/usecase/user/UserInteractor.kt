package com.fakhry.katakerjaapps.core.domain.usecase.user

import com.fakhry.katakerjaapps.core.domain.repository.IUserRepository
import javax.inject.Inject


class UserInteractor @Inject constructor(private val mIUserRepository: IUserRepository) :
    UserUseCase {

}