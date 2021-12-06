package com.fakhry.katakerjaapps.ui.profile.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.core.utils.viewBinding
import com.fakhry.katakerjaapps.databinding.FragmentEditProfileBinding
import com.fakhry.katakerjaapps.databinding.FragmentSettingBinding

class SettingFragment : Fragment(R.layout.fragment_setting) {
    private val binding by viewBinding(FragmentSettingBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}