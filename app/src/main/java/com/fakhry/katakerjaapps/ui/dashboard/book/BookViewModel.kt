package com.fakhry.katakerjaapps.ui.dashboard.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.domain.model.User
import com.fakhry.katakerjaapps.helper.DataDummy

class BookViewModel : ViewModel() {

    fun getDetailBooks(idBook: Int): LiveData<Book> = DataDummy.getBookDetails(idBook)
    fun getDummyBorrowedBooks(): LiveData<List<BorrowedBook>> = DataDummy.getBorrowedBooks()

}