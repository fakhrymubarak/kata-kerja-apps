package com.fakhry.katakerjaapps.ui.dashboard.book.details

import android.content.Intent
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
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding
    private val viewModel: BookDetailsViewModel by viewModels()

    private var isWishlist = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idBook = intent.getIntExtra(EXTRA_ID_BOOK, 0)
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

        //getWishlistStatus
        //setWishlistButton
        setWishlistButton()
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

            btnAddWishlist.setOnClickListener {
                insertWishlistBook()
            }

            btnShare.setOnClickListener {
                shareBook(book)
            }
        }
    }

    private fun insertWishlistBook() {
        Timber.d("0 btn pressed")
        isWishlist = true
        viewModel.insertWishBook().observe(this, { insertResource ->
            Timber.d("1 vm run")
            if (insertResource != null) {
                when (insertResource) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        Timber.d("2 vm return value")
                        Toast.makeText(this, insertResource.message, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Timber.d("2 vm return error")
                        Toast.makeText(this, insertResource.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setWishlistButton() {
        if (isWishlist) {
            binding.btnAddWishlist.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_mark, 0, 0, 0
            )
        } else {
            binding.btnAddWishlist.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_add_dark, 0, 0, 0
            )
        }
    }

    private fun shareBook(book: Book) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Book Title\t: ${book.title}" +
                        "\nAuthor\t: ${book.author} " +
                        "\nCategory\t: ${book.category} " +
                        "\nPublisher\t: ${book.publisher} " +
                        "\nRelease\t: ${book.releaseYear} " +
                        "\nDescription\t:\n" +
                        book.description
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, book.title)
        startActivity(shareIntent)
    }

    companion object {
        const val EXTRA_ID_BOOK = "extra_id_book"
    }
}