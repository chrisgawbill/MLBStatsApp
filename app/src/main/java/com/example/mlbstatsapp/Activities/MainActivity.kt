package com.example.mlbstatsapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mlbstatsapp.Fragments.HomeFragment
import com.example.mlbstatsapp.Fragments.SettingsFragment
import com.example.mlbstatsapp.Fragments.TeamsListFragment
import com.example.mlbstatsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, HomeFragment())
                        commit()
                    }
                }
                R.id.teams -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragmentContainerView, TeamsListFragment())
                        commit()
                    }
                }
                R.id.settings -> {
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