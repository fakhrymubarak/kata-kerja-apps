package com.fakhry.katakerjaapps.core.domain.usecase.book

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.data.source.repository.BookRepository
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookInteractor @Inject constructor(private val mBookRepository: BookRepository) :
    BookUseCase {
    override fun getBorrowedBooksById(idUser: Int): Flow<Resource<List<BorrowedBook>>> =
        mBookRepository.getBorrowedBooksById(idUser)

    override fun getBookDetailsById(bookId: Int): Flow<Resource<Book>> =
        mBookRepository.getBookDetailsById(bookId)

    override fun getSearchedBooks(query: String): Flow<Resource<List<Book>>> =
        mBookRepository.getSearchedBooks(query)

    override fun getBooksByCat(category: String): Flow<Resource<List<Book>>> =
        mBookRepository.getBooksByCat(category)

    override fun getWishBooks(idUser: Int): Flow<Resource<List<Book>>> =
        mBookRepository.getWishBooks(idUser)

    override fun insertWishBooks(
        authToken: String, idUser: Int, idBook: Int
    ): Flow<Resource<Nothing>> =
        mBookRepository.insertWishBooks(authToken, idUser, idBook)
}