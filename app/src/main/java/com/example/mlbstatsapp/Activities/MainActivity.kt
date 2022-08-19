package com.example.mlbstatsapp.Activities

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.example.mlbstatsapp.LoadData
import com.example.mlbstatsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    private lateinit var navController: NavController

    /**
     * We set the navController and then call methods to setup bottom navigation and the actionNav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNav()
        setupActionNav()
    }

    /**
     * Setups the bottom nav
     * Configures it so the bottom nav is hidden on playerSearch and playerIndividual page
     */
    fun setupBottomNav(){
        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.homeFragment|| nd.id == R.id.teamsListFragment || nd.id == R.id.settingsFragment) {
                bottomNav.visibility = View.VISIBLE
            } else {
                bottomNav.visibility = View.GONE
            }
        }
    }

    /**
     * Setups the actionNav so that there is a back button on the appropriate pages
     */
    fun setupActionNav(){
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}