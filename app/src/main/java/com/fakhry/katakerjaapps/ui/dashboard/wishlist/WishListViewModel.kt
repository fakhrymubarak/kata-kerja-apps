package com.fakhry.katakerjaapps.ui.dashboard.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.helper.DataDummy

class WishListViewModel : ViewModel() {
    fun getDummyBorrowedBooks(): LiveData<List<BorrowedBook>> = DataDummy.getBorrowedBooks()
}