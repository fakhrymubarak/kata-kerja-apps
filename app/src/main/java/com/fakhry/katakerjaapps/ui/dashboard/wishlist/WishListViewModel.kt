package com.fakhry.katakerjaapps.ui.dashboard.wishlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.domain.usecase.book.BookUseCase
import com.fakhry.katakerjaapps.helper.DataDummy
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(private val bookUseCase: BookUseCase) : ViewModel() {
    fun getDummyBorrowedBooks(): LiveData<List<BorrowedBook>> = DataDummy.getBorrowedBooks()
}