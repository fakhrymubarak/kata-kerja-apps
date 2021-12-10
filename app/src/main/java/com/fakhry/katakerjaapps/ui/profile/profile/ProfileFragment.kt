package com.fakhry.katakerjaapps.ui.profile.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.CircleCropTransformation
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentProfileBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle = requireActivity().intent?.extras!!
        val user = ProfileFragmentArgs.fromBundle(bundle).extraUser
        val navController = findNavController()
        binding.apply {
            ivAvatar.load(Base64.decode(user.avatar)) { transformations(CircleCropTransformation()) }
            tvProfileName.text = user.name
            tvProfileMail.text = user.email
            tvProfileJoinDate.text = getString(R.string.joined_since, user.memberSince)

            btnEditProfile.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_edit_profile)
            }
            btnChangePassword.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_change_password)
            }
            btnSettings.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_setting)
            }
            btnAboutUs.setOnClickListener {
                navController.navigate(R.id.action_nav_profile_to_nav_about)
            }
            btnLogout.setOnClickListener {
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finishAffinity()
            }
        }
    }
}