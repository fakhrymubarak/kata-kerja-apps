package com.fakhry.katakerjaapps.core.data.source.repository

import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.data.source.remote.RemoteBookDataSource
import com.fakhry.katakerjaapps.core.data.source.remote.network.ApiResponse
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist.WishlistBooksData
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.domain.repository.IBookRepository
import com.fakhry.katakerjaapps.core.helper.BookDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    private val remoteBookDataSource: RemoteBookDataSource,
) : IBookRepository {

    override fun getBorrowedBooksById(idUser: Int): Flow<Resource<List<BorrowedBook>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteBookDataSource.getBorrowedBooksById(idUser).first()) {
                is ApiResponse.Success -> {
                    val listBookDomain = mapListBorrowedBookToListBookDomain(apiResponse.data)
                    emit(Resource.Success(listBookDomain))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun getBookDetailsById(bookId: Int): Flow<Resource<Book>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteBookDataSource.getBookDetailsById(bookId).first()) {
                is ApiResponse.Success -> {
                    val data = BookDataMapper.Book.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun getSearchedBooks(query: String): Flow<Resource<List<Book>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteBookDataSource.getSearchedBooks(query).first()) {
                is ApiResponse.Success -> {
                    val data = BookDataMapper.Book.mapResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}

            }
        }

    override fun getWishBooks(idUser: Int): Flow<Resource<List<Book>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteBookDataSource.getWishBooksById(idUser).first()) {
                is ApiResponse.Success -> {
                    val listBookDomain = mapListWishBookToListBookDomain(apiResponse.data)
                    emit(Resource.Success(listBookDomain))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}
            }
        }

    override fun insertWishBooks(authToken:String, idUser: Int, idBook: Int): Flow<Resource<Nothing>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteBookDataSource.insertWishBook(authToken, idUser, idBook).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Error("Success insert data."))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {}
            }
        }

    private suspend fun mapListBorrowedBookToListBookDomain(listBorrowedBooksData: List<BorrowedBooksData>): List<BorrowedBook> {
        val listBookDetailsData = ArrayList<BorrowedBook>()
        listBorrowedBooksData.map { borrowedBookData ->
            getBookDetailsById(borrowedBookData.bookId).collectLatest { bookResource ->
                if (bookResource is Resource.Success) {
                    bookResource.data?.let {
                        listBookDetailsData.add(
                            BorrowedBook(
                                id = borrowedBookData.id,
                                bookData = it,
                                borrowDate = borrowedBookData.borrowDate,
                                returnDate = borrowedBookData.returnDate,
                                borrowStatus = borrowedBookData.status,
                            )
                        )
                    }
                }
            }
        }
        return listBookDetailsData
    }

    private suspend fun mapListWishBookToListBookDomain(listWishBook: List<WishlistBooksData>): List<Book> {
        val listBookDetailsData = ArrayList<Book>()
        listWishBook.map { wishBooks ->
            getBookDetailsById(wishBooks.bookId).collectLatest { bookResource ->
                if (bookResource is Resource.Success) {
                    bookResource.data?.let { book -> listBookDetailsData.add(book) }
                }
            }
        }
        return listBookDetailsData
    }
}