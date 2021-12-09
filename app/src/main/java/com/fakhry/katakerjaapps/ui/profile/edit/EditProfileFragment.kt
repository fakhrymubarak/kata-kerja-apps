package com.fakhry.katakerjaapps.ui.profile.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentEditProfileBinding
import com.fakhry.katakerjaapps.helper.Base64
import com.fakhry.katakerjaapps.ui.profile.ProfileFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {
    private val binding by viewBinding(FragmentEditProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle = requireActivity().intent?.extras!!
        val user = ProfileFragmentArgs.fromBundle(bundle).extraUser

        binding.apply {
            populateAvatar(user.avatar)
            etName.setText(user.name)
            etAddress.setText(user.fullAddress)
            etBornDate.setText(user.bornDate)
            etEmail.setText(user.email)
            etPhone.setText(user.phoneNumber)
        }
    }

    private fun populateAvatar(avatar: String?) {
        var isAvatarAdded: Boolean
        binding.apply {
            isAvatarAdded = if (avatar != null) {
                ivEditAvatar.load(Base64.decode(avatar)) { transformations(CircleCropTransformation()) }
                btnUpdateAvatar.setImageResource(R.drawable.ic_delete)
                true
            } else {
                ivEditAvatar.load(R.drawable.ic_empty_avatar)
                btnUpdateAvatar.setImageResource(R.drawable.ic_add)
                false
            }

            btnUpdateAvatar.setOnClickListener {
                isAvatarAdded = if (isAvatarAdded) {
                    ivEditAvatar.load(R.drawable.ic_empty_avatar)
                    btnUpdateAvatar.setImageResource(R.drawable.ic_add)
                    false
                } else {
                    // Add Picture
                    btnUpdateAvatar.setImageResource(R.drawable.ic_delete)
                    true
                }
            }
        }
    }
}