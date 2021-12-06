package com.fakhry.katakerjaapps.ui.profile.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {
    private val binding by viewBinding(FragmentEditProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}