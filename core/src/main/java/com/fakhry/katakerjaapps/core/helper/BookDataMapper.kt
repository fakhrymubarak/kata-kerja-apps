package com.fakhry.katakerjaapps.core.helper

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.borrow.BorrowedBooksData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.SearchedBookData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.wishlist.show.WishlistBookData
import com.fakhry.katakerjaapps.core.domain.model.Book as BookDomain

object BookDataMapper {
    object Book {
        fun mapResponseToDomain(bookDetailsData: BookDetailsData): BookDomain =
            BookDomain(
                idBook = bookDetailsData.id,
                isbn = bookDetailsData.isbn,
                title = bookDetailsData.judul,
                description = bookDetailsData.deskripsi,
                author = bookDetailsData.author,
                publisher = bookDetailsData.penerbit,
                releaseYear = bookDetailsData.tahunTerbit.toString(),
                stock = bookDetailsData.stock,
                cover = bookDetailsData.fotoBuku ?: "",
                category = bookDetailsData.kategori,
            )

        fun mapResponseToDomain(borrowedBooksData: BorrowedBooksData): BookDomain =
            BookDomain(
                idBook = borrowedBooksData.id,
                isbn = borrowedBooksData.isbn,
                title = borrowedBooksData.judul,
                description = borrowedBooksData.deskripsi,
                author = borrowedBooksData.author,
                publisher = borrowedBooksData.penerbit,
                releaseYear = borrowedBooksData.tahunTerbit.toString(),
                stock = borrowedBooksData.stock,
                cover = borrowedBooksData.fotoBuku,
                category = borrowedBooksData.kategori,
            )


        fun mapResponseToDomain(searchedBookData: SearchedBookData): BookDomain =
            BookDomain(
                idBook = searchedBookData.id,
                isbn = searchedBookData.isbn,
                title = searchedBookData.judul,
                description = searchedBookData.deskripsi,
                author = searchedBookData.author,
                publisher = searchedBookData.penerbit,
                releaseYear = searchedBookData.tahunTerbit.toString(),
                stock = searchedBookData.stock,
                cover = searchedBookData.fotoBuku ?: "",
                category = searchedBookData.kategori,
            )

        fun mapResponseToDomain(wishlistBook: WishlistBookData): BookDomain =
            BookDomain(
                idBook = wishlistBook.id,
                isbn = wishlistBook.isbn,
                title = wishlistBook.judul,
                description = wishlistBook.deskripsi,
                author = wishlistBook.author,
                publisher = wishlistBook.penerbit,
                releaseYear = wishlistBook.tahunTerbit.toString(),
                stock = wishlistBook.stock,
                cover = wishlistBook.fotoBuku,
                category = wishlistBook.kategori,
            )

    }
}