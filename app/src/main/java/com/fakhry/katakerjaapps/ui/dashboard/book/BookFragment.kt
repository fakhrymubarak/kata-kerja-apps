package com.fakhry.katakerjaapps.ui.dashboard.book

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.book.cover.ItemBookCoverGridAdapter
import com.fakhry.katakerjaapps.adapter.book.linear.ItemBookCoverLinearAdapter
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentBookBinding
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment(R.layout.fragment_book) {
    private val binding by viewBinding(FragmentBookBinding::bind)
    private val bookViewModel: BookViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel.getDummyBorrowedBooks().observe(this, { listBook ->
            populatePopularBook(listBook)
            populateNewestBook(listBook)
        })
    }

    private fun populatePopularBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookCoverLinearAdapter(listData.map { it.bookData })
        adapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
        }
        binding.rvPopulars.adapter = adapter
    }

    private fun populateNewestBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookCoverGridAdapter(listData.map { it.bookData })
        adapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
        }
        binding.rvNewest.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvNewest.adapter = adapter
    }

    private fun <T> intentTo(destination: Class<T>, idBook: Int?) {
        val intent = Intent(requireContext(), destination)
        if (idBook != null) intent.putExtra(BookDetailsActivity.EXTRA_ID_BOOK, idBook)
        startActivity(intent)
    }
}