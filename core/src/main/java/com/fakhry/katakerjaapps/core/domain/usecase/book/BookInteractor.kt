package com.fakhry.katakerjaapps.core.domain.usecase.book

import com.fakhry.katakerjaapps.core.data.source.repository.BookRepository
import javax.inject.Inject

class BookInteractor @Inject constructor(private val mBookRepository: BookRepository) :
    BookUseCase {

}