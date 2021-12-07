package com.fakhry.katakerjaapps.ui.profile.password

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

class ChangePassword : Fragment(R.layout.fragment_edit_profile) {
    private val binding by viewBinding(FragmentEditProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle = requireActivity().intent?.extras!!
        val user = ProfileFragmentArgs.fromBundle(bundle).extraUser

        binding.apply {
            ivEditAvatar.load(Base64.decode(user.avatar)) { transformations(CircleCropTransformation()) }
        }
    }
}