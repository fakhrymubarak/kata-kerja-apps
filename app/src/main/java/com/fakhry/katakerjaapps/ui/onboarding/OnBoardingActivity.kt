package com.fakhry.katakerjaapps.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fakhry.katakerjaapps.databinding.ActivityMainBinding
import com.fakhry.katakerjaapps.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}