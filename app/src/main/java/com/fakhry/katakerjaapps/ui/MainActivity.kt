package com.fakhry.katakerjaapps.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fakhry.katakerjaapps.R
import com.fakhry.katakerjaapps.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        // if role is 4 then :
        // navController.navigate(R.id.action_navigation_home_to_navigation_unverified)
        // binding.navView.visibility = View.INVISIBLE
    }
}