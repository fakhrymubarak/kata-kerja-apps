package com.fakhry.katakerjaapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.fakhry.katakerjaapps.const.Settings
import com.fakhry.katakerjaapps.ui.dashboard.MainActivity
import com.fakhry.katakerjaapps.ui.onboarding.OnBoardingActivity
import com.fakhry.katakerjaapps.ui.onboarding.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIsLogin()

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getInt(Settings.KEY_THEMES_MODE, Settings.DEFAULT_THEMES_MODE)
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private fun checkIsLogin() {
        viewModel.getUserId.observe(this, { userId ->
            if (userId != 0) {
                intentTo(MainActivity::class.java)
            } else {
                intentTo(OnBoardingActivity::class.java)
            }
        })
    }

    private fun <T> intentTo(destination: Class<T>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        finish()
    }

}