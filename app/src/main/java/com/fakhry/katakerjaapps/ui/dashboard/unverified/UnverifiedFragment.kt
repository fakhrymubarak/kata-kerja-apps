package com.fakhry.katakerjaapps.ui.dashboard.unverified

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentUnverifiedBinding
import com.fakhry.katakerjaapps.ui.profile.ProfileActivity

class UnverifiedFragment : Fragment(R.layout.fragment_unverified) {
    private val binding by viewBinding(FragmentUnverifiedBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{
            itemHeader.ivProfile.setOnClickListener {
                startActivity(Intent(requireContext(), ProfileActivity::class.java))
            }
            btnGoToKatakerja.setOnClickListener {
                val gmmIntentUri = Uri.parse("geo:-5.168062,119.494937?z=10&q=Katakerja")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }
}