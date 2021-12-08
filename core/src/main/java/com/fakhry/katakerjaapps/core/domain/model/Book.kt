package com.fakhry.katakerjaapps.core.domain.model

data class Book(
    val idBook: Int,
    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
    val releaseYear: String,
    val stock: String,
    val cover: String,
    val category: String,
)
