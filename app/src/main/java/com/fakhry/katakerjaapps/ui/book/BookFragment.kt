package com.fakhry.katakerjaapps.ui.book

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentBookBinding

class BookFragment : Fragment(R.layout.fragment_book) {
    private val binding by viewBinding(FragmentBookBinding::bind)
    private lateinit var bookViewModel: BookViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.text.observe(viewLifecycleOwner, {
            binding.tvBorrowed.text = it
        })
    }
}