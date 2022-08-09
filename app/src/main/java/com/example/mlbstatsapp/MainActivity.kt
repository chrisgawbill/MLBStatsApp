package com.example.mlbstatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, HomeFragment())
                        commit()
                    }
                }
                R.id.teams-> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, TeamsListFragment())
                        commit()
                    }
                }
                R.id.settings-> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, SettingsFragment())
                        commit()
                    }
                }
            }
            true
        }
    }

}