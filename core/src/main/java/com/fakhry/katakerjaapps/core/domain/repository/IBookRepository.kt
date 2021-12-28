package com.fakhry.katakerjaapps.core.domain.repository

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import kotlinx.coroutines.flow.Flow

interface IBookRepository {
    fun getBorrowedBooksById(idUser: Int): Flow<Resource<List<BorrowedBook>>>
    fun getBookDetailsById(bookId: Int): Flow<Resource<Book>>
    fun getSearchedBooks(query: String): Flow<Resource<List<Book>>>
    fun getWishBooks(idUser: Int): Flow<Resource<List<Book>>>
    fun insertWishBooks(authToken:String, idUser: Int, idBook: Int): Flow<Resource<Nothing>>
}