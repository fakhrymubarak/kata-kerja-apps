package com.fakhry.katakerjaapps.ui.dashboard.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.ItemBookHomeAdapter
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentHomeBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import com.fakhry.katakerjaapps.ui.profile.ProfileActivity

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding(FragmentHomeBinding::bind)
    private lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.getDummyUser().observe(viewLifecycleOwner, { user ->
            binding.itemHeader.ivProfile.load(Base64.decode(user.avatar)) {
                transformations(CircleCropTransformation())
            }
            binding.itemHeader.tvName.text = user.name
            binding.itemHeader.ivProfile.setOnClickListener {
                val intent = Intent(requireContext(), ProfileActivity::class.java)
                intent.putExtra(ProfileActivity.EXTRA_USER, user)
                startActivity(intent)
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
            val intent = Intent(requireContext(), BookDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.rvBorrowing.adapter = itemBookHomeAdapter
        binding.itemStatistics.tvNumberBorrow.text = listData.size.toString()

    }

    private fun populateHasReadBook(listData: List<BorrowedBook>) {
        val itemBookHomeAdapter = ItemBookHomeAdapter(listData)
        itemBookHomeAdapter.onItemClick = { selectedData ->
            val intent = Intent(requireContext(), BookDetailsActivity::class.java)
            startActivity(intent)
        }
        binding.rvHasRead.adapter = itemBookHomeAdapter
        binding.itemStatistics.tvNumberRead.text = listData.size.toString()
    }
}