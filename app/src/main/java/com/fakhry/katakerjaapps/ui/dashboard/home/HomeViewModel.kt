package com.fakhry.katakerjaapps.ui.dashboard.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.domain.model.User
import com.fakhry.katakerjaapps.helper.DataDummy

class HomeViewModel : ViewModel() {
    fun getDummyUser(): LiveData<User> = DataDummy.getUserDummy()
    fun getDummyBorrowedBooks(): LiveData<List<BorrowedBook>> = DataDummy.getBorrowedBooks()
}