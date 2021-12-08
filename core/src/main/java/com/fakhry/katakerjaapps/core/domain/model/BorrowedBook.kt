package com.fakhry.katakerjaapps.core.domain.model

data class BorrowedBook(
    val idTransaction: Int,
    val idUser: Int,
    val idBook: Int,
    val title: String,
    val author: String,
    val publisher: String,
    val releaseYear: String,
    val stock: Int,
    val cover: String,
    val borrowDate: String,
    val returnDate: String,
    val borrowStatus: String,
)
