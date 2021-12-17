package com.fakhry.katakerjaapps.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(userUseCase: UserUseCase) : ViewModel() {
    val getUserId: LiveData<Int> = userUseCase.getUserId().asLiveData()
}