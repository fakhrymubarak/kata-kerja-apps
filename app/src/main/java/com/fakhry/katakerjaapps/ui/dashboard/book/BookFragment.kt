package com.fakhry.katakerjaapps.ui.dashboard.book

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.ItemBookCoverGridAdapter
import com.fakhry.katakerjaapps.adapter.ItemBookCoverLinearAdapter
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentBookBinding
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity

class BookFragment : Fragment(R.layout.fragment_book) {
    private val binding by viewBinding(FragmentBookBinding::bind)
    private lateinit var bookViewModel: BookViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel = ViewModelProvider(this)[BookViewModel::class.java]

        bookViewModel.getDummyBorrowedBooks().observe(this, { listBook ->
            populatePopularBook(listBook)
            populateNewestBook(listBook)
        })
    }

    private fun populatePopularBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookCoverLinearAdapter(listData)
        adapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
        }
        binding.rvPopulars.adapter = adapter
    }

    private fun populateNewestBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookCoverGridAdapter(listData)
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