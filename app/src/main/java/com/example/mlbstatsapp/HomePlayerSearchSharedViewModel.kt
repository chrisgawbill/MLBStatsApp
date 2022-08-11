package com.example.mlbstatsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomePlayerSearchSharedViewModel: ViewModel() {
    val playerSearchTerm = MutableLiveData<String>()

    fun playerSearchTerm(playerNameParam:String){
        playerSearchTerm.value = playerNameParam
    }
}