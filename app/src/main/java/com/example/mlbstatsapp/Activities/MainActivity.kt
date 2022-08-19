package com.example.mlbstatsapp.Activities

import android.R
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mlbstatsapp.Fragments.IndividualPlayer
import com.example.mlbstatsapp.Fragments.PlayerSearchResultsFragment
import com.example.mlbstatsapp.HomeFragment
import com.example.mlbstatsapp.HomePlayerSearchSharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.MenuItem as MenuItem1


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    lateinit var bottomNav:BottomNavigationView
    lateinit var searchView: SearchView
    private lateinit var navController: NavController
    lateinit var sharedViewModel:HomePlayerSearchSharedViewModel

    /**
     * We set the navController and then call methods to setup bottom navigation and the actionNav
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mlbstatsapp.R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(com.example.mlbstatsapp.R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupBottomNav()
        setupActionNav()
    }

    /**
     * Setups the bottom nav
     * Configures it so the bottom nav is hidden on playerSearch and playerIndividual page
     */
    fun setupBottomNav(){
        bottomNav = findViewById(com.example.mlbstatsapp.R.id.bottomNavigationView)
        bottomNav.setupWithNavController(navController)
        /*
        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == com.example.mlbstatsapp.R.id.homeFragment|| nd.id == com.example.mlbstatsapp.R.id.teamsListFragment || nd.id == com.example.mlbstatsapp.R.id.settingsFragment) {
                bottomNav.visibility = View.VISIBLE
            } else {
                bottomNav.visibility = View.GONE
            }
        }
        */
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.mlbstatsapp.R.menu.action_bar_search, menu )
        var searchItem: android.view.MenuItem =
            menu!!.findItem(com.example.mlbstatsapp.R.id.app_bar_menu_search);
        searchView =  MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(this)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem1):Boolean {
        when(item.itemId){
            com.example.mlbstatsapp.R.id.app_bar_menu_search->{
                searchView.setIconified(false);
                return true;
            }
        }
        return false;
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        sharedViewModel = this?.run{
            ViewModelProviders.of(this).get(HomePlayerSearchSharedViewModel::class.java)
        }?: throw Exception("Invalid Activity")
        sharedViewModel.playerSearchTerm.value = p0

        var currentFragment:String = navController.currentDestination!!.label.toString()
        Log.d(MainActivity::class.java.simpleName, navController.currentDestination!!.label.toString())
        var view:View = findViewById(R.id.content)
        if(currentFragment.equals("Home")){
            navController.navigate(com.example.mlbstatsapp.R.id.action_homeFragment_to_playerSearchResultsFragment)
        }else if(currentFragment.equals("Player Search Results")){
            navController.navigate(com.example.mlbstatsapp.R.id.action_playerSearchResultsFragment_self)
        }else if(currentFragment.equals("Player Page")){
            navController.navigate(com.example.mlbstatsapp.R.id.action_individualPlayer_to_playerSearchResultsFragment)
        }

        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }
}