package com.fakhry.katakerjaapps.core.domain.usecase.book

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import kotlinx.coroutines.flow.Flow

interface BookUseCase {
    fun getBookDetailsById(bookId: Int): Flow<Resource<Book>>
    fun getBorrowedBooksById(idUser: Int): Flow<Resource<List<BorrowedBook>>>
}