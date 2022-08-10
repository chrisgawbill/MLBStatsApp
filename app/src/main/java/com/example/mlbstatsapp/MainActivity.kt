package com.example.mlbstatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val populateDB= LoadData.getInstance(getApplicationContext())

        populateDB.insertTeams()

        populateDB.updateAllTeams()





    }
}