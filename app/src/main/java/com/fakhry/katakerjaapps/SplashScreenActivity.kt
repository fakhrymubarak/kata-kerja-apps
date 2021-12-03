package com.fakhry.katakerjaapps

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.fakhry.katakerjaapps.const.Settings

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getInt(Settings.KEY_THEMES_MODE, Settings.DEFAULT_THEMES_MODE)
        AppCompatDelegate.setDefaultNightMode(mode)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}