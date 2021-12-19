package com.fakhry.katakerjaapps.ui.dashboard.book

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.book.cover.ItemBookCoverGridAdapter
import com.fakhry.katakerjaapps.adapter.book.linear.ItemBookCoverLinearAdapter
import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.Book
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentBookBinding
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookFragment : Fragment(R.layout.fragment_book) {
    private val binding by viewBinding(FragmentBookBinding::bind)
    private val bookViewModel: BookViewModel by viewModels()

    private val searchMovieAdapter = ItemBookCoverGridAdapter()
    private var isSearchActive = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        setItemLayout()

        bookViewModel.getDummyBorrowedBooks().observe(this, { listBook ->
            populatePopularBook(listBook)
//            populateNewestBook(listBook)
        })
    }

    private fun setItemLayout() {
        if (isSearchActive) {
            binding.itemSearchList.root.visibility = View.VISIBLE
            binding.itemExplore.root.visibility = View.INVISIBLE
        } else {
            binding.itemSearchList.root.visibility = View.INVISIBLE
            binding.itemExplore.root.visibility = View.VISIBLE
        }

    }

    private fun setSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                isSearchActive = true
                setItemLayout()
                searchMovieAdapter.setData(listOf())
                searchBook(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun searchBook(query: String) {
        bookViewModel.searchBooks(query).observe(viewLifecycleOwner, { listSearchedBookResource ->
            when (listSearchedBookResource) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    listSearchedBookResource.data?.let { listBook ->
                        if (listBook.isNotEmpty()) {
                            populateSearchedBooks(listBook)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(), listSearchedBookResource.message, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun populateSearchedBooks(listBook: List<Book>) {
        searchMovieAdapter.setData(listBook)
        binding.itemSearchList.rvSearch.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.itemSearchList.rvSearch.adapter = searchMovieAdapter

    }

    private fun populatePopularBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookCoverLinearAdapter(listData.map { it.bookData })
        adapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
        }
        binding.itemExplore.rvPopulars.adapter = adapter
    }

//    private fun populateNewestBook(listData: List<Book>) {
//        val adapter = ItemBookCoverGridAdapter()
//        adapter.setData(listData)
//        adapter.onItemClick = { selectedData ->
//            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
//        }
//        binding.itemExplore.rvNewest.layoutManager = GridLayoutManager(requireContext(), 3)
//        binding.itemExplore.rvNewest.adapter = adapter
//    }

    private fun <T> intentTo(destination: Class<T>, idBook: Int?) {
        val intent = Intent(requireContext(), destination)
        if (idBook != null) intent.putExtra(BookDetailsActivity.EXTRA_ID_BOOK, idBook)
        startActivity(intent)
    }
}