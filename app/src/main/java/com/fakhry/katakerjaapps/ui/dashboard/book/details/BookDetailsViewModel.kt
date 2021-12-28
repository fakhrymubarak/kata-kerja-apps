package com.fakhry.katakerjaapps.ui.dashboard.book.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.usecase.book.BookUseCase
import com.fakhry.katakerjaapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val bookUseCase: BookUseCase
) : ViewModel() {
    private val watchlistStatus = MutableStateFlow(true)
    private val _book = MutableSharedFlow<Book>()

    private var _userId by Delegates.notNull<Int>()
    private lateinit var _authToken: String

    init {
        Timber.d("1 vm run")
        viewModelScope.launch {
            userUseCase.getUserId().onEach { userId ->
                Timber.d("2 got user id = $userId")
                _userId = userId
            }.launchIn(this)
            userUseCase.getAuthToken().onEach { authToken ->
                Timber.d("3 got auth token = $authToken")
                _authToken = authToken
            }.launchIn(this)
        }
    }

    fun getDetailBooks(idBook: Int): LiveData<Resource<Book>> {
        val bookDetailsFlow = bookUseCase.getBookDetailsById(idBook)
        viewModelScope.launch {
            bookDetailsFlow.collectLatest { bookResource ->
                if (bookResource is Resource.Success) {
                    val bookData = bookResource.data
                    bookData?.let { _book.emit(bookData) }
                }
            }
        }
        return bookDetailsFlow.asLiveData()
    }

    fun insertWishBook(): LiveData<Resource<Nothing>> =
        flow {
            _book.collect { bookData ->
                Timber.d("4 got book data")
                bookUseCase.insertWishBooks(_authToken, _userId, bookData.idBook)
                    .collectLatest { insertResource ->
                        Timber.d("5 got resource")
                        emit(insertResource)
                    }
            }
        }.asLiveData()
}

