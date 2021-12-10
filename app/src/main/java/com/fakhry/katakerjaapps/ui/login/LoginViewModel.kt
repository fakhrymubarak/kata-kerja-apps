package com.fakhry.katakerjaapps.ui.login

import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
}