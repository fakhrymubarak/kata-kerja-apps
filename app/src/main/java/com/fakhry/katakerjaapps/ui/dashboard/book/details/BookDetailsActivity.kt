package com.fakhry.katakerjaapps.ui.dashboard.book.details

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.databinding.ActivityBookDetailsBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.dashboard.book.BookViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding
    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idBook = intent.getIntExtra(EXTRA_ID_BOOK, 0)
        Timber.d(idBook.toString())
        viewModel.getDetailBooks(idBook).observe(this, { bookResource ->
            if (bookResource != null) {
                when (bookResource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        bookResource.data?.let { populateView(it) }
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, bookResource.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun populateView(book: Book) {
        binding.apply {
            tvDetailTitle.text = book.title
            tvDetailAuthor.text = book.author
            tvDetailCategory.text = book.category
            tvDetailYear.text = book.releaseYear
            tvDetailIsbn.text = book.isbn
            tvDetailPublisher.text = book.publisher
            tvDetailDesc.text = book.description

            try {
                ivDetailCover.load(Base64.decode(book.cover))
            } catch (e: Exception) {
                ivDetailCover.load(R.drawable.img_cover_default)
            }

            btnDetailBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    companion object {
        const val EXTRA_ID_BOOK = "extra_id_book"
    }
}