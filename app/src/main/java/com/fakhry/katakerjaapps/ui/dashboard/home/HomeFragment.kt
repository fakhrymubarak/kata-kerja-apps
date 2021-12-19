package com.fakhry.katakerjaapps.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.book.transaction.ItemBookTransactionAdapter
import com.fakhry.katakerjaapps.core.data.Resource
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentHomeBinding
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserId().observe(this, { userId ->
            observeBookTransaction(userId)
        })
    }

    private fun observeBookTransaction(userId: Int) {
        viewModel.getBorrowedBooksById(userId).observe(viewLifecycleOwner, { listTransRes ->
            if (listTransRes != null) {
                when (listTransRes) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        listTransRes.data?.let { listTransaction ->
                            if (listTransaction.isNotEmpty()) {
                                val listBorrowed = listTransaction.filter { it.borrowStatus == 1 }
                                val listHasRead = listTransaction.filter { it.borrowStatus == 2 }
                                populateBorrowedBook(listBorrowed)
                                populateHasReadBook(listHasRead)
                            }
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(), listTransRes.message, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun populateBorrowedBook(listData: List<BorrowedBook>) {
        if (listData.isEmpty()) {
            binding.tvBorrowed.visibility = View.GONE
            binding.rvBorrowed.visibility = View.GONE
        } else {
            binding.tvBorrowed.visibility = View.VISIBLE
            binding.rvBorrowed.visibility = View.VISIBLE
            val itemBookHomeAdapter = ItemBookTransactionAdapter(listData)
            itemBookHomeAdapter.onItemClick = { selectedData ->
                intentTo(BookDetailsActivity::class.java, selectedData.bookData.idBook)
            }
            binding.rvBorrowed.adapter = itemBookHomeAdapter
            binding.itemStatistics.tvNumberBorrow.text = listData.size.toString()
        }
    }


    private fun populateHasReadBook(listData: List<BorrowedBook>) {
        if (listData.isEmpty()) {
            binding.tvHasRead.visibility = View.GONE
            binding.rvHasRead.visibility = View.GONE
        } else {
            binding.tvHasRead.visibility = View.VISIBLE
            binding.rvHasRead.visibility = View.VISIBLE
            val itemBookHomeAdapter = ItemBookTransactionAdapter(listData)
            itemBookHomeAdapter.onItemClick = { selectedData ->
                intentTo(BookDetailsActivity::class.java, selectedData.bookData.idBook)
            }
            binding.rvHasRead.adapter = itemBookHomeAdapter
            binding.itemStatistics.tvNumberRead.text = listData.size.toString()
        }
    }

    private fun <T> intentTo(destination: Class<T>, idBook: Int?) {
        val intent = Intent(requireContext(), destination)
        if (idBook != null) intent.putExtra(BookDetailsActivity.EXTRA_ID_BOOK, idBook)
        startActivity(intent)
    }
}