package com.fakhry.katakerjaapps.ui.dashboard.unverified

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentUnverifiedBinding

class UnverifiedFragment : Fragment(R.layout.fragment_unverified) {
    private val binding by viewBinding(FragmentUnverifiedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}