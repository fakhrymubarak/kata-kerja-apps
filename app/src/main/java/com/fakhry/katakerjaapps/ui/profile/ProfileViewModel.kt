package com.fakhry.katakerjaapps.ui.profile

import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    fun logout() {
        userUseCase.clearUserDataStore()
    }
}