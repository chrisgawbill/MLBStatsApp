package com.example.mlbstatsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * It stores the playerSearchTerm from the searchView in the app bar
 */
class HomePlayerSearchSharedViewModel: ViewModel() {
    val playerSearchTerm = MutableLiveData<String>()

    fun playerSearchTerm(playerNameParam:String){
        playerSearchTerm.value = playerNameParam
    }
}