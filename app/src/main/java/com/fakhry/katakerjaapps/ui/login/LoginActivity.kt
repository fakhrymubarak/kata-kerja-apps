package com.fakhry.katakerjaapps.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.katakerjaapps.databinding.ActivityLoginBinding
import com.fakhry.katakerjaapps.ui.dashboard.MainActivity
import com.fakhry.katakerjaapps.ui.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvRegister.setOnClickListener { intentTo(RegisterActivity::class.java) }
            btnLogin.setOnClickListener { intentTo(MainActivity::class.java) }
            btnLoginGoogle.setOnClickListener {}
        }
    }

    private fun <T> intentTo(destination: Class<T>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        finish()
    }
}