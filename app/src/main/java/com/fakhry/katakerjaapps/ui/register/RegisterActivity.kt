package com.fakhry.katakerjaapps.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fakhry.katakerjaapps.databinding.ActivityRegisterBinding
import com.fakhry.katakerjaapps.ui.dashboard.MainActivity
import com.fakhry.katakerjaapps.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvLogin.setOnClickListener { intentTo(LoginActivity::class.java) }
            btnRegister.setOnClickListener { intentTo(MainActivity::class.java) }
            btnRegisterGoogle.setOnClickListener {}
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun <T> intentTo(destination: Class<T>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        finish()
    }
}