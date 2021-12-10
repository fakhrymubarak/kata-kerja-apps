package com.fakhry.katakerjaapps.ui.dashboard.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.adapter.ItemBookWishlistAdapter
import com.fakhry.katakerjaapps.core.domain.model.BorrowedBook
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentWishlistBinding
import com.fakhry.katakerjaapps.ui.dashboard.book.details.BookDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : Fragment(R.layout.fragment_wishlist) {
    private val binding by viewBinding(FragmentWishlistBinding::bind)
    private val wishListViewModel: WishListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishListViewModel.getDummyBorrowedBooks().observe(viewLifecycleOwner, { listBook ->
            populateWishlistBook(listBook)
        })
    }

    private fun populateWishlistBook(listData: List<BorrowedBook>) {
        val adapter = ItemBookWishlistAdapter(listData)
        adapter.onItemClick = { selectedData ->
            intentTo(BookDetailsActivity::class.java, selectedData.idBook)
        }
        binding.rvWishlist.adapter = adapter
    }

    private fun <T> intentTo(destination: Class<T>, idBook: Int?) {
        val intent = Intent(requireContext(), destination)
        if (idBook != null) intent.putExtra(BookDetailsActivity.EXTRA_ID_BOOK, idBook)
        startActivity(intent)
    }
}
