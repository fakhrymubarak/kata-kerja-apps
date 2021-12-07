package com.fakhry.katakerjaapps.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentProfileBinding
import com.fakhry.katakerjaapps.ui.login.LoginActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.apply {
            btnEditProfile.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_edit_profile)
            }
            btnChangePassword.setOnClickListener {

            }
            btnSettings.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_setting)
            }
            btnAboutUs.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_about)
            }
            btnLogout.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
            }
        }
    }
}