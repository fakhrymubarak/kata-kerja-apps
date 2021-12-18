package com.fakhry.katakerjaapps.core.domain.repository

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.SearchedBookData
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import kotlinx.coroutines.flow.Flow

interface IBookRepository {
    fun getBorrowedBooksById(idUser: Int): Flow<Resource<List<BorrowedBook>>>
    fun getBookDetailsById(bookId: Int): Flow<Resource<Book>>
    fun getSearchedBooks(query: String): Flow<Resource<List<Book>>>
}