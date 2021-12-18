package com.fakhry.katakerjaapps.core.helper

import com.fakhry.katakerjaapps.core.data.source.remote.response.book.details.BookDetailsData
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
    }
}