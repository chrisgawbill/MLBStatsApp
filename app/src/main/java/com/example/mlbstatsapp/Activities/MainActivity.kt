package com.example.mlbstatsapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.example.mlbstatsapp.LoadData
import com.example.mlbstatsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        val populateDB= LoadData.getInstance(applicationContext)
        populateDB.insertTeams()
        populateDB.updateAllTeams()

        setupBottomNav()
        setupActionNav()
    }
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
    fun setupActionNav(){
        setupActionBarWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}