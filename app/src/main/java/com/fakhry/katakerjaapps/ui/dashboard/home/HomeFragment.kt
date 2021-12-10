package com.fakhry.katakerjaapps.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.ItemBookHomeAdapter
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.domain.model.User
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentHomeBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import com.fakhry.katakerjaapps.ui.profile.ProfileActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getDummyUser().observe(viewLifecycleOwner, { user ->
            binding.itemHeader.ivProfile.load(Base64.decode(user.avatar)) {
                transformations(CircleCropTransformation())
            }
            binding.itemHeader.tvName.text = user.name
            binding.itemHeader.ivProfile.setOnClickListener {
                intentTo(ProfileActivity::class.java, null, user)
            }
        })

        homeViewModel.getDummyBorrowedBooks().observe(viewLifecycleOwner, { listTransactionBook ->
            val listBorrowed = listTransactionBook.filter { it.borrowStatus == "dipinjam" }
            val listHasRead = listTransactionBook.filter { it.borrowStatus == "kembali" }
            populateBorrowedBook(listBorrowed)
            populateHasReadBook(listHasRead)
        })
    }

    private fun populateBorrowedBook(listData: List<BorrowedBook>) {
        val itemBookHomeAdapter = ItemBookHomeAdapter(listData)
        itemBookHomeAdapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook, null)
        }
        binding.rvBorrowing.adapter = itemBookHomeAdapter
        binding.itemStatistics.tvNumberBorrow.text = listData.size.toString()

    }

    private fun populateHasReadBook(listData: List<BorrowedBook>) {
        val itemBookHomeAdapter = ItemBookHomeAdapter(listData)
        itemBookHomeAdapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook, null)
        }
        binding.rvHasRead.adapter = itemBookHomeAdapter
        binding.itemStatistics.tvNumberRead.text = listData.size.toString()
    }

    private fun <T> intentTo(destination: Class<T>, idBook: Int?, user: User?) {
        val intent = Intent(requireContext(), destination)
        if (idBook != null) intent.putExtra(BookDetailsActivity.EXTRA_ID_BOOK, idBook)
        if (user != null) intent.putExtra(ProfileActivity.EXTRA_USER, user)
        startActivity(intent)
    }
}