package com.fakhry.katakerjaapps.ui.dashboard.book.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.databinding.ActivityBookDetailsBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.dashboard.book.BookViewModel

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idBook = intent.getIntExtra(EXTRA_ID_BOOK, 1000)
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]
        bookViewModel.getDetailBooks(idBook).observe(this, {
            populateView(it)
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
            ivDetailCover.load(Base64.decode(book.cover))
            btnDetailBack.setOnClickListener {
                onBackPressed()
            }
        }

    }

    companion object {
        const val EXTRA_ID_BOOK = "extra_id_book"
    }

}