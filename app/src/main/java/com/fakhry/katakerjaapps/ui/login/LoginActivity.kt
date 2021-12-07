package com.fakhry.katakerjaapps.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.katakerjaapps.databinding.ActivityLoginBinding
import com.fakhry.katakerjaapps.databinding.ActivityOnBoardingBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}