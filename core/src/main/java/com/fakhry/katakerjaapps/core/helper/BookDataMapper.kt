package com.fakhry.katakerjaapps.core.helper

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsData
import com.fakhry.katakerjaapps.core.data.source.remote.response.book.search.SearchedBookData
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

        fun mapResponseToDomain(listSearchedBookData: List<SearchedBookData>): List<BookDomain> =
            listSearchedBookData.map { bookData ->
                BookDomain(
                    idBook = bookData.id,
                    isbn = bookData.isbn,
                    title = bookData.judul,
                    description = bookData.deskripsi,
                    author = bookData.author,
                    publisher = bookData.penerbit,
                    releaseYear = bookData.tahunTerbit.toString(),
                    stock = bookData.stock,
                    cover = bookData.fotoBuku ?: "",
                    category = bookData.kategori,
                )
            }
    }
}