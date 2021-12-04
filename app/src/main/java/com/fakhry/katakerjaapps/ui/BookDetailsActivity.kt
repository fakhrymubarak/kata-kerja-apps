package com.fakhry.katakerjaapps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.katakerjaapps.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}