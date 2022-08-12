package com.example.mlbstatsapp

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher

class HomeViewModel(activity: FragmentActivity?):ViewModel() {
    val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)
    fun getAllBatters():List<Batter>{
        return db.getAllBatters()
    }
    fun getAllPitchers():List<Pitcher>{
        return db.getAllPitchers()
    }
}