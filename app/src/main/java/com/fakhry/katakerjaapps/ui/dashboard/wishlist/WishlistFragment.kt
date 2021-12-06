package com.fakhry.katakerjaapps.ui.dashboard.wishlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment(R.layout.fragment_wishlist) {
    private val binding by viewBinding(FragmentWishlistBinding::bind)
    private lateinit var wishListViewModel: WishListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wishListViewModel = ViewModelProvider(this).get(WishListViewModel::class.java)
        wishListViewModel.text.observe(viewLifecycleOwner, {
        })
    }
}