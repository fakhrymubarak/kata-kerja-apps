package com.fakhry.katakerjaapps.core.domain.model

data class BorrowedBook(
    val id: Int,
    val bookData: Book,
    val borrowDate: String,
    val returnDate: String,
    val borrowStatus: Int,
)
